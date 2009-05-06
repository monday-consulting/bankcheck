package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Keine Prüfzifferberechnung
 * 
 * @author tma
 *
 */
public class Checksum09 extends AbstractChecksumValidator {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return true;
	}

}
