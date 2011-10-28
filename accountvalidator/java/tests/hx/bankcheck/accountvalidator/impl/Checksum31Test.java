package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Testclass for testing algorithm 31.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum31Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum31();

		// Valid account numbers
		int[] validAccountNumber1 = { 1, 0, 0, 0, 0, 0, 0, 5, 2, 4 };
		int[] validAccountNumber2 = { 1, 0, 0, 0, 0, 0, 0, 5, 8, 3 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));

	}
}
