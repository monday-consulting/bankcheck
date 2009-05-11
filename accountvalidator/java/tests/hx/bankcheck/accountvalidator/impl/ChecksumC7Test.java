/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm C7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC7Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC7();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 3, 5, 0, 0, 0, 2, 2 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 3, 8, 1, 5, 0, 9, 0, 0 };
		int[] validAccountNumberAlternative1_3 = { 0, 6, 0, 0, 1, 0, 3, 6, 6, 0 };
		int[] validAccountNumberAlternative1_4 = { 0, 0, 3, 9, 1, 0, 1, 1, 8, 1 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 9, 4, 0, 1, 2, 3, 4,
				1 };
		int[] invalidAccountNumberAlternative1_2 = { 5, 0, 7, 3, 3, 2, 1, 0, 1,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 9, 4, 0, 1, 2, 3, 4, 1 };
		int[] validAccountNumberAlternative2_2 = { 5, 0, 7, 3, 3, 2, 1, 0, 1, 0 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 1, 2, 3, 4, 5, 1, 7, 8, 9,
				2 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 9, 8, 7, 6, 1, 4, 3, 2,
				5 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_4, null)
				&& (validator.getAlternative() == 0));

		// Should be invalid for alternative 1
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_2, null)
				&& (validator.getAlternative() == 1));

		// Should be invalid for alternative 2
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_2, null)
				&& (validator.getAlternative() == 1));

	}
}
