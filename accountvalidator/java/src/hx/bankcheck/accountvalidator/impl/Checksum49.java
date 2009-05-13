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
public class Checksum49 extends AbstractChecksumValidator {

	/* (non-Javadoc)
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(new Checksum00().validate(accountNumber)){
			setAlternative(0);
			return true;
		}else{
			setAlternative(1);
			return new Checksum01().validate(accountNumber);
		}
	}

}
