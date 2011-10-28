package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Testclass for testing algorithm 33.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum33Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum33();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 0, 0, 0, 4, 8, 6, 5, 8 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 0, 8, 4, 9, 5, 6 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));

	}
}
