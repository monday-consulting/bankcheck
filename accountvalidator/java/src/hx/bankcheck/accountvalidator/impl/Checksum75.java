/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2<br/>
 * 
 * Die Kontonummer (6-, 7- oder 9-stellig) ist durch linksbündige
 * Nullenauffüllung 10-stellig darzustellen.<br/>
 * 
 * Die für die Berechnung relevante 5-stellige Stammnummer (S) wird von links
 * nach rechts mit den Ziffern 2, 1, 2, 1, 2 multipliziert.<br/>
 * 
 * Die weitere Berechnung und die Ergebnisse entsprechen dem Verfahren 00.<br/>
 * 
 * Zusammensetzung der Kontonummer:<br/>
 * 
 * S = Stammnummer <br/>
 * X = Weitere Ziffern der Kontonummer, die jedoch nicht in die
 * Prüfzifferberechnung mit einbezogen werden<br/>
 * P = Prüfziffer <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * 6stell. Kontonr.: 0 0 0 0 S S S S S P <br/>
 * 7stell. Kontonr.: 0 0 0 X S S S S S P <br/>
 * 9stell. Kontonr.: 0 9 S S S S S P X X <br/>
 * 10stell. Kontonr.: 0 S S S S S P X X X <br/>
 * 
 * Anmerkungen: <br/>
 * 
 * Bei 6- und 7-stelligen Kontonummern befindet sich die für die Berechnung
 * relevante Stammnummer in den Stellen 5 bis 9, die Prüfziffer in Stelle 10 der
 * Kontonummer.<br/>
 * 
 * Bei 9-stelligen Kontonummern befindet sich die für die Berechnung relevante
 * Stammnummer in den Stellen 2 bis 6, die Prüfziffer in der 7. Stelle der
 * Kontonummer. Ist die erste Stelle der 9-stelligen Kontonummer = 9 (2. Stelle
 * der »gedachten« Kontonummer), so befindet sich die für die Berechnung
 * relevante Stammnummer in den Stellen 3 bis 7, die Prüfziffer in der 8. Stelle
 * der Kontonummer.<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum75 extends Checksum00 {

	private static final int[] WEIGHTS_6_DIGITS = { 0, 0, 0, 0, 2, 1, 2, 1, 2,
			0 };
	private static final int[] WEIGHTS_7_DIGITS = { 0, 0, 0, 0, 2, 1, 2, 1, 2,
			0 };
	private static final int[] WEIGHTS_9_DIGITS = { 0, 0, 2, 1, 2, 1, 2, 0, 0,
			0 };
	private static final int[] WEIGHTS_10_DIGITS = { 0, 2, 1, 2, 1, 2, 0, 0, 0,
			0 };
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int leadingNeutralDigits = ChecksumUtils
				.countNeutralLeadingDigits(accountNumber);
		switch (leadingNeutralDigits) {
		case 4:
			this.setWeights(WEIGHTS_6_DIGITS);
			return accountNumber[9] == calcChecksum(accountNumber);
		case 3:
			this.setWeights(WEIGHTS_7_DIGITS);
			return accountNumber[9] == calcChecksum(accountNumber);
		case 1:
			this.setWeights(WEIGHTS_9_DIGITS);
			return accountNumber[7] == calcChecksum(accountNumber);
		case 0:
			this.setWeights(WEIGHTS_10_DIGITS);
			return accountNumber[6] == calcChecksum(accountNumber);
		default:
			return false;
		}
	}

	/**
	 * Needed by Checksum C5 for avoiding multiple implementation of the same
	 * algorithm.
	 * 
	 * @param accountNumber
	 *            The account number
	 * @param checksumDigitPosition
	 *            The Position the calculated checksum is compared to.
	 * @return
	 */
	protected boolean validate(int[] accountNumber, int checksumDigitPosition, int[] weights) {
		setWeights(weights);
		return accountNumber[checksumDigitPosition] == calcChecksum(accountNumber);
	}

}
