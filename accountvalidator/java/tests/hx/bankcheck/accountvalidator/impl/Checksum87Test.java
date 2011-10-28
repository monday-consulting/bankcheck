/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 87.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum87Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum87();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 0, 0, 0, 0, 4, 0, 6 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 0, 0, 5, 1, 7, 6, 8 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 1, 0, 7, 0, 1, 5, 9, 0 };
		int[] validAccountNumberAlternative1_4 = { 0, 0, 1, 0, 7, 2, 0, 1, 8, 5 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 1, 0, 0, 0, 0, 5 };
		int[] validAccountNumberAlternative2_3 = { 0, 0, 0, 0, 9, 5, 0, 3, 6, 0 };
		int[] validAccountNumberAlternative2_4 = { 3, 1, 9, 9, 5, 0, 0, 5, 0, 1 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 0, 0, 0, 0, 3, 9, 3, 8, 1, 4 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_4, null)
				&& (validator.getAlternative() == 0));

		// Valid exceptional account numbers
		int[] validAccountNumberException1 = { 0, 1, 9, 9, 1, 0, 0, 0, 0, 4 };
		int[] validAccountNumberException2 = { 2, 5, 9, 9, 1, 0, 0, 0, 0, 3 };
		int[] validAccountNumberException3 = { 3, 1, 9, 9, 2, 0, 4, 0, 9, 0 };

		// Invalid exceptional account numbers
		int[] invalidAccountNumberException1 = { 0, 0, 9, 9, 3, 4, 5, 6, 7, 8 };
		int[] invalidAccountNumberException2 = { 0, 0, 9, 9, 1, 0, 0, 1, 1, 0 };
		int[] invalidAccountNumberException3 = { 0, 1, 9, 9, 1, 0, 0, 0, 4, 0 };

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_3, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_4, null)
				&& (validator.getAlternative() == 1));

		// Should be valid for alternative 3 (also valid for alternative 1)
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() <= 2));

		// Should be valid
		assertTrue(validator.validate(validAccountNumberException1, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException2, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException3, null)
				&& (validator.isException()));

		// Should be valid
		assertFalse(validator.validate(invalidAccountNumberException1, null)
				&& (validator.isException()));
		assertFalse(validator.validate(invalidAccountNumberException2, null)
				&& (validator.isException()));
		assertFalse(validator.validate(invalidAccountNumberException3, null)
				&& (validator.isException()));

	}
}
