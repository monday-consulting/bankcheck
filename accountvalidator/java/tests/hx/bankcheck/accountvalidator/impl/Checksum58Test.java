package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum58;

/**
 * Testclass for testing algorithm 58.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum58Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum58();

		// Valid account numbers for testing method A
		int[] validAccountNumber1 = { 1, 8, 0, 0, 8, 8, 1, 1, 2, 0 };
		int[] validAccountNumber2 = { 9, 2, 0, 0, 6, 5, 4, 1, 0, 8 };
		int[] validAccountNumber3 = { 1, 0, 1, 5, 2, 2, 2, 2, 2, 4 };
		int[] validAccountNumber4 = { 3, 7, 0, 3, 1, 6, 9, 6, 6, 8 };

		// Should be valid using method A
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));

	}
}
