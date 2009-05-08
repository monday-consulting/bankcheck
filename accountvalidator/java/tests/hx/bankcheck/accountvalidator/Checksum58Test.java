package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum58;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 58.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum58Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum58 checksum = new Checksum58();

		// Valid account numbers for testing method A
		int[] validAccountNumber1 = { 1, 8, 0, 0, 8, 8, 1, 1, 2, 0 };
		int[] validAccountNumber2 = { 9, 2, 0, 0, 6, 5, 4, 1, 0, 8 };
		int[] validAccountNumber3 = { 1, 0, 1, 5, 2, 2, 2, 2, 2, 4 };
		int[] validAccountNumber4 = { 3, 7, 0, 3, 1, 6, 9, 6, 6, 8 };

		// Should be valid using method A
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));
		assertTrue(checksum.validate(validAccountNumber3));
		assertTrue(checksum.validate(validAccountNumber4));

	}
}
