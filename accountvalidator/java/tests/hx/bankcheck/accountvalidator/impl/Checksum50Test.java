/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 50.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum50Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum50();

		// Valid account number
		int[] validAccountNumber1 = { 4, 0, 0, 0, 0, 0, 5, 0, 0, 1 };
		int[] validAccountNumber2 = { 4, 4, 4, 4, 4, 4, 2, 0, 0, 1 };
		int[] validAccountNumber3 = { 0, 0, 0, 4, 0, 0, 0, 0, 0, 5 };
		int[] validAccountNumber4 = { 0, 0, 0, 4, 4, 4, 4, 4, 4, 2 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));
		assertTrue(validator.validate(validAccountNumber3, null));
		assertTrue(validator.validate(validAccountNumber4, null));

	}
}
