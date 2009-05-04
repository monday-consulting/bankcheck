package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum93;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 93.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum93Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum93 checksum93 = new Checksum93();

		// Valid account numbers for testing alternative 1
		int[] validAccountNumberAlternative1a = { 6, 7, 1, 4, 7, 9, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative1b = { 0, 0, 0, 0, 6, 7, 1, 4, 7, 9 };

		// Valid account numbers for testing alternative 2
		int[] validAccountNumberAlternative21 = { 1, 2, 7, 7, 8, 3, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative2b1 = { 0, 0, 0, 0, 1, 2, 7, 7, 8, 3 };
		int[] validAccountNumberAlternative23 = { 1, 2, 7, 7, 9, 1, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative2b2 = { 0, 0, 0, 0, 1, 2, 7, 7, 9, 1 };

		// Valid account numbers for testing alternative 1 and alternative 2
		int[] validAccountNumber1 = { 3, 0, 6, 7, 5, 4, 0, 0, 0, 0 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 3, 0, 6, 7, 5, 4 };

		// Should be valid using alternative 1 case a)
		assertTrue((checksum93.validate(validAccountNumberAlternative1a))
				&& (checksum93.getMethodFlag() == 0));

		// Should be valid using alternative 1 case b)
		assertTrue((checksum93.validate(validAccountNumberAlternative1b))
				&& (checksum93.getMethodFlag() == 1));

		// Should be valid using alternative 2 case a)
		assertTrue((checksum93.validate(validAccountNumberAlternative21))
				&& (checksum93.getMethodFlag() == 2));

		assertTrue((checksum93.validate(validAccountNumberAlternative23))
				&& (checksum93.getMethodFlag() == 2));

		// Should be valid using alternative 2 case a)
		assertTrue((checksum93.validate(validAccountNumberAlternative2b1))
				&& (checksum93.getMethodFlag() == 3));

		assertTrue((checksum93.validate(validAccountNumberAlternative2b2))
				&& (checksum93.getMethodFlag() == 3));

		// Should be valid without regarding the alternatives
		assertTrue(checksum93.validate(validAccountNumber1));
		assertTrue(checksum93.validate(validAccountNumber2));

	}

}
