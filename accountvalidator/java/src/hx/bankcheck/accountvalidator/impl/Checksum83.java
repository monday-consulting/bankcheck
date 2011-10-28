/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * 1. Kundenkonten <br/>
 * 
 * A. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * B. Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * C. Modulus 7, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Gemeinsame Anmerkungen für die Berechnungsverfahren <br/>
 * 
 * Die Kontonummer ist immer 10-stellig. Die für die Berechnung relevante
 * Kundennummer (K) befindet sich bei der Methode A in den Stellen 4 bis 9 der
 * Kontonummer und bei den Methoden B + C in den Stellen 5 - 9, die Prüfziffer
 * in Stelle 10 der Kontonummer.
 * 
 * Ergibt die erste Berechnung der Prüfziffer nach dem Verfahren A einen
 * Prüfzifferfehler, so sind weitere Berechnungen mit den anderen Methoden
 * vorzunehmen. <br/>
 * 
 * Kontonummern, die nach Durchführung aller 3 Berechnungsmethoden nicht zu
 * einem richtigen Ergebnis führen, sind nicht prüfbar.<br/>
 * 
 * Methode A: <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * 
 * Die Berechnung und möglichen Ergebnisse entsprechen dem Verfahren 32. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x K K K K K K P <br/>
 * Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * Testkontonummern: 0001156071, 0001156136 <br/>
 * 
 * <b>Methode B</b>:<br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die Berechnung und möglichen Ergebnisse entsprechen dem Verfahren 33.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x K K K K K P<br/>
 * Gewichtung: 6 5 4 3 2 <br/>
 * 
 * Testkontonummer: 0000156078 <br/>
 * 
 * <b>Methode C:</b> <br/>
 * 
 * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine 7, 8
 * oder 9 haben, sind ungültig. Modulus 7, Gewichtung 2, 3, 4, 5, 6 Das
 * Berechnungsverfahren entspricht Methode B. Die Summe der Produkte ist jedoch
 * durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt kein Rest, ist die
 * Prüfziffer 0.<br/>
 * 
 * Testkontonummer: 0000156071<br/>
 * 
 *<b> 2. Sachkonten</b> <br/>
 * 
 * <i>Berechnungsmethode:</i><br/>
 * 
 * A. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8 <br/>
 * 
 * Die Sachkontonummer ist immer 10-stellig. Die für die Berechnung relevante
 * Sachkontenstammnummer (S) befindet sich in den Stellen 3 bis 9 der
 * Kontonummer, wobei die 3. und 4. Stelle immer jeweils 9 sein müssen; die
 * Prüfziffer ist in Stelle 10 der Sachkontonummer. <br/>
 * 
 * Führt die Berechnung nicht zu einem richtigen Ergebnis, ist die Nummer nicht
 * prüfbar.<br/>
 * 
 * <b>Methode A:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8 <br/>
 * 
 *<i> Berechnung:</i><br/>
 * 
 * Die einzelnen Stellen der Sachkontonummern sind von rechts nach links mit den
 * Ziffern 2, 3, 4, 5, 6, 7, 8 zu multiplizieren. Die jeweiligen Produkte werden
 * addiert. <br/>
 * 
 * Die Summe ist durch 11 zu dividieren. Der verbleibende Rest wird vom Divisor
 * (11) subtrahiert. Das Ergebnis ist die Prüfziffer. <br/>
 * 
 * Verbleibt nach der Division durch die 11 kein Rest, ist die Prüfziffer "0". <br/>
 * 
 * Das Rechenergebnis "10" ist nicht verwendbar und muss auf eine Stelle
 * reduziert werden. Die rechte Stelle Null findet als Prüfziffer Verwendung.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x S S S S S S S P <br/>
 * Gewichtung: 8 7 6 5 4 3 2<br/>
 * 
 * Testkontonummer: 0099100002<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum83 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_PERSONAL_ALTERNATIVE1 = { 0, 0, 0, 7, 6,
			5, 4, 3, 2 };
	private static final int[] WEIGHTS_PERSONAL_ALTERNATIVE2 = { 0, 0, 0, 0, 6,
			5, 4, 3, 2 };
	private static final int[] WEIGHTS_PERSONAL_ALTERNATIVE3 = { 0, 0, 0, 0, 6,
			5, 4, 3, 2 };
	private static final int[] WEIGHTS_IMPERSONAL = { 0, 0, 8, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[2] == 9) && (accountNumber[3] == 9)) {
			return validateImpersonalAccoutNumber(accountNumber);
		} else {
			return validatePersonalAccountNumber(accountNumber);
		}
	}

	private boolean validateImpersonalAccoutNumber(int[] accountNumber)
			throws ValidationException {
		return accountNumber[9] == calcChecksumImpersonal(accountNumber);
	}

	private boolean validatePersonalAccountNumber(int[] accountNumber)
			throws ValidationException {
		if (new Checksum32(WEIGHTS_PERSONAL_ALTERNATIVE1)
				.validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else {
			if (new Checksum33(WEIGHTS_PERSONAL_ALTERNATIVE2)
					.validate(accountNumber)) {
				setAlternative(1);
				return true;
			} else {
				if ((accountNumber[9] == 7) || (accountNumber[9] == 8)
						|| (accountNumber[9] == 9)) {
					return false;
				} else {
					return accountNumber[9] == calcChecksum(accountNumber);
				}
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_PERSONAL_ALTERNATIVE3.length; i++) {
			sum += accountNumber[i] * WEIGHTS_PERSONAL_ALTERNATIVE3[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}

	private int calcChecksumImpersonal(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_IMPERSONAL.length; i++) {
			sum += accountNumber[i] * WEIGHTS_IMPERSONAL[i];
		}
		return ((sum % 11 == 0) || (sum % 11 == 1)) ? 0 : (11 - sum % 11);
	}

}
