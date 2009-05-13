/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 9, 10, 5, 8, 4, 2. <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die für die Berechnung relevanten Stellen der
 * Kontonummer befinden sich in den Stellen 1 bis 6 und werden von links nach
 * rechts mit den Ziffern 9, 10, 5, 8, 4, 2 multipliziert. Die weitere
 * Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Prüfziffer
 * befindet sich in Stelle 7 der Kontonummer.<br/>
 * 
 * Testkontonummern: 1206473010, 5016511020<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum64 extends Checksum06 {

	private static final int[] WEIGHTS = { 9, 10, 5, 8, 4, 2, 0, 0, 0 };

	public Checksum64() {
		super(WEIGHTS);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[6] == calcChecksum(accountNumber);
	}
}
