package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumA0;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm A0.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA0Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumA0 checksum = new ChecksumA0();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 5, 2, 1, 0, 0, 3, 2, 8, 7 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 0, 5, 4, 5, 0, 0 };
		int[] validAccountNumber3 = { 0, 0, 0, 0, 0, 0, 3, 2, 8, 7 };
		int[] validAccountNumber4 = { 0, 0, 0, 0, 0, 1, 8, 7, 6, 1 };
		int[] validAccountNumber5 = { 0, 0, 0, 0, 0, 2, 8, 2, 9, 0 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));
		assertTrue(checksum.validate(validAccountNumber5));

		// Testing exceptions
		Random rnd = new Random();

		int max = 999;

		int i = 0;
		while (i++ < 10000) {
			long testNumber = (int) (rnd.nextDouble() * max);
			assertTrue(checksum.validate(ChecksumUtils
					.parseAccountNumber(testNumber))
					&& checksum.isException());
		}
	}
}
