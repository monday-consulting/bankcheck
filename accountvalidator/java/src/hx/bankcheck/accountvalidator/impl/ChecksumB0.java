/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB0 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (((accountNumber[0] == 0)) || (accountNumber[0] == 8)
				|| (accountNumber[1] == 8)) {
			return false;
		} else {
			if ((accountNumber[7] == 1) || (accountNumber[7] == 2)
					|| (accountNumber[7] == 3) || (accountNumber[7] == 6)) {
				setAlternative(0);
				return new Checksum09().validate(accountNumber);
			} else {
				setAlternative(1);
				return new Checksum06().validate(accountNumber);
			}
		}
	}

}
