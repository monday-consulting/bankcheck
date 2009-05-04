package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum99;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 98.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum99Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum99 checksum99 = new Checksum99();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 6, 8, 0, 0, 7, 0, 0, 3 };
		int[] validAccountNumber2 = { 0, 8, 4, 7, 3, 2, 1, 7, 5, 0 };

		// Should be valid
		assertTrue(checksum99.validate(validAccountNumber1));
		assertTrue(checksum99.validate(validAccountNumber2));

		// Testing exceptions

		// CAUTION --> LONG TESTING TIME
		//
		// for (long l = 396000000; l <= 499999999; l++) {
		// assertTrue(checksum99.validate(ChecksumUtils.parseAccountNumber(l))
		// && checksum99.isException());
		// }

	}
}
