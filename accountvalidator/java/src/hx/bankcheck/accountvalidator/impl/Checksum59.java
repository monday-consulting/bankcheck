/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 00; es ist jedoch zu beachten, dass
 * Kontonummern, die kleiner als 9-stellig sind, nicht in die
 * Prüfzifferberechnung einbezogen und als richtig behandelt werden.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum59 extends Checksum00 {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) > 1) {
			setException(true);
			return true;
		} else {
			return super.validate(accountNumber);
		}
	}

}
