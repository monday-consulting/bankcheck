/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 84.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum84Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum84();

		// Valid account numbers
		int[] validAccountNumber1 = { 0,0,0,0,1,0,0,0,0,5 };
		int[] validAccountNumber2 = { 0,0,0,0,3,9,3,8,1,4 };
		int[] validAccountNumber3 = { 0,0,0,0,9,5,0,3,6,0 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));

		// Valid exceptional account numbers
		int[] validAccountNumberException1 = { 0, 1, 9, 9, 1, 0, 0, 0, 0, 4 };
		int[] validAccountNumberException2 = { 2, 5, 9, 9, 1, 0, 0, 0, 0, 3 };
		int[] validAccountNumberException3 = { 3, 1, 9, 9, 2, 0, 4, 0, 9, 0 };

		// Invalid exceptional account numbers
		int[] invalidAccountNumberException1 = { 0, 0, 9, 9, 3, 4, 5, 6, 7, 8 };
		int[] invalidAccountNumberException2 = { 0, 0, 9, 9, 1, 0, 0, 1, 1, 0 };
		int[] invalidAccountNumberException3 = { 0, 1, 9, 9, 1, 0, 0, 0, 4, 0 };

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberException1, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException2, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException3, null)
				&& (validator.isException()));

		// Should be valid for alternative 2
		assertFalse(validator.validate(invalidAccountNumberException1, null)
				&& (validator.isException()));
		assertFalse(validator.validate(invalidAccountNumberException2, null)
				&& (validator.isException()));
		assertFalse(validator.validate(invalidAccountNumberException3, null)
				&& (validator.isException()));

	}
}
