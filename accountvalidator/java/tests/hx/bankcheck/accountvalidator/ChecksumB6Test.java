package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumB6;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm B7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB6Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumB6 checksum = new ChecksumB6();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 9, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 9, 1, 1, 1, 0, 0, 0, 0, 0,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 4, 8, 7, 3, 1, 0, 0, 1, 8 };
		int[] validBankNumberAlternative2_1 = { 8, 0, 0, 5, 3, 7, 8, 2 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 4, 6, 7, 3, 1, 0, 0, 1,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 4, 7, 7, 3, 1, 0, 0, 1,
				8 };
		int[] invalidBankNumberAlternative2_1 = { 8, 0, 0, 5, 3, 7, 6, 2 };
		int[] invalidBankNumberAlternative2_2 = { 8, 0, 0, 5, 3, 7, 6, 2 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1, null))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative1_1, null))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1, validBankNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_1, invalidBankNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_2, invalidBankNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));

	}
}
