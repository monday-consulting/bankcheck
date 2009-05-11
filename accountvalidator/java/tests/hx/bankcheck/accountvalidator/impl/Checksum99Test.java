package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.impl.Checksum99;

/**
 * Testclass for testing algorithm 98.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum99Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws Exception {

		Checksum99 checksum = new Checksum99();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 6, 8, 0, 0, 7, 0, 0, 3 };
		int[] validAccountNumber2 = { 0, 8, 4, 7, 3, 2, 1, 7, 5, 0 };
		int[] validAccountNumber3 = { 0, 3, 9, 5, 9, 9, 9, 9, 9, 9 };
		int[] validAccountNumber4 = { 0, 3, 9, 6, 0, 0, 0, 0, 0, 0 };
		int[] validAccountNumber5 = { 0, 4, 9, 9, 9, 9, 9, 9, 9, 9 };
		int[] validAccountNumber6 = { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertFalse(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));
		assertTrue(checksum.validate(validAccountNumber5));
		assertFalse(checksum.validate(validAccountNumber6));

		// Testing exceptions
		checkRangeOfAccountNumbers(396000000l, 499999999l, null, 0, true, checksum, 10000);
	}
}
