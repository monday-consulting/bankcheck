/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm C9.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC9Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC9();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 9 };
		int[] validAccountNumberAlternative1_2 = { 5, 6, 7, 8, 9, 0, 1, 2, 3, 1 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1,
				2 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				9 };
		int[] invalidAccountNumberAlternative1_3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 9, 0, 1, 2, 3, 4, 5, 6, 7,
				8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative2_2 = { 9, 0, 1, 2, 3, 4, 5, 6, 7,
				8 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));

		// Should be invalid for alternative 1
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_3, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_4, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
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
