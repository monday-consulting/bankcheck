package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumA1;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm A1.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA1Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumA1 checksum = new ChecksumA1();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 1, 0, 0, 3, 0, 0, 0, 5 };
		int[] validAccountNumber2 = { 0, 0, 1, 0, 0, 3, 0, 9, 9, 7 };
		int[] validAccountNumber3 = { 1, 0, 1, 0, 0, 3, 0, 0, 5, 4 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 0, 1, 1, 0, 0, 3, 0, 0, 0, 5 };
		int[] invalidAccountNumber2 = { 0, 0, 1, 0, 0, 3, 0, 9, 9, 8 };
		int[] invalidAccountNumber3 = { 0, 0, 0, 0, 0, 3, 0, 0, 0, 5 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));

		// Should be invalid
		assertFalse(checksum.validate(invalidAccountNumber1));
		assertFalse(checksum.validate(invalidAccountNumber2));
		assertFalse(checksum.validate(invalidAccountNumber3));

	}

}
