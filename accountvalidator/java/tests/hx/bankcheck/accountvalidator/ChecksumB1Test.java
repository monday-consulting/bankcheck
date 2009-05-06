package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumB1;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm B1.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB1Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumB1 checksum = new ChecksumB1();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 4, 3, 4, 2, 5, 3, 1, 5, 0 };
		int[] validAccountNumberAlternative1_2 = { 2, 7, 4, 6, 3, 1, 5, 4, 7, 1 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 7, 4, 1, 4, 3, 9, 8, 2, 6,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 8, 3, 4, 7, 2, 5, 1, 6, 9,
				3 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
				9 };
		int[] invalidAccountNumberAlternative1_4 = { 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1 };
		int[] invalidAccountNumberAlternative1_5 = { 5, 6, 7, 8, 9, 0, 1, 2, 3,
				4 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 7, 4, 1, 4, 3, 9, 8, 2, 6, 0 };
		int[] validAccountNumberAlternative2_2 = { 8, 3, 4, 7, 2, 5, 1, 6, 9, 3 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8,
				9 };
		int[] invalidAccountNumberAlternative2_2 = { 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1 };
		int[] invalidAccountNumberAlternative2_3 = { 5, 6, 7, 8, 9, 0, 1, 2, 3,
				4 };

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

	}
}