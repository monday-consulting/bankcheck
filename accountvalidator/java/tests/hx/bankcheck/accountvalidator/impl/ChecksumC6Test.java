/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.impl.ChecksumC6;

/**
 * Testclass for testing algorithm C6.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC6Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC6();

		// Valid account numbers
		int[] validAccountNumber1 = { 7, 0, 0, 8, 1, 9, 9, 0, 2, 7 };
		int[] validAccountNumber2 = { 7, 0, 0, 2, 0, 0, 0, 0, 2, 3 };
		int[] validAccountNumber3 = { 7, 0, 0, 0, 2, 0, 2, 0, 2, 7 };
		int[] validAccountNumber4 = { 9, 0, 0, 0, 4, 3, 0, 2, 2, 3 };
		int[] validAccountNumber5 = { 9, 0, 0, 0, 7, 8, 1, 1, 5, 3 };
		int[] validAccountNumber6 = { 2, 0, 0, 3, 4, 5, 5, 1, 8, 9 };
		int[] validAccountNumber7 = { 2, 0, 0, 4, 0, 0, 1, 0, 1, 6 };
		int[] validAccountNumber8 = { 1, 0, 3, 1, 4, 0, 5, 2, 0, 9 };
		int[] validAccountNumber9 = { 1, 0, 8, 2, 0, 1, 2, 2, 0, 1 };
		int[] validAccountNumber10 = { 0, 0, 0, 0, 0, 6, 5, 5, 1, 6 };
		int[] validAccountNumber11 = { 0, 2, 0, 3, 1, 7, 8, 2, 4, 9 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 7, 0, 0, 0, 0, 6, 2, 0, 2, 2 };
		int[] invalidAccountNumber2 = { 7, 0, 0, 6, 0, 0, 3, 0, 2, 7 };
		int[] invalidAccountNumber3 = { 7, 0, 0, 3, 3, 0, 6, 0, 2, 6 };
		int[] invalidAccountNumber4 = { 7, 0, 0, 1, 5, 0, 1, 0, 2, 9 };
		int[] invalidAccountNumber5 = { 9, 0, 0, 0, 6, 4, 1, 5, 0, 9 };
		int[] invalidAccountNumber6 = { 9, 0, 0, 0, 2, 6, 0, 9, 8, 6 };
		int[] invalidAccountNumber7 = { 2, 0, 0, 4, 3, 0, 6, 5, 1, 8 };
		int[] invalidAccountNumber8 = { 2, 0, 1, 6, 0, 0, 1, 2, 0, 6 };
		int[] invalidAccountNumber9 = { 1, 0, 8, 2, 3, 1, 1, 2, 7, 5 };
		int[] invalidAccountNumber10 = { 1, 0, 0, 0, 1, 1, 8, 8, 2, 1 };
		int[] invalidAccountNumber11 = { 0, 5, 2, 5, 1, 1, 1, 2, 1, 2 };
		int[] invalidAccountNumber12 = { 0, 0, 9, 1, 4, 2, 3, 6, 1, 4 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));
		assertTrue(validator.validate(validAccountNumber5, null));
		assertTrue(validator.validate(validAccountNumber6, null));
		assertTrue(validator.validate(validAccountNumber7, null));
		assertTrue(validator.validate(validAccountNumber8, null));
		assertTrue(validator.validate(validAccountNumber9, null));
		assertTrue(validator.validate(validAccountNumber10, null));
		assertTrue(validator.validate(validAccountNumber11, null));

		// Should be invalid
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));
		assertFalse(validator.validate(invalidAccountNumber3, null));
		assertFalse(validator.validate(invalidAccountNumber4, null));
		assertFalse(validator.validate(invalidAccountNumber5, null));
		assertFalse(validator.validate(invalidAccountNumber6, null));
		assertFalse(validator.validate(invalidAccountNumber7, null));
		assertFalse(validator.validate(invalidAccountNumber8, null));
		assertFalse(validator.validate(invalidAccountNumber9, null));
		assertFalse(validator.validate(invalidAccountNumber10, null));
		assertFalse(validator.validate(invalidAccountNumber11, null));
		assertFalse(validator.validate(invalidAccountNumber12, null));

	}
}