package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Keine Prüfzifferberechnung
 * 
 * @author tma
 *
 */
public class Checksum09 implements ChecksumValidator {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return true;
	}

	@Override
	public int calcChecksum(int[] accountNumber) {
		return 0;
	}

}
