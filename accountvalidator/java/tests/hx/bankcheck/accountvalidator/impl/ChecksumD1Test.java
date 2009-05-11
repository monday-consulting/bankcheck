/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D1.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
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

		// Valid account numbers
		int[] validAccountNumberAlternative1_1 = { 1, 0, 0, 0, 0, 0, 4, 0, 1, 9 };
		int[] validAccountNumberAlternative1_2 = { 4, 0, 0, 0, 0, 0, 8, 0, 1, 4 };
		int[] validAccountNumberAlternative1_3 = { 6, 1, 0, 0, 0, 2, 0, 0, 1, 3 };
		int[] validAccountNumberAlternative1_4 = { 8, 3, 0, 0, 0, 4, 2, 0, 1, 1 };

		// Invalid account numbers
		int[] invalidAccountNumberAlternative1_1 = { 2, 0, 0, 0, 0, 0, 5, 0, 1,
				8 };
		int[] invalidAccountNumberAlternative1_2 = { 5, 0, 0, 0, 0, 6, 4, 0, 1,
				5 };
		int[] invalidAccountNumberAlternative1_3 = { 7, 4, 0, 0, 0, 4, 1, 0, 1,
				1 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null));
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null));
		assertTrue(validator.validate(validAccountNumberAlternative1_4, null));

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

	}
}
