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
public class Checksum85 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE3 = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_EXCEPTIONS = { 0, 0, 8, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[2] == 9) && (accountNumber[3] == 9)) {
			setException(true);
			return new Checksum02(WEIGHTS_EXCEPTIONS).validate(accountNumber);
		} else {
			if (new Checksum06(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				if (new Checksum33(WEIGHTS_ALTERNATIVE2)
						.validate(accountNumber)) {
					setAlternative(1);
					return true;
				} else {
					if ((accountNumber[9] == 7) || (accountNumber[9] == 8)
							|| (accountNumber[9] == 9)) {
						return false;
					} else {
						setAlternative(2);
						return (accountNumber[9] == calcChecksumAlternative3(accountNumber));
					}
				}
			}
		}
	}

	private int calcChecksumAlternative3(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE3.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE3[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - (sum % 7));
	}

}
