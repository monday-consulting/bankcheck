/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die zweistellige Unterkontonummer (Stellen 9
 * und 10) wird nicht in das Berechnungsverfahren einbezogen. Die für die
 * Berechnung relevanten Stellen 1 bis 7 werden von rechts nach links mit den
 * Ziffern 2, 3, 4, 5, 6, 7, 8 multipliziert. Die 8. Stelle ist die Prüfziffer.
 * Die Berechnung und Ergebnisse entsprechen dem Verfahren 06. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x P x x <br/>
 * Gewichtung: 8 7 6 5 4 3 2 <br/>
 * 
 * Wird als Rest eine 0 oder eine 1 ermittelt, so lautet die Prüfziffer 0. <br/>
 * 
 * Testkontonummern: 19999000, 9130000201<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum28 extends Checksum06 {

	private static final int[] WEIGHTS = { 8, 7, 6, 5, 4, 3, 2, 0, 0, 0 };

	public Checksum28() {
		super(WEIGHTS);
	}

	public Checksum28(int[] weights) {
		super(weights);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[7] == calcChecksum(accountNumber);
	}
}
