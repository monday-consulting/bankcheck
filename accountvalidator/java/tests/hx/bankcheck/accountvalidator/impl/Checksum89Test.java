/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 89.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum89Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum89();

		// Valid account numbers for alternative 1
		int[] validAccountNumber1 = { 0, 0, 0, 1, 0, 9, 8, 5, 0, 6 };
		int[] validAccountNumber2 = { 0, 0, 3, 2, 0, 2, 8, 0, 0, 8 };
		int[] validAccountNumber3 = { 0, 2, 1, 8, 4, 3, 3, 0, 0, 0 };

		// Should be valid 
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));

	}
}
