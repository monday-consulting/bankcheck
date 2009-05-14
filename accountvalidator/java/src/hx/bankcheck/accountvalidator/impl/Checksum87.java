/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Der zur Prüfzifferberechnung heranzuziehende Teil befindet sich
 * bei der Methode A in den Stellen 4 bis 9 der Kontonummer und bei den Methoden
 * B und C in Stellen 5 - 9, die Prüfziffer in Stelle 10 der Kontonummer. <br/>
 * 
 * Ergibt die erste Berechnung der Prüfziffer nach der Methode A einen
 * Prüfzifferfehler, so sind weitere Berechnungen mit den anderen Methoden
 * vorzunehmen. <br/>
 * 
 * <b>Ausnahme:</b> <br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in
 * Methode 51 mit den gleichen Ergebnissen und Testkontonummern. <br/>
 * 
 * <b>Methode A:</b> <br/>
 * 
 * Für die Berechnung werden folgende Felder verwendet: <br/>
 * 
 * i = Hilfsvariable (Laufvariable) <br/>
 * C2 = Hilfsvariable (Kennung, ob gerade oder un-gerade Stelle bearbeitet wird)<br/>
 * D2 = Hilfsvariable <br/>
 * A5 = Hilfsvariable (Summenfeld), kann negativ werden <br/>
 * P = Hilfsvariable (zur Zwischenspeicherung der Prüfziffer)<br/>
 * KONTO = 10-stelliges Kontonummernfeld mit KONTO (i) = in Bearbeitung
 * befindliche Stelle; der Wert an jeder Stelle kann zweistellig werden <br/>
 * TAB1; TAB2 = Tabellen mit Prüfziffern:<br/>
 * 
 * Tabelle TAB1<br/>
 * Stelle | Inhalt<br/>
 * 0 --> 0<br/>
 * 1 --> 4<br/>
 * 2 --> 3<br/>
 * 3 --> 2<br/>
 * 4 --> 6<br/>
 * 
 * Tabelle TAB2 <br/>
 * Stelle | Inhalt<br/>
 * 0 --> 7<br/>
 * 1 --> 1<br/>
 * 2 --> 5<br/>
 * 3 --> 9<br/>
 * 4 --> 8<br/>
 * 
 * Führt die Berechnung nach Methode A zu einem Prüfzifferfehler, ist die
 * Berechnung nach Methode B vorzunehmen.<br/>
 * 
 * <b>Methode B:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die für die Berechnung relevanten Stellen werden von rechts nach links mit
 * den Ziffern 2, 3, 4, 5, 6 multipliziert. Die weitere Berechnung und die
 * möglichen Ergebnisse entsprechen dem Verfahren 33. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 6 5 4 3 2 <br/>
 * 
 * Führt die Berechnung nach Methode B wiederum zu einem Prüfzifferfehlen, ist
 * eine weitere Berechnung nach Methode C vorzunehmen.<br/>
 * 
 * <b>Methode C:</b><br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die Stellen 5 bis 9 der Kontonummer werden von rechts nach links mit den
 * Gewichten multipliziert. Die jeweiligen Produkte werden addiert. Die Summe
 * ist durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach der Division
 * kein Rest, ist die Prüfziffer = 0. <br/>
 * 
 * Testkontonummern Methode B und C: 0000100005, 0000393814, 0000950360,
 * 3199500501<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum87 extends AbstractChecksumValidator {

	private static final int[] TAB1 = { 0, 4, 3, 2, 6 };
	private static final int[] TAB2 = { 7, 1, 5, 9, 8 };

	private static final int[] WEIGHTS_ALTERNATIVE_2 = { 0, 0, 0, 0, 6, 5, 4,
			3, 2 };
	private static final int[] WEIGHTS_ALTERNATIVE_3 = { 0, 0, 0, 0, 6, 5, 4,
			3, 2 };

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
			if (validateMethodA(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				if (new Checksum33(WEIGHTS_ALTERNATIVE_2)
						.validate(accountNumber)) {
					setAlternative(1);
					return true;
				} else {
					setAlternative(2);
					return accountNumber[9] == calcChecksumAlternative3(accountNumber);
				}
			}
		}
	}

	private boolean validateMethodA(int[] accountNumber) {
		int[] konto = accountNumber.clone();
		int i, c2, d2, a5, p;

		i = 4;
		while ((konto[i - 1] == 0)&&(i<10)) {
			i++;
		}
		c2 = i % 2;
		d2 = 0;
		a5 = 0;
		while (i < 10) {
			switch (konto[i - 1]) {
			case 0:
				konto[i - 1] = 5;
				break;
			case 1:
				konto[i - 1] = 6;
				break;
			case 5:
				konto[i - 1] = 10;
				break;
			case 6:
				konto[i - 1] = 1;
				break;
			default:
				break;
			}
			if (c2 == d2) {
				if (konto[i - 1] > 5) {
					if ((c2 == 0) && (d2 == 0)) {
						c2 = 1;
						d2 = 1;
						a5 = a5 + 6 - (konto[i - 1] - 6);
					} else {
						c2 = 0;
						d2 = 0;
						a5 = a5 + konto[i - 1];
					}
				} else {
					if ((c2 == 0) && (d2 == 0)) {
						c2 = 1;
						a5 = a5 + konto[i - 1];
					} else {
						c2 = 0;
						a5 = a5 + konto[i - 1];
					}
				}
			} else {
				if (konto[i - 1] > 5) {
					if (c2 == 0) {
						c2 = 1;
						d2 = 0;
						a5 = a5 - 6 + (konto[i - 1] - 6);
					} else {
						c2 = 0;
						d2 = 1;
						a5 -= konto[i - 1];
					}
				} else {
					if (c2 == 0) {
						c2 = 1;
						a5 = a5 - konto[i - 1];
					} else {
						c2 = 0;
						a5 = a5 - konto[i - 1];
					}
				}
			}
			i++;
		}

		while ((a5 < 0) || (a5 > 4)) {
			a5 += (a5 > 4) ? -5 : 5;
		}

		p = (d2 == 0) ? TAB1[a5] : TAB2[a5];

		if (p == konto[9]) {
			return true;
		} else {
			p += (p > 4) ? -5 : 5;
			if (p == konto[9]) {
				return true;
			}
		}
		return false;
	}

	private int calcChecksumAlternative3(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE_3.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE_3[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}
}
