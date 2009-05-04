package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum96;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 96.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum96Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum96 checksum96 = new Checksum96();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 0, 2, 5, 4, 1, 0, 0 };
		int[] validAccountNumberAlternative1_2 = { 9, 4, 2, 1, 0, 0, 0, 0, 0, 9 };

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 0, 0, 0, 2, 0, 8 };
		int[] validAccountNumberAlternative2_2 = { 0, 1, 0, 1, 1, 1, 5, 1, 5, 2 };
		int[] validAccountNumberAlternative2_3 = { 0, 3, 0, 1, 2, 0, 4, 3, 0, 1 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 0, 0, 0, 1, 3, 0, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative3_2 = { 0, 0, 0, 1, 3, 0, 0, 0, 2, 2 };
		int[] validAccountNumberAlternative3_3 = { 0, 0, 9, 9, 3, 9, 9, 9, 7, 7 };
		int[] validAccountNumberAlternative3_4 = { 0, 0, 9, 9, 3, 9, 9, 9, 9, 9 };

		// Should be valid using alternative 1
		assertTrue((checksum96.validate(validAccountNumberAlternative1_1))
				&& (checksum96.getMethodFlag() == 0));
		assertTrue((checksum96.validate(validAccountNumberAlternative1_2))
				&& (checksum96.getMethodFlag() == 0));

		// Should be valid using alternative 1
		assertTrue((checksum96.validate(validAccountNumberAlternative2_1))
				&& (checksum96.getMethodFlag() == 1));
		assertTrue((checksum96.validate(validAccountNumberAlternative2_2))
				&& (checksum96.getMethodFlag() == 1));
		assertTrue((checksum96.validate(validAccountNumberAlternative2_3))
				&& (checksum96.getMethodFlag() == 1));

		// Exceptions should be valid
		assertTrue(checksum96.validate(validAccountNumberAlternative3_1)
				&& (checksum96.getMethodFlag() == 2));
		assertTrue(checksum96.validate(validAccountNumberAlternative3_2)
				&& (checksum96.getMethodFlag() == 2));
		assertTrue(checksum96.validate(validAccountNumberAlternative3_3)
				&& (checksum96.getMethodFlag() == 2));
		assertTrue(checksum96.validate(validAccountNumberAlternative3_4)
				&& (checksum96.getMethodFlag() == 2));

	}
}