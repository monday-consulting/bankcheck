/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 41.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum41Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum41();

		// Valid account number
		int[] validAccountNumber1 = { 4, 0, 1, 3, 4, 1, 0, 0, 2, 4 };
		int[] validAccountNumber2 = { 4, 0, 1, 6, 6, 6, 0, 1, 9, 5 };
		int[] validAccountNumber3 = { 0, 1, 6, 6, 8, 0, 5, 3, 1, 7 };

		// Valid exceptional account numbers
		int[] validException1 = { 4, 0, 1, 9, 3, 1, 0, 0, 7, 9 };
		int[] validException2 = { 4, 0, 1, 9, 3, 4, 0, 8, 2, 9 };
		int[] validException3 = { 4, 0, 1, 9, 1, 5, 1, 0, 0, 2 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));

		// Should be valid
		assertTrue(validator.validate(validException1, null)
				&& validator.isException());
		assertTrue(validator.validate(validException2, null)
				&& validator.isException());
		assertTrue(validator.validate(validException3, null)
				&& validator.isException());

	}
}
