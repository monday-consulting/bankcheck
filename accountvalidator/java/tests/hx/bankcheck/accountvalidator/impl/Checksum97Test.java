package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum97;

/**
 * Testclass for testing algorithm 97.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum97Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum97();

		// Valid account number
		int[] validAccountNumber = { 0, 0, 2, 4, 0, 1, 0, 0, 1, 9 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber, null));

	}

}
