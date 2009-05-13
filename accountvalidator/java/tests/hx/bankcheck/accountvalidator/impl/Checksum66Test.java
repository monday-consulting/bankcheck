/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 66.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum66Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum66();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 1, 0, 0, 1, 5, 0, 5, 0, 2 };
		int[] validAccountNumber2 = { 0, 1, 0, 0, 1, 5, 4, 5, 0, 8 };
		int[] validAccountNumber3 = { 0, 1, 0, 1, 1, 5, 4, 5, 0, 8 };
		int[] validAccountNumber4 = { 0, 1, 0, 0, 1, 5, 4, 5, 1, 6 };
		int[] validAccountNumber5 = { 0, 1, 0, 1, 1, 5, 4, 5, 1, 6 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));
		assertTrue(validator.validate(validAccountNumber5, null));
	}
}
