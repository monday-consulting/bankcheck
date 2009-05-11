package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum98;

/**
 * Testclass for testing algorithm 98.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum98Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		Checksum98 checksum = new Checksum98();

		// Valid account number
		int[] validAccountNumber1 = { 9, 6, 1, 9, 4, 3, 9, 2, 1, 3 };
		int[] validAccountNumber2 = { 3, 0, 0, 9, 8, 0, 0, 0, 1, 6 };
		int[] validAccountNumber3 = { 9, 6, 1, 9, 5, 0, 9, 9, 7, 6 };
		int[] validAccountNumber4 = { 5, 9, 8, 9, 8, 0, 0, 1, 7, 3 };
		int[] validAccountNumber5 = { 9, 6, 1, 9, 3, 1, 9, 9, 9, 9 };
		int[] validAccountNumber6 = { 6, 7, 1, 9, 4, 3, 0, 0, 1, 8 };
		int[] validAccountNumber7 = { 9, 6, 1, 9, 6, 0, 8, 1, 1, 8 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));
		assertTrue(checksum.validate(validAccountNumber5));
		assertTrue(checksum.validate(validAccountNumber6));
		assertTrue(checksum.validate(validAccountNumber7));

	}
}
