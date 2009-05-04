/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4 <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 06.<br/>
 * 
 * <b>Ausnahmen:</b><br/>
 * 
 * Kontonr.:0396000000 bis 0499999999<br/>
 * Für diese Kontonummern ist keine Prüfzifferberechnung möglich. Sie sind als
 * richtig anzusehen.
 * 
 * Testkontonummern: 0068007003, 0847321750
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum99 extends Checksum06 {

	private static final int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	private boolean exception=false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		long accountNumberAsLong = ChecksumUtils.parseLong(accountNumber);
		if ((accountNumberAsLong >= Long.parseLong("0396000000"))
				&& (accountNumberAsLong <= Long.parseLong("0396000000"))) {
			exception=true;
			return true;
		} else {
			return new Checksum06(WEIGHTS).validate(accountNumber);
		}
	}

	/**
	 * @return the exception
	 */
	public boolean isException() {
		return exception;
	}
}
