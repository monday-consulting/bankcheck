/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10 <br/>
 * 
 * Die Kontonummer ist ggf. durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die 10. Stelle der Kontonummer ist die Prüfziffer. Die Stellen
 * 1 bis 9 der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4,
 * ff. multipliziert. Die jeweiligen Produkte werden addiert. Die Summe der
 * Produkte ist durch 11 zu dividieren. Der verbleibende Rest ist die
 * Prüfziffer. Sollte jedoch der Rest 10 ergeben, so ist die Kontonummer
 * unabhängig vom eigentlichen Berechnungsergebnis richtig, wenn die Ziffern an
 * 10. und 9. Stelle identisch sind. <br/>
 * 
 * Beispiel 1: <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: 0 0 0 0 1 0 8 4 4 3 <br/>
 * Gewichtung: 10 9 8 7 6 5 4 3 2 0+ 0+ 0+ 0+ 6+ 0+ 32+ 12+ 8 = 58 58 : 11 = 5
 * Rest 3 3 ist die Prüfziffer <br/>
 * 
 * Beispiel 2: <br/>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: 0 0 0 0 1 0 1 5 9 9 <br/>
 * Gewichtung: 10 9 8 7 6 5 4 3 2 0+ 0+ 0+ 0+ 6+ 0+ 4+ 15+ 18 = 43:11 Rest 10 <br/>
 * 
 * Testkontonummern: 0000108443, 0000107451, 0000102921, 0000102349, 0000101709,
 * 0000101599<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum35 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };

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
		if (sum % 11 == 10) {
			if (accountNumber[8] == accountNumber[9]) {
				return accountNumber[8];
			} else {
				return -1;
			}
		} else {
			return (sum % 11);
		}
	}
}
