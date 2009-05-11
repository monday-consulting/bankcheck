package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.impl.ChecksumA0;

/**
 * Testclass for testing algorithm A0.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA0Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws Exception {

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
		checkRangeOfAccountNumbers(0, 999, null, 0, true, checksum, 10000);
	}
}
