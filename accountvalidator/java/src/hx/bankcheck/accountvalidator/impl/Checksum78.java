/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 00<br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * 8-stellige Kontonummern sind nicht prüfbar, da diese Nummern keine Prüfziffer
 * enthalten.<br/>
 * 
 * Testkontonummern: 7581499, 9999999981<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum78 extends Checksum00 {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) == 2) {
			setException(true);
			return false;
		} else {
			return super.validate(accountNumber);
		}
	}

}
