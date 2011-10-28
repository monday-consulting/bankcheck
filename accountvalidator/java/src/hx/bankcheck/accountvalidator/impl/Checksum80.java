/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * <b>Methode A </b><br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die Berechnung und die möglichen Ergebnisse entsprechen dem
 * Verfahren 00; es ist jedoch zu beachten, dass nur die Stellen 5 bis 9 in das
 * Prüfzifferberechnungsverfahren einbezogen werden. Die Stelle 10 der
 * Kontonummer ist die Prüfziffer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 2 1 2 1 2<br/>
 * Testkontonummer: 340968<br/>
 * 
 * Führt die Berechnung nach Methode A zu einem Prüfziffe-fehler, ist die
 * Berechnung nach Methode B vorzunehmen.<br/>
 * 
 * <b>Methode B</b><br/>
 * 
 * Modulus 7, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Das Berechnungsverfahren entspricht Methode A. Die Summe der
 * Produkt-Quersummen ist jedoch durch 7 zu dividieren. Der verbleibende Rest
 * wird vom Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt
 * nach der Division kein Rest, ist die Prüfziffer = 0<br/>
 * 
 * Testkontonummer: 340966<br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in
 * Methode 51 mit den gleichen Ergebnissen und Testkontonummern.<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum80 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 0, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 0, 2, 1, 2, 1,
			2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[2]==9) {
			setException(true);
			return new Checksum51().validate(accountNumber);
		} else {
			if(new Checksum00(WEIGHTS_ALTERNATIVE1).validate(accountNumber)){
				setAlternative(0);
				return true;
			}else{
				setAlternative(1);
				return accountNumber[9]==calcChecksum(accountNumber);
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * WEIGHTS_ALTERNATIVE2[i]);
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}

}
