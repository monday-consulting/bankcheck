/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 3 bis 7 der Kontonummer werden
 * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle
 * 8 der Kontonummer ist per Definition die Prüfziffer.
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x P x x <br/>
 * Gewichtung: 6 5 4 3 2<br/>
 * 
 * Testkontonummern: 0235468612, 0837890901, 1041447600<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum46 extends Checksum06 {

	private static final int[] WEIGHTS = { 0, 0, 6, 5, 4, 3, 2, 0, 0, 0 };

	public Checksum46() {
		super(WEIGHTS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[7] == super.calcChecksum(accountNumber);
	}

}
