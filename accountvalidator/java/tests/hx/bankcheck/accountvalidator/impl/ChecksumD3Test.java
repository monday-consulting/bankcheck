/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D3.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD3Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD3();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 6, 0, 0, 1, 6, 9, 5, 9, 1 };
		int[] validAccountNumberAlternative1_2 = { 1, 6, 0, 0, 1, 8, 9, 1, 5, 1 };
		int[] validAccountNumberAlternative1_3 = { 1, 8, 0, 0, 0, 8, 4, 0, 7, 9 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 1, 6, 0, 0, 1, 6, 6, 3, 0,
				7 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 6, 0, 0, 1, 7, 6, 4, 8,
				5 };
		int[] invalidAccountNumberAlternative1_3 = { 1, 6, 0, 0, 2, 0, 1, 9, 3,
				4 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 6, 0, 1, 9, 9, 3, 7, 0, 0, 7 };
		int[] validAccountNumberAlternative2_2 = { 6, 0, 2, 1, 3, 5, 4, 0, 0, 7 };
		int[] validAccountNumberAlternative2_3 = { 6, 0, 3, 0, 6, 4, 2, 0, 0, 6 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 6, 0, 2, 5, 0, 1, 7, 0, 0,
				9 };
		int[] invalidAccountNumberAlternative2_2 = { 6, 0, 2, 8, 2, 6, 7, 0, 0,
				3 };
		int[] invalidAccountNumberAlternative2_3 = { 6, 0, 1, 9, 8, 3, 5, 0, 0,
				1 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& validator.getAlternative() == 0);
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& validator.getAlternative() == 0);
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null)
				&& validator.getAlternative() == 0);

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

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& validator.getAlternative() == 1);
		assertTrue(validator.validate(validAccountNumberAlternative2_2, null)
				&& validator.getAlternative() == 1);
		assertTrue(validator.validate(validAccountNumberAlternative2_3, null)
				&& validator.getAlternative() == 1);

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

	}
}
