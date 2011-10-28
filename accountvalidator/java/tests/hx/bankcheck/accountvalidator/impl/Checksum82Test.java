/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 82.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum82Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum82();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 0, 1, 2, 3, 8, 9, 7 };
		int[] validAccountNumberAlternative1_2 = { 3, 1, 9, 9, 5, 0, 0, 5, 0, 1 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null));

	}
}
