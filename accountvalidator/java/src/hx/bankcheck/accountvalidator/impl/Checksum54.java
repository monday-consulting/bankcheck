/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7,2<br/>
 * 
 * Die Kontonummer ist 10-stellig, wobei die Stellen 1 u. 2 generell mit 49
 * belegt sind. Die einzelnen Stellen der Kontonummer sind von rechts nach links
 * mit den Ziffern 2, 3, 4, 5, 6, 7, 2 zu multiplizieren. Die jeweiligen
 * Produkte werden addiert. Die Summe ist durch 11 zu dividieren. Der
 * verbleibende Rest wird vom Divisor (11) subtrahiert. Das Ergebnis ist die
 * Prüfziffer. Ergibt sich als Rest 0 oder 1, ist die Prüfziffer zweistellig und
 * kann nicht verwendet werden. Die Kontonummer ist dann nicht verwendbar.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: 4 9 K K K K K K K P<br/>
 * Gewichtung: 2 7 6 5 4 3 2 <br/>
 * 
 * Testkontonummern: (49) 64137395, (49) 00010987<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum54 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 0, 2, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] != 4) && (accountNumber[1] != 9)) {
			return false;
		} else {
			return accountNumber[9] == calcChecksum(accountNumber);
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		return ((sum % 11 == 0) || (sum % 11 == 1)) ? -1 : (11 - sum % 11);
	}

}
