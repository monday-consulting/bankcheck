package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1 <br />
 * 
 * Die Kontonummer ist 10-stellig.
 * 
 * Die Berechnung erfolgt wie bei Verfahren 01.<br />
 * 
 * Es ist jedoch zu beachten, dass nur die Stellen 4 bis 9 in das
 * Prüfzifferberechnungsverfahren einbezogen werden. <br />
 * 
 * Die Stelle 10 der Kontonummer ist die Prüfziffer. <br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br />
 * Kontonr.: x x x x x x x x x P<br />
 * Gewichtung: 1 7 3 1 7 3<br />
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum92 implements ChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 1, 7, 3, 1, 7, 3 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i + 5] + WEIGHTS[i]);
		}
		return ((10 - sum % 10) == 10) ? 0 : (10 - sum % 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber.length != 10) {
			return false;
		} else {
			return accountNumber[9] == calcChecksum(accountNumber);
		}
	}

}
