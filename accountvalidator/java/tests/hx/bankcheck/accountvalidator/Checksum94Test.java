package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum94;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 95.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum94Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum94 checksum94 = new Checksum94();

		// Valid account numbers for testing alternative 1
		int[] validAccountNumber = { 6, 7, 8, 2, 5, 3, 3, 0, 0, 3 };

		// Should be valid
		assertTrue(checksum94.validate(validAccountNumber));

	}

}
