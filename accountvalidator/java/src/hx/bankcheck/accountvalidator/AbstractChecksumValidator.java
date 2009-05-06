/**
 * 
 */
package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Abstract class for algorithms needing no bank number for validation.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public abstract class AbstractChecksumValidator implements ChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[],
	 * int[])
	 */
	@Override
	public boolean validate(int[] accountNumber, int[] bankNumber)
			throws ValidationException {
		return validate(accountNumber);
	}

	/**
	 * Checks an account number for correctness
	 * 
	 * @param accountNumber
	 *            The 10-Digit accountNumber, right aligned.
	 * @return
	 */
	public abstract boolean validate(int[] accountNumber)
			throws ValidationException;
}
