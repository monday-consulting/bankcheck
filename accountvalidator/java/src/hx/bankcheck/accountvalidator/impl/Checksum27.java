package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

public class Checksum27 extends Checksum00 {

	private ChecksumValidator m10h;

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] == 0)
			return super.validate(accountNumber);
		
		ChecksumValidator cs = getChecksumM10H();
		
		return cs.validate(accountNumber, null);
	}
	
	private ChecksumValidator getChecksumM10H() {
		if (m10h == null) 
			m10h = new ChecksumM10H();
		
		return m10h;
	}

}
