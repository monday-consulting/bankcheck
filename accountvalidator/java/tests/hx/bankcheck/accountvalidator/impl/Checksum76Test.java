/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 76.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum76Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum76();

		// Valid account numbers for alternative 1
		int[] validAccountNumber1 = { 0, 0, 0, 6, 5, 4, 3, 2, 0, 0 };
		int[] validAccountNumber2 = { 9, 0, 1, 2, 3, 4, 5, 6, 0, 0 };
		int[] validAccountNumber3 = { 7, 8, 7, 6, 5, 4, 3, 1, 0, 0 };

		// Valid exceptional account numbers
		int[] validAccountNumberException1 = { 0, 0, 0, 0, 0, 6, 5, 4, 3, 2 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));

		// Should be valid as exception
		assertTrue(validator.validate(validAccountNumberException1, null)
				&& validator.isException());

	}
}
