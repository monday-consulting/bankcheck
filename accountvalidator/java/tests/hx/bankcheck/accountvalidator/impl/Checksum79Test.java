/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 79.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum79Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum79();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 3, 2, 3, 0, 0, 1, 2, 6, 8, 8 };
		int[] validAccountNumberAlternative1_2 = { 4, 2, 3, 0, 0, 2, 8, 8, 7, 2 };
		int[] validAccountNumberAlternative1_3 = { 5, 4, 4, 0, 0, 0, 1, 8, 9, 8 };
		int[] validAccountNumberAlternative1_4 = { 6, 3, 3, 0, 0, 0, 1, 0, 6, 3 };
		int[] validAccountNumberAlternative1_5 = { 7, 0, 0, 0, 1, 4, 9, 3, 4, 9 };
		int[] validAccountNumberAlternative1_6 = { 8, 0, 0, 0, 0, 0, 3, 5, 7, 7 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 1, 5, 5, 0, 1, 6, 7, 8, 5, 0 };
		int[] validAccountNumberAlternative2_2 = { 9, 0, 1, 1, 2, 0, 0, 1, 4, 0 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_4, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_5, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_6, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_2, null)
				&& (validator.getAlternative() == 1));

	}
}
