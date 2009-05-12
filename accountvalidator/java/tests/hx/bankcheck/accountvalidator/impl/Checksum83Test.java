/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 83.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum83Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum83();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 1, 1, 5, 6, 1, 3, 6 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative3_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 1 };

		// Valid account numbers for alternative 2
		int[] validImpersonalAccountNumber = { 0, 0, 9, 9, 1, 0, 0, 0, 0, 2 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2 (is also valid for alternative 1)
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() <= 1));

		// Should be valid for alternative 3 (is also valid for alternative 1)
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() <= 2));

		// Should be valid for impersonal account numbers
		assertTrue(validator.validate(validImpersonalAccountNumber, null));

	}
}
