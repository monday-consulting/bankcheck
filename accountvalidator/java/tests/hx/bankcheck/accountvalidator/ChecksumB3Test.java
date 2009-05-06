package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumB3;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm B3.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB3Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumB3 checksum = new ChecksumB3();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 0, 0, 0, 0, 0, 0, 0, 6, 0 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 0, 0, 0, 0, 1, 4, 0 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 9 };
		int[] validAccountNumberAlternative1_4 = { 1, 0, 0, 2, 7, 9, 8, 4, 1, 7 };
		int[] validAccountNumberAlternative1_5 = { 8, 4, 0, 9, 9, 1, 5, 0, 0, 1 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 2, 7, 9, 9, 8, 9,
				9 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 0, 0, 0, 0, 0, 0, 1, 1,
				1 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 9, 6, 3, 5, 0, 0, 0, 1, 0, 1 };
		int[] validAccountNumberAlternative2_2 = { 9, 7, 3, 0, 2, 0, 0, 1, 0, 0 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 9, 6, 3, 5, 1, 0, 0, 1, 0,
				1 };
		int[] invalidAccountNumberAlternative2_2 = { 9, 7, 3, 0, 3, 0, 0, 1, 0,
				0 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_4))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_5))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));

	}
}
