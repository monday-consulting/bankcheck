/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 6, 5, 4, 3, 2, 1<br/>
 * 
 * Die Kontonummer ist immer 10-stellig. Die Stellen 2 bis 7 sind von links nach
 * rechts mit den Ziffern 6, 5, 4, 3, 2, 1 zu multiplizieren. Die Summe ist
 * durch 11 zu dividieren. Der verbleibende Rest wird vom Divisor (11)
 * subtrahiert. Das Ergebnis ist die Prüfziffer.<br/>
 * 
 * <b>Ausnahmen:</b> <br/>
 * 
 * Verbleibt nach der Division durch 11 kein Rest, ist die Prüfziffer 0. Ergibt
 * sich als Rest 1, entsteht bei der Subtraktion 11 - 1 = 10; die Zehnerstelle
 * (1) ist dann die Prüfziffer.<br/>
 * 
 * Darstellung der Kontonummer: S G G K K K K U U P <br/>
 * S = Sachgebiet <br/>
 * G = Geschäftsstelle <br/>
 * K = Kundennummer <br/>
 * U = Unternummer <br/>
 * P = Prüfziffer <br/>
 * 
 * <b>Prüfzifferberechnung: </b><br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: 7 1 0 1 2 3 4 0 0 P <br/>
 * Gewichtung: 6 5 4 3 2 1 6 + 0 + 4 + 6 + 6 + 4 = 26 71 26 : 11 = 2, Rest 4 11
 * - 4 = 7 <br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 7 und die vollständige Kontonummer
 * lautet: 7 1 0 1 2 3 4 0 0 7<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum71 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 6, 5, 4, 3, 2, 1, 0, 0 };

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
		return ((sum % 11 == 0) ? 0 : ((sum % 11 == 1) ? 1 : (11 - sum % 11)));
	}
}
