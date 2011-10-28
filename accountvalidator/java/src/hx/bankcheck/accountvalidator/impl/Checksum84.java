/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * <b>Methode A </b><br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. Die für die
 * Berechnung relevanten Stellen werden von rechts nach links mit den Ziffern 2,
 * 3, 4, 5, 6 multipliziert. Die weitere Berechnung und die möglichen Ergebnisse
 * entsprechen dem Verfahren 33.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 6 5 4 3 2 <br/>
 * 
 * Führt die Berechnung nach Methode A zu einem Prüfziffer-fehler, ist die
 * Berechnung nach Methode B vorzunehmen.
 * 
 * <b>Methode B </b><br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Stellen 5 bis 9 der Kontonummer werden von rechts nach links mit den
 * Gewichten multipliziert. Die jeweiligen Produkte werden addiert. Die Summe
 * ist durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach der Division
 * kein Rest, ist die Prüfziffer = 0. <br/>
 * 
 * Testkontonummern: 100005, 393814, 950360<br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in
 * Methode 51 mit den gleichen Ergebnissen und Testkontonummern.<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum84 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[2] == 9) {
			setException(true);
			return new Checksum51().validate(accountNumber);
		} else {
			if (new Checksum33(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				setAlternative(1);
				return accountNumber[9] == calcChecksum(accountNumber);
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE2[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}
}
