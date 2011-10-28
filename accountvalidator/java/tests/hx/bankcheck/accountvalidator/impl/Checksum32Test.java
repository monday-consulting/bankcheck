package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum32;

/**
 *  Testclass for testing algorithm 32.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 *
 */
public class Checksum32Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		Checksum32 checksum = new Checksum32();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 0, 9, 1, 4, 1, 4, 0, 5 };
		int[] validAccountNumber2 = { 1, 7, 0, 9, 1, 0, 7, 9, 8, 3 };
		int[] validAccountNumber3 = { 0, 1, 2, 2, 1, 1, 6, 9, 7, 9 };
		int[] validAccountNumber4 = { 0, 1, 2, 1, 1, 1, 4, 8, 6, 7 };
		int[] validAccountNumber5 = { 9, 0, 3, 0, 1, 0, 1, 1, 9, 2 };
		int[] validAccountNumber6 = { 9, 2, 4, 5, 5, 0, 0, 4, 6, 0 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));
		assertTrue(checksum.validate(validAccountNumber5));
		assertTrue(checksum.validate(validAccountNumber6));

	}
}
