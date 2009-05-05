package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumA7;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm A7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA7Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumA7 checksum = new ChecksumA7();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 1, 9, 0, 1, 0, 0, 0, 8 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 1, 9, 0, 1, 0, 4, 3, 8 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 1, 9, 0, 1, 0, 6, 6,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 1, 9, 0, 1, 0, 8, 7, 6,
				2 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 2, 0, 9, 0, 1, 0, 8, 9,
				2 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 2, 0, 9, 0, 1, 0, 8, 9,
				3 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 1, 9, 0, 1, 0, 6, 6, 0 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 1, 9, 0, 1, 0, 8, 7, 6 };
		int[] validAccountNumberAlternative2_3 = { 0, 2, 0, 9, 0, 1, 0, 8, 9, 2 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 2, 0, 9, 0, 1, 0, 8, 9,
				3 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_4))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_3))
				&& (checksum.getAlternative() <= 1));

		// Should be invalid using alternative 2
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));

	}
}
