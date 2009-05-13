/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Die Kontonummer ist durch linksbündiges Auffüllen mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Kontonummer ist die Prüfziffer.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1<br/>
 * 
 * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den
 * Ziffern 2, 1, 2, 1, 2, 1 multipliziert. Die Berechnung und Ergebnisse
 * entsprechen dem Verfahren 00.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: X X X X X X X X X P <br/>
 * Gewichtung: 1 2 1 2 1 2<br/>
 * 
 * Testkontonummern: richtig: 0003503398, 0001340967 <br/>
 * falsch: 0003503391, 0001340966 <br/>
 * 
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, ist eine
 * weitere Berechnung nach Variante 2 vorzunehmen: <br/>
 * 
 * <b>Variante 2: </b>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Das Berechnungsverfahren entspricht Variante 1, es ist jedoch zu beachten,
 * dass nur die Stellen 5 bis 9 in das Prüfziffernberechnungsverfahren
 * einbezogen werden. <br/>
 * 
 * Testkontonummern: richtig: 0003503391, 0001340968<br/>
 * falsch: 0003503392, 0001340966<br/>
 * 
 * Führt die Berechnung auch nach Variante 2 zu einem Prüfzifferfehler, ist die
 * Berechnung nach Variante 3 vorzunehmen:<br/>
 * 
 * <b>Variante 3 </b><br/>
 * 
 * Modulus 7, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Das Berechnungsverfahren entspricht Variante 2. Die Summe der
 * Produkt-Quersummen ist jedoch durch 7 zu dividieren. Der verbleibende Rest
 * wird vom Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt
 * nach der Division kein Rest, ist die Prüfziffer = 0 <br/>
 * 
 * Testkontonummern: richtig: 0003503392, 0001340966, 123456 <br/>
 * falsch: 121212, 987654321 <br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in
 * Methode 51 mit den gleichen Ergebnissen und Testkontonummern.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum73 extends Checksum00 {

	private static final int[] WEIGHTS_ALTERANTIVE1 = { 0, 0, 0, 1, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERANTIVE2 = { 0, 0, 0, 0, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERANTIVE3 = { 0, 0, 0, 0, 2, 1, 2, 1,
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
			setWeights(WEIGHTS_ALTERANTIVE1);
			if (accountNumber[9] == calcChecksum(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				setWeights(WEIGHTS_ALTERANTIVE2);
				if (accountNumber[9] == calcChecksum(accountNumber)) {
					setAlternative(1);
					return true;
				} else {
					setAlternative(2);
					setWeights(WEIGHTS_ALTERANTIVE3);
					return accountNumber[9] == calcChecksumAlternative3(accountNumber);
				}
			}
		}
	}

	private int calcChecksumAlternative3(int[] accountNumber) {
		int sum = 0;
		for (int i = getWeights().length - 1; i >= 0; i--) {
			sum += ChecksumUtils.qs(accountNumber[i] * getWeights()[i]);
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}

}
