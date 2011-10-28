/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Testclass for testing algorithm D4.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * @version 1.0
 * 
 */
public class ChecksumD4Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD4();

		long[] validNumbers = { 1112048219L, 2024601814L, 3000005012L,
				4143406984L, 5926485111L, 6286304975L, 7900256617L,
				8102228628L, 9002364588L };

		long[] invalidNumbers = { 359432843L, 1000062023L, 2204271250L,
				3051681017L, 4000123456L, 5212744564L, 6286420010L,
				7859103459L, 8003306026L, 9916524534L };

		// Should be valid
		for(long n : validNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = validator.validate(an, null);
			assertTrue(n+" is invalid!", valid);
		}

		// Should be invalid
		for(long n : invalidNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = validator.validate(an, null);
			assertFalse(n+" is valid!", valid);
		}
	}
}
