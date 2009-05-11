package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA6;

/**
 * Testclass for testing algorithm A6.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA6Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumA6 checksum = new ChecksumA6();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 8, 0, 0, 0, 4, 8, 5, 4, 8 };
		int[] validAccountNumberAlternative1_2 = { 0, 8, 5, 5, 0, 0, 0, 0, 1, 4 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 8, 6, 0, 0, 0, 0, 8, 1,
				7 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 8, 1, 0, 0, 3, 3, 6, 5,
				2 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 7 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 5, 5, 3, 0, 0, 0, 3, 0 };
		int[] validAccountNumberAlternative2_3 = { 0, 1, 5, 0, 1, 7, 8, 0, 3, 3 };
		int[] validAccountNumberAlternative2_4 = { 0, 6, 0, 0, 0, 0, 3, 5, 5, 5 };
		int[] validAccountNumberAlternative2_5 = { 0, 9, 0, 0, 2, 9, 1, 8, 2, 3 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 0, 0, 0, 3, 0, 5, 8, 8,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 2, 0, 0, 0, 7, 1, 2, 8,
				0 };

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

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_3))
				&& (checksum.getAlternative() <= 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_4))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_5))
				&& (checksum.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));

	}
}
