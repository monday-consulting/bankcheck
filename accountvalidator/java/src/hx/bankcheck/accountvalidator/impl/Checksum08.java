package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)
 * Die Berechnung erfolgt wie bei Verfahren 00, jedoch erst ab
 * der Kontonummer 60 000.
 * @author tma
 *
 */
public class Checksum08 extends Checksum00 {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] + accountNumber[1] + accountNumber[2] + accountNumber[3] + accountNumber[4] == 0 && accountNumber[5] < 6) {
			return false;
		} else {
			return super.validate(accountNumber);
		}
	}
	
}
