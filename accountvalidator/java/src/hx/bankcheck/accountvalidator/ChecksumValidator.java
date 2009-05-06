package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

public interface ChecksumValidator {

	/**
	 * Checks an account number for correctness
	 * @param accountNumber The 10-Digit accountNumber, right aligned.
	 * @param bankNumber The 8-Digit bankNumber, right aligned.
	 * @return
	 */
	public boolean validate(int[] accountNumber, int[] bankNumber) throws ValidationException;
	
}
