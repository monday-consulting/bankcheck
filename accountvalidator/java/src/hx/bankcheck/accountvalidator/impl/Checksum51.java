package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist durch linksbündige Nullenauffüllung immer 10-stellig
 * darzustellen. Die für die Berechnung relevante Kundennummer (K) befindet sich
 * bei der Methode A in den Stellen 4 bis 9 der Kontonummer und bei den Methoden
 * B + C in den Stellen 5 bis 9, die Prüfziffer in Stelle 10 der Kontonummer.<br/>
 * 
 * 
 * <b>Methode A: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x K K K K K K P <br/>
 * Gewichtung: 7 6 5 4 3 2 <br/>
 * 
 * Testkontonummern: 0001156071, 0001156136<br/>
 * 
 * Ergibt die Berechnung der Prüfziffer nach der Methode A einen
 * Prüfzifferfehler, ist eine weitere Berechnung mit der Methode B vorzunehmen. <br/>
 * 
 * <b>Methode B:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 33.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x K K K K K P<br/>
 * Gewichtung: 6 5 4 3 2<br/>
 * 
 * Testkontonummer: 0000156078 <br/>
 * 
 * Ergibt auch die Berechnung der Prüfziffer nach Methode B einen
 * Prüfzifferfehler, ist eine weitere Berechnung mit der Methode C vorzunehmen.
 * 
 * <b>Methode C: </b><br/>
 * 
 * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine 7, 8
 * oder 9 haben, sind ungültig.<br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Das Berechnungsverfahren entspricht Methode B. Die Summe der Produkte ist
 * jedoch durch 7 zu dividieren. Der verbleibendeRest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt kein Rest, ist die
 * Prüfziffer 0. <br/>
 * 
 * Testkontonummer: 0000156071<br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung wie folgt:<br/>
 * 
 * <b>Variante 1 zur Ausnahme </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8
 * 
 * Die für die Berechnung relevanten Stellen 3 bis 9 werden von rechts nach
 * links mit den Ziffern 2, 3, 4, 5, 6, 7, 8 multipliziert. Die Produkte werden
 * addiert. Die Summe ist durch 11 zu dividieren. Der verbleibende Rest wird vom
 * Divisor (11) subtrahiert. Das Ergebnis ist die Prüfziffer. Ergibt sich als
 * Rest 1 oder 0, ist die Prüfziffer 0.
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)<br/>
 * Kontonr.; x x 9 x x x x x x P <br/>
 * Gewichtung: 8 7 6 5 4 3 2 <br/>
 * 
 * Testkontonummern (richtig): 0199100002, 0099100010, 2599100002<br/>
 * Testkontonummern (falsch): 0199100004, 2599100003, 0099345678
 * 
 * Führt die Variante 1 zur Ausnahme zu einem Prüfzifferfehler, ist eine weitere
 * Berechnung nach der Variante 2 vorzunehmen.<br/>
 * 
 * <b>Variante 2 zur Ausnahme </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10<br/>
 * 
 * Berechnung und Ergebnisse entsprechen der Variante 1 zur Ausnahme.<br/>
 * 
 * Testkontonummern (richtig): 0199100004, 2599100003, 3199204090<br/>
 * Testkontonummern (falsch): 0099345678, 0099100110, 0199100040<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum51 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERNATIVEA = { 0, 0, 0, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVEB = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVEC = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_EXCEPTION_ALTERNATIVE1 = { 0, 0, 8, 7,
			6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS_EXCEPTION_ALTERNATIVE2 = { 10, 9, 8, 7,
			6, 5, 4, 3, 2 };

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (accountNumber[2] == 9) {
			setException(true);
			if (new Checksum06(WEIGHTS_EXCEPTION_ALTERNATIVE1)
					.validate(accountNumber)) {
				return true;
			} else {
				setAlternative(1);
				return new Checksum06(WEIGHTS_EXCEPTION_ALTERNATIVE2)
						.validate(accountNumber);
			}
		} else {
			if (new Checksum06(WEIGHTS_ALTERNATIVEA).validate(accountNumber)) {
				return true;
			} else {
				setAlternative(1);
				if (new Checksum33(WEIGHTS_ALTERNATIVEB)
						.validate(accountNumber)) {
					return true;
				} else {
					setAlternative(2);
					return accountNumber[9] == calcChecksumAlternativeC(accountNumber);
				}
			}
		}
	}

	private int calcChecksumAlternativeC(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVEC.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVEC[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);

	}

}
