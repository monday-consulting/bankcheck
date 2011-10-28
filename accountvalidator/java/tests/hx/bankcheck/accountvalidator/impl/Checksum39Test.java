/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 39.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum39Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum39();

		// Valid account number
		int[] validAccountNumber1 = { 0, 0, 0, 0, 2, 0, 0, 2, 0, 5 };
		int[] validAccountNumber2 = { 0, 0, 1, 0, 0, 1, 9, 4, 0, 0 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));

	}
}
