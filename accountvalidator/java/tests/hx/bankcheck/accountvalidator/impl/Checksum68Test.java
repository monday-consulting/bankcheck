/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for algorithm 68.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum68Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum68();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1 = { 8, 8, 8, 9, 6, 5, 4, 3, 2, 8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2 = { 0, 9, 8, 7, 6, 5, 4, 3, 2, 4 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3 = { 0, 9, 8, 7, 6, 5, 4, 3, 2, 8 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1, null)
				&& validator.getAlternative() == 0);

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2, null)
				&& validator.getAlternative() == 1);

		// Should be valid for alternative 3
		assertTrue(validator.validate(validAccountNumberAlternative3, null)
				&& validator.getAlternative() == 2);

	}

}
