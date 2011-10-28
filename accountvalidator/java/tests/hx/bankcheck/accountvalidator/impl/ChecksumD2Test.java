/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D2.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD2Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD2();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 1, 8, 9, 9, 1, 2, 1, 3, 7 };
		int[] validAccountNumberAlternative1_2 = { 0, 2, 3, 5, 3, 0, 8, 2, 1, 5 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 4, 4, 5, 5, 6, 6, 7, 7, 8,
				4 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				7 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 5, 1, 1, 8, 1, 0, 0,
				8 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 0, 7, 1, 2, 1, 4, 2, 0,
				5 };
		int[] invalidAccountNumberAlternative1_5 = { 0, 0, 0, 6, 4, 1, 4, 2, 4,
				1 };
		int[] invalidAccountNumberAlternative1_6 = { 0, 1, 7, 9, 7, 5, 1, 3, 1,
				4 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 4, 4, 5, 5, 6, 6, 7, 7, 8, 4 };
		int[] validAccountNumberAlternative2_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 7 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 0, 5, 1, 1, 8, 1, 0, 0,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 7, 1, 2, 1, 4, 2, 0,
				5 };
		int[] invalidAccountNumberAlternative2_3 = { 0, 0, 0, 6, 4, 1, 4, 2, 4,
				1 };
		int[] invalidAccountNumberAlternative2_4 = { 0, 0, 0, 6, 4, 1, 4, 2, 4,
				1 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 0, 0, 5, 1, 1, 8, 1, 0, 0, 8 };
		int[] validAccountNumberAlternative3_2 = { 0, 0, 7, 1, 2, 1, 4, 2, 0, 5 };

		// Invalid account numbers for alternative 3
		int[] invalidAccountNumberAlternative3_1 = { 0, 0, 0, 6, 4, 1, 4, 2, 4,
				1 };
		int[] invalidAccountNumberAlternative3_2 = { 0, 1, 7, 9, 7, 5, 1, 3, 1,
				4 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null));

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
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_5, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_6, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null));
		assertTrue(validator.validate(validAccountNumberAlternative2_2, null));

		// Should be invalid for alternative 2
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_2, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_3, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_4, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 3
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null));
		assertTrue(validator.validate(validAccountNumberAlternative3_2, null));

		// Should be invalid for alternative 3
		assertFalse(validator
				.validate(invalidAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator
				.validate(invalidAccountNumberAlternative3_2, null)
				&& (validator.getAlternative() == 0));

	}
}
