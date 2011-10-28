/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 88.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum88Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum88();

		// Valid account numbers for alternative 1
		int[] validAccountNumber1 = { 0, 0, 0, 2, 5, 2, 5, 2, 5, 9 };
		int[] validAccountNumber2 = { 0, 0, 0, 1, 0, 0, 0, 5, 0, 0 };
		int[] validAccountNumberException1 = { 0, 0, 9, 0, 0, 1, 3, 0, 0, 0 };
		int[] validAccountNumberException2 = { 0, 0, 9, 2, 5, 2, 5, 2, 5, 3 };
		int[] validAccountNumberException3 = { 0, 0, 9, 9, 9, 1, 3, 0, 0, 3 };

		// Should be valid and not an exception
		assertTrue(validator.validate(validAccountNumber1, null)
				&& !(validator.isException()));
		assertTrue(validator.validate(validAccountNumber2, null)
				&& !(validator.isException()));

		// Should be valid and not an exception
		assertTrue(validator.validate(validAccountNumberException1, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException2, null)
				&& (validator.isException()));
		assertTrue(validator.validate(validAccountNumberException3, null)
				&& (validator.isException()));

	}
}
