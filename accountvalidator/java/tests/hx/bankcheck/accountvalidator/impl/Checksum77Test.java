/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 77.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum77Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum77();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 0, 0, 0, 1, 0, 3, 3, 8 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 0, 1, 3, 8, 4, 4 };
		int[] validAccountNumber3 = { 0, 0, 0, 0, 0, 6, 5, 3, 5, 4 };
		int[] validAccountNumber4 = { 0, 0, 0, 0, 0, 6, 9, 2, 5, 8 };

		// Should be valid 
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));

	}
}
