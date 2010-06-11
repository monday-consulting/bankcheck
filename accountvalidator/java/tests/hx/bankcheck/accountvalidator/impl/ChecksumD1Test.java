/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D1.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * @version 1.1
 * 
 */
public class ChecksumD1Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD1();

		int[] validAccountNumber1 = { 3, 0, 0, 2, 0, 0, 0, 0, 2, 7 };
		int[] validAccountNumber2 = { 9, 0, 0, 0, 4, 3, 0, 2, 2, 3 };
		int[] validAccountNumber3 = { 2, 0, 0, 3, 4, 5, 5, 1, 8, 2 };
		int[] validAccountNumber4 = { 1, 0, 3, 1, 4, 0, 5, 2, 0, 1 };
		int[] validAccountNumber5 = { 0, 0, 8, 2, 0, 1, 2, 2, 0, 3 };
		int[] validAccountNumber6 = { 5, 0, 0, 0, 0, 6, 5, 5, 1, 4 };

		int[] invalidAccountNumber1 = { 7, 0, 0, 0, 0, 6, 2, 0, 2, 5 };
		int[] invalidAccountNumber2 = { 4, 0, 0, 6, 0, 0, 3, 0, 2, 7 };
		int[] invalidAccountNumber3 = { 8, 0, 0, 3, 3, 0, 6, 0, 2, 6 };
		int[] invalidAccountNumber4 = { 2, 0, 0, 1, 5, 0, 1, 0, 2, 6 };
		int[] invalidAccountNumber5 = { 9, 0, 0, 0, 6, 4, 1, 5, 0, 9 };
		int[] invalidAccountNumber6 = { 0, 0, 0, 0, 2, 6, 0, 9, 8, 6 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));
		assertTrue(validator.validate(validAccountNumber5, null));
		assertTrue(validator.validate(validAccountNumber6, null));

		// Should be invalid for alternative 1
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));
		assertFalse(validator.validate(invalidAccountNumber3, null));
		assertFalse(validator.validate(invalidAccountNumber4, null));
		assertFalse(validator.validate(invalidAccountNumber5, null));
		assertFalse(validator.validate(invalidAccountNumber6, null));
	}
}
