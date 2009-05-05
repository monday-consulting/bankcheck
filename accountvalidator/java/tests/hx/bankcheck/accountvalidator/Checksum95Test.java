package hx.bankcheck.accountvalidator;

import java.util.Random;

import hx.bankcheck.accountvalidator.impl.Checksum95;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 95.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum95Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum95 checksum = new Checksum95();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 6, 8, 0, 0, 7, 0, 0, 3 };
		int[] validAccountNumber2 = { 0, 8, 4, 7, 3, 2, 1, 7, 5, 0 };
		int[] validAccountNumber3 = { 6, 4, 5, 0, 0, 6, 0, 4, 9, 4 };
		int[] validAccountNumber4 = { 6, 4, 5, 4, 0, 0, 0, 0, 0, 3 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));

		// Testing exceptions
		Random rnd = new Random();
		
		int range = 1999999 - 1;
		int i=0;
		
		while(i++ < 10000) {
			long testNumber = 1 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils.parseAccountNumber(testNumber))
					&& checksum.isException());
		}
		
		range = 25999999 - 9000000;
		
		i=0;
		while(i++ < 10000) {
			long testNumber = 9000000 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils.parseAccountNumber(testNumber))
					&& checksum.isException());
		}

		range = 499999999 - 396000000;
		
		i=0;
		while(i++ < 10000) {
			long testNumber = 396000000 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils.parseAccountNumber(testNumber))
					&& checksum.isException());
		}

		range = 799999999 - 700000000;
		
		i=0;
		while(i++ < 10000) {
			long testNumber = 700000000 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils.parseAccountNumber(testNumber))
					&& checksum.isException());
		}

	}
}
