/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 3 bis 8 der Kontonummer werden
 * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle
 * 9 der Kontonummer ist per Definition die Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x P x <br/>
 * Gewichtung: 7 6 5 4 3 2
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum48 extends Checksum06 {

	private static final int[] WEIGHTS = { 0, 0, 7, 6, 5, 4, 3, 2, 0, 0 };

	public Checksum48() {
		super(WEIGHTS);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[8] == super.calcChecksum(accountNumber);
	}

}
