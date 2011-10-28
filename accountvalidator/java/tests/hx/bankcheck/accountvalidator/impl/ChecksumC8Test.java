/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm C8.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC8Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC8();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 9 };
		int[] validAccountNumberAlternative1_2 = { 5, 6, 7, 8, 9, 0, 1, 2, 3, 1 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1,
				2 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
				9 };
		int[] invalidAccountNumberAlternative1_3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 9, 0, 1, 2, 3, 4, 5, 6, 7,
				8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
				9 };
		int[] invalidAccountNumberAlternative2_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative2_3 = { 9, 0, 1, 2, 3, 4, 5, 6, 7,
				8 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// Invalid account numbers for alternative 3
		int[] invalidAccountNumberAlternative3_1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative3_2 = { 9, 0, 1, 2, 3, 4, 5, 6, 7,
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
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_3, null)
				&& (validator.getAlternative() == 1));
		
		// Should be valid for alternative 3
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() == 2));

		// Should be invalid for alternative 3
		assertFalse(validator
				.validate(invalidAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() == 2));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative3_2, null)
				&& (validator.getAlternative() == 2));
		
	}
}
