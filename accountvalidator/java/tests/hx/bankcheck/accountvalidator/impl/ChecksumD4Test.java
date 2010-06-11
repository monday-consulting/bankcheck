/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D4.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * @version 1.0
 * 
 */
public class ChecksumD4Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD4();
		
		int[] validAccountNumber1 = { 3, 0, 0, 0, 0, 0, 5, 0, 1, 2 };
		int[] validAccountNumber2 = { 4, 1, 4, 3, 4, 0, 6, 9, 8, 4 };
		int[] validAccountNumber3 = { 5, 9, 2, 6, 4, 8, 5, 1, 1, 1 };
		int[] validAccountNumber4 = { 9, 0, 0, 2, 3, 6, 4, 5, 8, 8 };

		int[] invalidAccountNumber1 = { 1, 0, 0, 0, 0, 6, 2, 0, 2, 5 };
		int[] invalidAccountNumber2 = { 0, 0, 0, 6, 0, 0, 3, 0, 2, 7 };
		int[] invalidAccountNumber3 = { 8, 0, 0, 3, 3, 0, 6, 0, 2, 6 };
		int[] invalidAccountNumber4 = { 9, 9, 1, 6, 5, 2, 4, 5, 3, 4 };
		int[] invalidAccountNumber5 = { 5, 2, 1, 2, 7, 4, 4, 5, 6, 4 };
		int[] invalidAccountNumber6 = { 3, 0, 0, 0, 2, 5, 5, 3, 9, 7 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));

		// Should be invalid for alternative 1
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));
		assertFalse(validator.validate(invalidAccountNumber3, null));
		assertFalse(validator.validate(invalidAccountNumber4, null));
		assertFalse(validator.validate(invalidAccountNumber5, null));
		assertFalse(validator.validate(invalidAccountNumber6, null));
	}
}
