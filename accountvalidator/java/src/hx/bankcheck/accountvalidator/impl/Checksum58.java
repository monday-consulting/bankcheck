/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum58 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 0, 0, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) > 4) {
			return false;
		} else {
			return calcChecksum(accountNumber)==accountNumber[9];
		}
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		return (sum % 11 == 1) ? -1 : ((sum % 11 == 0) ? 0 : 11 - (sum % 11));
	}

}
