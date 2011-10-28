/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1<br/>
 * 
 * <b>Methode A</b><br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die Berechnung und die möglichen Ergebnisse entsprechen dem
 * Verfahren 00; es ist jedoch zu beachten, dass nur die Stellen 4 bis 9 in das
 * Prüfzifferberechnungsverfahren einbezogen werden. Die Stelle 10 der
 * Kontonummer ist die Prüfziffer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 1 2 1 2 1 2<br/>
 * Testkontonummern: 340968, 1001171, 1009588<br/>
 * 
 * Führt die Berechnung nach Methode A zu einem Prüfziffer-fehler, so ist die
 * Berechnung nach Methode B vorzunehmen.<br/>
 * 
 * <b>Methode B</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die Stellen 4 bis 9 der Kontonummer werden von rechts nach
 * links mit den Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die weitere Berechnung
 * und die möglichen Ergebnisse entsprechen dem Verfahren 32. Die Stelle 10 ist
 * die Prüfziffer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * Testkontonummern: 123897, 340960<br/>
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
public class Checksum86 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 1, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 7, 6, 5, 4, 3,
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
			if (new Checksum00(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				setAlternative(1);
				return new Checksum32(WEIGHTS_ALTERNATIVE2)
						.validate(accountNumber);
			}
		}
	}

}
