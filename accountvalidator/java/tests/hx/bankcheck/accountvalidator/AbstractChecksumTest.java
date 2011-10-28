package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import java.util.Random;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public abstract class AbstractChecksumTest extends TestCase {

	@Test
	public abstract void testValidate() throws Throwable;

	/**
	 * Test method to test a range of account numbers
	 * 
	 * @param begin
	 *            The begin of the range
	 * @param end
	 *            The end of the range
	 * @param bankNumber
	 *            The bank number, can be 'null'
	 * @param alternative
	 *            The alternative
	 * @param exception
	 *            The exception state of the validator
	 * @param validator
	 *            The validator
	 * @param numberOfTests
	 *            The number of tests
	 * 
	 * @throws Throwable
	 */
	public void checkRangeOfAccountNumbers(long begin, long end,
			int[] bankNumber, int alternative, boolean exception,
			ChecksumValidator validator, int numberOfTests) throws Exception {
		Random rnd = new Random();
		long range = end - begin;

		int i = 0;

		while (i++ < numberOfTests) {
			long testNumber = begin + (int) (rnd.nextDouble() * range);
			assertTrue(validator.validate(ChecksumUtils
					.parseAccountNumber(testNumber), bankNumber)
					&& (validator.getAlternative() == alternative)
					&& (validator.isException() == exception));
		}

	}
}
