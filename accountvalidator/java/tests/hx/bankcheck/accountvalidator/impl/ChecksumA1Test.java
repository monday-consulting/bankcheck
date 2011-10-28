package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA1;

/**
 * Testclass for testing algorithm A1.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA1Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumA1();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 1, 0, 0, 3, 0, 0, 0, 5 };
		int[] validAccountNumber2 = { 0, 0, 1, 0, 0, 3, 0, 9, 9, 7 };
		int[] validAccountNumber3 = { 1, 0, 1, 0, 0, 3, 0, 0, 5, 4 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 0, 1, 1, 0, 0, 3, 0, 0, 0, 5 };
		int[] invalidAccountNumber2 = { 0, 0, 1, 0, 0, 3, 0, 9, 9, 8 };
		int[] invalidAccountNumber3 = { 0, 0, 0, 0, 0, 3, 0, 0, 0, 5 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));

		// Should be invalid
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));
		assertFalse(validator.validate(invalidAccountNumber3, null));

	}

}
