package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumB2;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm B2.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB2Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumB2 checksum = new ChecksumB2();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 2, 0, 0, 1, 2, 3, 5, 7 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 8, 0, 0, 1, 2, 3, 4, 5 };
		int[] validAccountNumberAlternative1_3 = { 0, 9, 2, 6, 8, 0, 1, 9, 1, 0 };
		int[] validAccountNumberAlternative1_4 = { 1, 0, 0, 2, 3, 4, 5, 6, 7, 4 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 2, 0, 0, 1, 2, 3, 9,
				9 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 8, 0, 0, 1, 2, 3, 4,
				7 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 8, 0, 0, 1, 2, 3, 7,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 9, 3, 2, 1, 0, 0, 0, 2,
				7 };
		int[] invalidAccountNumberAlternative1_5 = { 3, 3, 1, 0, 1, 2, 3, 4, 5,
				4 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 8, 0, 0, 0, 9, 9, 0, 0, 5, 4 };
		int[] validAccountNumberAlternative2_2 = { 9, 0, 0, 0, 4, 8, 1, 8, 0, 5 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 8, 0, 0, 0, 9, 9, 0, 0, 5,
				7 };
		int[] invalidAccountNumberAlternative2_2 = { 8, 0, 1, 1, 0, 0, 0, 1, 2,
				6 };
		int[] invalidAccountNumberAlternative2_3 = { 9, 0, 0, 0, 4, 8, 1, 8, 0,
				0 };
		int[] invalidAccountNumberAlternative2_4 = { 9, 9, 8, 0, 4, 8, 0, 1, 1,
				1 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_4))
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
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_5))
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
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_3))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_4))
				&& (checksum.getAlternative() == 1));

	}
}
