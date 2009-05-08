package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum29;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 29.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum29Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum29 checksum = new Checksum29();

		// Valid account number
		int[] validAccountNumber1 = { 3, 1, 4, 5, 8, 6, 3, 0, 2, 9 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber1));
		
	}
}
