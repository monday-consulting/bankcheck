/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.AccountNumberNotTestableException;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum45 extends Checksum00 {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] == 0) || (accountNumber[4] == 1)) {
			throw new AccountNumberNotTestableException();
		} else {
			return super.validate(accountNumber);
		}
	}
}
