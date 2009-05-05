package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumA3;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm A3.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA3Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumA3 checksum = new ChecksumA3();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 7 };
		int[] validAccountNumberAlternative1_2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] invalidAccountNumberAlternative1_3 = { 6, 5, 4, 3, 2, 1, 7, 8, 9, 0 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 5, 4, 3, 2, 1, 6, 7, 8, 9 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int[] validAccountNumberAlternative2_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] validAccountNumberAlternative2_3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 6, 5, 4, 3, 2, 1, 7, 8, 9, 0 };
		int[] invalidAccountNumber2 = { 0, 5, 4, 3, 2, 1, 6, 7, 8, 9 };

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
				&& (checksum.getAlternative() == 1));

		// Should be invalid
		assertFalse(checksum.validate(invalidAccountNumber1));
		assertFalse(checksum.validate(invalidAccountNumber2));

	}

}
