/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2 ff.<br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Berechnung und Ergebnisse entsprechen dem
 * Verfahren 00. Es ist jedoch zu beachten, dass die Berechnung vom Wert der 1.
 * Stelle der Kontonummer abhängig ist. .<br/>
 * 
 * <b>Variante 1</b> <br/>
 * 
 * Die 1. Stelle der Kontonummer hat die Ziffer 3, 4, 5, 6, 7 oder 8 .<br/>
 * 
 * Die für die Berechnung relevanten Stellen der Kontonummer befinden sich in
 * den Stellen 1 bis 9. Die 10. Stelle ist per Definition die Prüfziffer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: 3 2 3 0 0 1 2 6 8 8<br/>
 * Gewichtung: 2 1 2 1 2 1 2 1 2<br/>
 * 
 * <b>Variante 2</b><br/>
 * 
 * Die 1. Stelle der Kontonummer hat die Ziffer 1, 2 oder 9 <br/>
 * 
 * Die für die Berechnung relevanten Stellen der Kontonummer befinden sich in
 * den Stellen 1 bis 8. Die 9. Stelle ist die Prüfziffer der 10-stelligen
 * Kontonummer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: 9 0 1 1 2 0 0 1 4 0<br/>
 * Gewichtung: 1 2 1 2 1 2 1 2 <br/>
 * 
 * Kontonummern, die in der 1. Stelle eine 0 haben, wurden nicht vergeben und
 * gelten deshalb als falsch.<br/>
 * 
 * Testkontonummern: 3230012688, 4230028872, 5440001898, 6330001063, 7000149349,
 * 8000003577, 1550167850, 9011200140
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum79 extends Checksum00 {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 2, 1, 2, 1, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 1, 2, 1, 2, 1, 2, 1, 2,
			0 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] == 0) {
			return false;
		} else {
			if ((accountNumber[0] == 3) || (accountNumber[0] == 4)
					|| (accountNumber[0] == 5) || (accountNumber[0] == 6)
					|| (accountNumber[0] == 7) || (accountNumber[0] == 8)) {
				setAlternative(0);
				setWeights(WEIGHTS_ALTERNATIVE1);
				return super.validate(accountNumber);
			} else {
				setAlternative(1);
				setWeights(WEIGHTS_ALTERNATIVE2);
				return accountNumber[8]==calcChecksum(accountNumber);
			}
		}
	}
}
