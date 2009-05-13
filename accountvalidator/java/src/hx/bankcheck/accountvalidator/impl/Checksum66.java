/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 7 <br/>
 * 
 * Aufbau der 9-stelligen Kontonummer (innerhalb des zwischenbetrieblich
 * 10-stelligen Feldes) <br/>
 * 
 * Stelle 1 = gehört nicht zur Kontonummer, muss daher 0 sein <br/>
 * Stelle 2 = Stammnummer<br/>
 * Stellen 3 - 4 = Unterkontonummer, wird bei der Prüf- zifferberechnung nicht
 * berücksichtigt <br/>
 * Stellen 5 - 9 = Stammnummer <br/>
 * Stelle 10 = Prüfziffer <br/>
 * 
 * Der 9-stelligen Kontonummer wird für die Prüfzifferberechnung eine 0
 * vorangestellt. Die Prüfziffer steht in Stelle 10. Die für die Berechnung
 * relevante 6-stellige Stammnummer (Kundenummer) befindet sich in den Stellen 2
 * und 5 bis 9. Die zweistellige Unterkontonummer (Stellen 3 und 4) wird nicht
 * in das Prüfzifferberechnungsverfahren mit einbezogen und daher mit 0
 * gewichtet. Die einzelnen Stellen der Stammnummer sind von rechts nach links
 * mit den Ziffern 2, 3, 4, 5, 6, 0, 0, 7 zu multiplizieren. Die jeweiligen
 * Produkte werden addiert. Die Summe ist durch 11 zu dividieren. Bei einem
 * verbleibenden Rest von 0 ist die Prüfziffer 1. Bei einem Rest von 1 ist die
 * Prüfziffer 0. Verbleibt ein Rest von 2 bis 10, so wird dieser vom Divisor
 * (11) subtrahiert. Die Differenz ist dann die Prüfziffer. <br/>
 * 
 * <b>Zusammengefasst: </b><br/>
 * 
 * Summe dividiert durch 11 = x, Rest <br/>
 * 
 * Rest = 0 Prüfziffer = 1 <br/>
 * Rest = 1 Prüfziffer = 0 <br/>
 * Rest = 2 bis 10 <br/>
 * Prüfziffer = 11 minus Rest<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: 0 1 0 0 1 5 0 5 0 P <br/>
 * Gewichtung: 0 7 0 0 6 5 4 3 2 0 + 7 + 0 + 0 + 6 +25 + 0 +15 + 0 = 53 53 : 11
 * = 4, Rest 9, 11-9=2, <br/>
 * Prüfziffer = 2 <br/>
 * Die vollständige Kontonummer lautet: 100150502<br/>
 * 
 * Testkontonummern: 100154508, 101154508, 100154516, 101154516<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum66 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 7, 0, 0, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] != 0) {
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
		return ((sum % 11 == 0) ? 1 : ((sum % 11 == 1) ? 0 : (11 - sum % 11)));
	}

}
