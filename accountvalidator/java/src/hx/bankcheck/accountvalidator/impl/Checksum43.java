/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 1, 2, 3, 4, 5, 6, 7, 8, 9 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 1 bis 9 der Kontonummer werden
 * von rechts nach links mit den Faktoren 1, 2, 3, 4, 5, 6, 7, 8, 9
 * multipliziert. Die Summe der Produkte wird durch den Wert 10 dividiert. Der
 * Rest der Division wird vom Divisor subtrahiert. Die Differenz ist die
 * Prüfziffer. Ergibt die Berechnung eine Differenz von 10, lautet die
 * Prüfziffer 0. Die Stelle 10 der Kontonummer ist per Definition die
 * Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 9 8 7 6 5 4 3 2 1 <br/>
 * 
 * Testkontonummern: 6135244, 9516893476 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum43 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

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
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}

}
