package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum97;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 97.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum97Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum97 checksum97 = new Checksum97();

		// Valid account number
		int[] validAccountNumber = { 2, 4, 0, 1, 0, 0, 1, 9 };

		// Should be valid
		assertTrue(checksum97.validate(validAccountNumber));

	}

}
