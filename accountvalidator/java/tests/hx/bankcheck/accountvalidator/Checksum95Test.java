package hx.bankcheck.accountvalidator;

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

		Checksum95 checksum95 = new Checksum95();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 6, 8, 0, 0, 7, 0, 0, 3 };
		int[] validAccountNumber2 = { 0, 8, 4, 7, 3, 2, 1, 7, 5, 0 };
		int[] validAccountNumber3 = { 6, 4, 5, 0, 0, 6, 0, 4, 9, 4 };
		int[] validAccountNumber4 = { 6, 4, 5, 4, 0, 0, 0, 0, 0, 3 };

		// Should be valid
		assertTrue(checksum95.validate(validAccountNumber1));
		assertTrue(checksum95.validate(validAccountNumber2));
		assertTrue(checksum95.validate(validAccountNumber3));
		assertTrue(checksum95.validate(validAccountNumber4));

		// Testing exceptions

		// CAUTION --> LONG TESTING TIME
		//		 
		// for (long l = 1; l <= 1999999; l++) {
		// assertTrue(checksum95.validate(ChecksumUtils.parseAccountNumber(l))
		// && checksum95.isException());
		// }
		//
		// for (long l = 9000000; l <= 25999999; l++) {
		// assertTrue(checksum95.validate(ChecksumUtils.parseAccountNumber(l))
		// && checksum95.isException());
		// }
		//
		// for (long l = 396000000; l <= 499999999; l++) {
		// assertTrue(checksum95.validate(ChecksumUtils.parseAccountNumber(l))
		// && checksum95.isException());
		// }
		//
		// for (long l = 0700000000; l <= 799999999; l++) {
		// assertTrue(checksum95.validate(ChecksumUtils.parseAccountNumber(l))
		// && checksum95.isException());
		// }

	}
}
