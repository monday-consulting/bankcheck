/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 61.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum61Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum61();

		// Valid account number
		int[] validAccountNumber = { 2, 0, 6, 3, 0, 9, 9, 2, 0, 0 };
		
		//valid exceptional account number
		int[] validException = { 0, 2, 6, 0, 7, 6, 0, 4, 8, 1 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber, null)&&(!validator.isException()));
		assertTrue(validator.validate(validException, null)&&(validator.isException()));

	}
}
