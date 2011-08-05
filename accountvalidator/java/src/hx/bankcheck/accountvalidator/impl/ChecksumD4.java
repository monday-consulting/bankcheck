/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist 10-stellig, ggf. ist die Kontonummer für die
 * Prüfziffernberechnung durch linksbündiges Auffüllung mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Konto-nummer ist die Prüfziffer.<br/>
 * <br/>
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Werte 0, 1, 2, 6, 7 oder 8 beinhalten, sind falsch. Kontonummern,
 * die an der 1. Stelle von links der 10-stelligen Kontonummer einen der Werte
 * 3, 4, 5 oder 9 beinhalten, sind wie folgt zu prüfen:<br/>
 * <br/>
 * Für die Berechnung der Prüfziffer werden die Stellen 1 bis 9 der Kontonummer
 * von links verwendet. Diese Stellen sind links um die Zahl (Konstante)
 * „428259“ zu ergänzen.<br/>
 * <br/>
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * <br/>
 * Testkontonummern (richtig): 3000005012, 4143406984, 5926485111, 9002364588<br/>
 * <br/>
 * Testkontonummern (falsch): 1000062025, 0006003027, 8003306026, 9916524534,
 * 5212744564, 3000255397
 * 
 * @author Tobias Mayer (backcheck@tobiasm.de)
 * @version 1.0
 * 
 */
public class ChecksumD4 extends Checksum00 {

	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
			2, 1, 2 };

	public ChecksumD4() {
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
		int[] mergedAccountNumber = new int[15];
		if (accountNumber[0] == 0) {
			return false;
		} else {
			mergedAccountNumber[0] = 4;
			mergedAccountNumber[1] = 2;
			mergedAccountNumber[2] = 8;
			mergedAccountNumber[3] = 2;
			mergedAccountNumber[4] = 5;
			mergedAccountNumber[5] = 9;
		}
		for (int i = 0; i < accountNumber.length - 1; i++) {
			mergedAccountNumber[i + 6] = accountNumber[i];
		}
		return (accountNumber[9] == calcChecksum(mergedAccountNumber));
	}

}
