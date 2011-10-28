/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 65.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum65Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum65();

		// Valid account numbers
		int[] validAccountNumber = { 1, 2, 3, 4, 5, 6, 7, 4, 0, 0 };

		// Valid exceptions
		int[] validException = { 1, 2, 3, 4, 5, 6, 7, 5, 9, 0 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber, null)
				&& (!validator.isException()));
		assertTrue(validator.validate(validException, null)
				&& validator.isException());
	}
}
