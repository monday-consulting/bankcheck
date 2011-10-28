/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 9, 8, 7, 6, 5, 4, 3, 2, 1<br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 1 bis 9 der Kontonummer sind von
 * rechts nach links mit den Ziffern 9, 8, 7, 6, 5, 4, 3, 2, 1 zu
 * multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe ist durch
 * 11 zu dividieren. Der verbleibende Rest ist die Prüfziffer. Verbleibt nach
 * der Division durch 11 kein Rest, ist die Prüfziffer 0. Ergibt sich ein Rest
 * 10, ist die Kontonummer falsch. Die Prüfziffer befindet sich in der 10.
 * Stelle der Kontonummer. <br/>
 * 
 * <b>Beispiel: </b><br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10<br/>
 * Kontonr.: 0 2 6 3 1 6 0 1 6 5 <br/>
 * Gewichtung: 1 2 3 4 5 6 7 8 9 0+ 4+ 18+ 12+ 5+ 36+ 0+ 8+ 54 = 137 137 : 11 =
 * 12 Rest 5 5 = Prüfziffer <br/>
 * 
 * Testkontonummern: 1000000524, 1000000583<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum31 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[9] == calcChecksum(accountNumber);
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		return (sum % 11 == 1) ? -1 : (sum % 11);
	}
}
