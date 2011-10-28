/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 74.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum74Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum74();

		// Valid account numbers for alternative 1
		int[] validAccountNumber1 = { 0, 0, 0, 0, 0, 0, 1, 0, 1, 6 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 0, 2, 6, 2, 6, 0 };
		int[] validAccountNumber3 = { 0, 0, 0, 0, 2, 4, 2, 2, 4, 3 };
		int[] validAccountNumber4 = { 0, 0, 1, 8, 0, 0, 2, 1, 1, 3 };
		int[] validAccountNumber5 = { 1, 8, 2, 1, 2, 0, 0, 0, 4, 3 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 0, 0, 0, 0, 0, 0, 1, 0, 1, 1 };
		int[] invalidAccountNumber2 = { 0, 0, 0, 0, 0, 2, 6, 2, 6, 5 };
		int[] invalidAccountNumber3 = { 0, 0, 1, 8, 0, 0, 2, 1, 1, 8 };
		int[] invalidAccountNumber4 = { 6, 1, 6, 0, 0, 0, 0, 0, 2, 4 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));
		assertTrue(validator.validate(validAccountNumber5, null));

		// Should be invalid
		assertFalse(validator.validate(invalidAccountNumber1, null)
				&& validator.isException());
		assertFalse(validator.validate(invalidAccountNumber2, null)
				&& validator.isException());
		assertFalse(validator.validate(invalidAccountNumber3, null)
				&& validator.isException());
		assertFalse(validator.validate(invalidAccountNumber4, null)
				&& validator.isException());

	}
}
