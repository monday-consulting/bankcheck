/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 1, 2, 3, 4, 5 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die für die Berechnung relevanten Stellen 6
 * bis 10 werden von rechts nach links mit den Ziffern 1, 2, 3, 4, 5
 * multipliziert. Die Produkte werden addiert. Die Summe ist durch 11 zu
 * dividieren. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x 4 7 6 7 8 <br/>
 * Gewichtung: 5 4 3 2 1 20 + 28 + 18 + 14 + 8 = 88 88 : 11 = 8 Rest 0<br/>
 * 
 * Verbleibt nach der Division der Summe durch 11 ein Rest, ist folgende neue
 * Berechnung durchzuführen: <br/>
 * 
 * Modulus 11, Gewichtung 5, 4, 3, 4, 5 <br/>
 * 
 * <b>Beispiel:</b><br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x 4 7 6 7 1<br/>
 * Gewichtung: 5 4 3 4 5 20 + 28 + 18 + 28 + 5 = 99 99 : 11 = 9 Rest 0<br/>
 * 
 * Ergibt sich bei der erneuten Berechnung wiederum ein Rest, dann ist die
 * Kontonummer falsch.
 * 
 * Erläuterung: x = weitere Ziffern der Kontonummer, die jedoch nicht in die
 * Berechnung einbezogen werden. <br/>
 * 
 * Testkontonummern: 10338, 13844, 65354, 69258<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum77 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS1 = { 0, 0, 0, 0, 0, 5, 4, 3, 2, 1 };
	private static final int[] WEIGHTS2 = { 0, 0, 0, 0, 0, 5, 4, 3, 4, 5 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return ((calcChecksum(accountNumber, WEIGHTS1) == 0) || (calcChecksum(
				accountNumber, WEIGHTS2) == 0));
	}

	private int calcChecksum(int[] accountNumber, int[] weights) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += accountNumber[i] * weights[i];
		}
		return sum % 11;
	}

}
