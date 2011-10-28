/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 0, 0, 0, 0, 1, 2, 1, 2 <br/>
 * 
 * Die letzte Stelle ist per Definition die Prüfziffer. Die einzelnen Stellen
 * der Kontonummer sind ab der ersten Stelle von links nach rechts mit den
 * Ziffern 2, 0, 0, 0, 0, 1, 2, 1, 2 zu multiplizieren. Die jeweiligen Produkte
 * werden addiert (ohne Quersummenbildung). Die weitere Berechnung erfolgt wie
 * bei Verfahren 00.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 2 0 0 0 0 1 2 1 2<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum30 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 2, 0, 0, 0, 0, 1, 2, 1, 2 };

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[9] == calcChecksum(accountNumber);
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		return (10 - (sum % 10)) % 10;
	}

}
