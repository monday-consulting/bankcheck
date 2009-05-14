/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.AccountNumberNotTestableException;

/**
 * Testclass for testing algorithm 45.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum45Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum45();

		// Valid account number
		int[] validAccountNumber1 = { 3, 5, 4, 5, 3, 4, 3, 2, 3, 2 };
		int[] validAccountNumber2 = { 4, 0, 1, 3, 4, 1, 0, 0, 2, 4 };

		// Not testable
		int[] invalidAccountNumber1 = { 0, 9, 9, 4, 6, 8, 1, 2, 5, 4 };
		int[] invalidAccountNumber2 = { 0, 0, 0, 0, 0, 1, 2, 3, 4, 0 };
		int[] invalidAccountNumber3 = { 1, 0, 0, 0, 1, 9, 9, 9, 9, 9 };
		int[] invalidAccountNumber4 = { 0, 1, 0, 0, 1, 1, 4, 2, 4, 0 };

		// Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));

		// Not testable, so an exception should be thrown
		try {
			assertTrue(!validator.validate(invalidAccountNumber1, null));
			fail("Accountnumber shouldn't be testable");
		} catch (AccountNumberNotTestableException e) {
			assertTrue(true);
		}

		try {
			assertTrue(!validator.validate(invalidAccountNumber2, null));
			fail("Accountnumber shouldn't be testable");
		} catch (AccountNumberNotTestableException e) {
			assertTrue(true);
		}

		try {
			assertTrue(!validator.validate(invalidAccountNumber3, null));
			fail("Accountnumber shouldn't be testable");
		} catch (AccountNumberNotTestableException e) {
			assertTrue(true);
		}

		try {
			assertTrue(!validator.validate(invalidAccountNumber4, null));
			fail("Accountnumber shouldn't be testable");
		} catch (AccountNumberNotTestableException e) {
			assertTrue(true);
		}

	}
}
