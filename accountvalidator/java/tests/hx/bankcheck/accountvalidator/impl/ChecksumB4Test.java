package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB4;

/**
 * Testclass for testing algorithm B4.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB4Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumB4 checksum = new ChecksumB4();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 9, 9, 4, 1, 5, 1, 0, 0, 0, 1 };
		int[] validAccountNumberAlternative1_2 = { 9, 9, 6, 1, 2, 3, 0, 0, 1, 9 };
		int[] validAccountNumberAlternative1_3 = { 9, 3, 8, 0, 0, 2, 7, 2, 1, 0 };
		int[] validAccountNumberAlternative1_4 = { 9, 9, 3, 2, 2, 9, 0, 9, 1, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 9, 9, 4, 1, 5, 1, 0, 0, 0,
				2 };
		int[] invalidAccountNumberAlternative1_2 = { 9, 9, 6, 1, 2, 3, 0, 0, 2,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 2, 5, 1, 4, 3, 7 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 0, 7, 9, 4, 8, 3, 4, 4 };
		int[] validAccountNumberAlternative2_3 = { 0, 0, 0, 0, 0, 5, 1, 6, 4, 0 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 0, 0, 0, 2, 5, 1, 4, 3,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 0, 7, 9, 4, 8, 3, 4,
				5 };
		int[] invalidAccountNumberAlternative2_3 = { 0, 0, 0, 0, 1, 5, 9, 5, 9,
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
