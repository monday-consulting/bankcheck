/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 69.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum69Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum69();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 2, 3, 4, 5, 6, 7, 9, 0, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 1, 2, 3, 4, 5, 6, 7, 0, 0,
				6 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 1, 2, 3, 4, 5, 6, 7, 0, 0, 6 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 1, 2, 3, 4, 5, 6, 7, 9, 0,
				0 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& validator.getAlternative() == 0);

		// Should be invalid for alternative 1
		assertFalse(validator
				.validate(invalidAccountNumberAlternative1_1, null)
				&& validator.getAlternative() == 0);

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& validator.getAlternative() == 1);

		// Should be invalid for alternative 2
		assertFalse(validator
				.validate(invalidAccountNumberAlternative2_1, null)
				&& validator.getAlternative() == 1);

	}
}
