/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm D0.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD0Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD0();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 6, 1, 0, 0, 2, 7, 2, 3, 2, 4 };
		int[] validAccountNumberAlternative1_2 = { 6, 1, 0, 0, 2, 7, 3, 4, 7,9 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 6, 1, 0, 0, 2, 7, 2, 8, 8,
				5 };
		int[] invalidAccountNumberAlternative1_2 = { 6, 1, 0, 0, 2, 7, 3, 3, 7,
				7 };
		int[] invalidAccountNumberAlternative1_3 = { 6, 1, 0, 0, 2, 7, 4, 0, 1,
				2 };

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

		// Should be valid for alternative 2
		checkRangeOfAccountNumbers(5700000000l, 5799999999l, null, 1, false,
				validator, 10000);

	}
}
