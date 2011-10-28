/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 35.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum35Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum35();

		// Valid account number
		int[] validAccountNumber1 = { 0, 0, 0, 0, 1, 0, 8, 4, 4, 3 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 1, 0, 7, 4, 5, 1 };
		int[] validAccountNumber3 = { 0, 0, 0, 0, 1, 0, 2, 9, 2, 1 };
		int[] validAccountNumber4 = { 0, 0, 0, 0, 1, 0, 2, 3, 4, 9 };
		int[] validAccountNumber5 = { 0, 0, 0, 0, 1, 0, 1, 7, 0, 9 };
		int[] validAccountNumber6 = { 0, 0, 0, 0, 1, 0, 1, 5, 9, 9 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));
		assertTrue(validator.validate(validAccountNumber5, null));
		assertTrue(validator.validate(validAccountNumber6, null));

	}
}
