package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Die Stellen 1 bis 9 der Kontonummer sind von rechts nach links mit den
 * Gewichten zu multiplizieren.<br/>
 * 
 * Die weitere Berechnung erfolgt wie bei Verfahren 00.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 1 2 1 2 1 2 1 2 1 <br/>
 * 
 * Testkontonummer: 6782533003
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum94 implements ChecksumValidator {

	private static final int[] WEIGHTS = { 1, 2, 1, 2, 1, 2, 1, 2, 1 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#calcChecksum(int[])
	 */
	@Override
	public int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * WEIGHTS[i]);
		}
		int checksum = (10 - (sum % 10)) % 10;
		return checksum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int[] filledAccountNumber=ChecksumUtils.getFilledAcountNumber(10, accountNumber);
		return (filledAccountNumber[9]==calcChecksum(filledAccountNumber));
	}

}
