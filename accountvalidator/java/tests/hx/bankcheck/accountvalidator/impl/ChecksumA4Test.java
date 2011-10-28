package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA4;

/**
 * Testclass for testing algorithm A4.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA4Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator checksum = new ChecksumA4();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 4, 7, 1, 1, 1, 7, 3 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 7, 0, 9, 3, 3, 3, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 4, 7, 1, 1, 1, 7,
				2 };
		int[] invalidAccountNumberAlternative1_2 = { 8, 6, 2, 3, 4, 2, 0, 0, 0,
				4 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 0, 1, 1, 2, 3, 4, 5,
				8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 4, 7, 1, 1, 1, 7, 2 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 0, 7, 0, 9, 3, 3, 3, 5 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 8, 6, 2, 3, 4, 2, 0, 0, 0,
				0 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 0, 1, 1, 2, 3, 4, 5,
				8 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 1, 1, 9, 9, 5, 0, 3, 0, 1, 0 };
		int[] validAccountNumberAlternative3_2 = { 8, 4, 9, 9, 4, 2, 1, 2, 3, 5 };

		// Invalid account numbers for alternative 3
		int[] invalidAccountNumberAlternative3_1 = { 1, 2, 9, 9, 5, 0, 3, 1, 1,
				7 };
		int[] invalidAccountNumberAlternative3_2 = { 6, 0, 9, 9, 7, 0, 2, 0, 3,
				1 };

		// Valid account numbers for alternative 4
		int[] validAccountNumberAlternative4_1 = { 0, 0, 0, 0, 8, 6, 2, 3, 4, 2 };
		int[] validAccountNumberAlternative4_2 = { 8, 9, 9, 7, 7, 1, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative4_3 = { 0, 6, 6, 4, 0, 4, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative4_4 = { 0, 0, 0, 0, 9, 0, 5, 8, 4, 4 };
		int[] validAccountNumberAlternative4_5 = { 5, 0, 3, 0, 1, 0, 1, 0, 9, 9 };
		int[] validAccountNumberAlternative4_6 = { 0, 0, 0, 1, 1, 2, 3, 4, 5, 8 };
		int[] validAccountNumberAlternative4_7 = { 1, 2, 9, 9, 5, 0, 3, 1, 1, 7 };

		// Invalid account numbers for alternative 4
		int[] invalidAccountNumberAlternative4_1 = { 0, 0, 0, 0, 3, 9, 9, 4, 4,
				3 };
		int[] invalidAccountNumberAlternative4_2 = { 0, 0, 0, 0, 5, 5, 3, 3, 1,
				3 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1, null))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2, null))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative1_1, null))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative1_2, null))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative1_3, null))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1, null))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_2, null))
				&& (checksum.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative2_1, null))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative2_2, null))
				&& (checksum.getAlternative() == 1));

		// Should be valid using alternative 3
		assertTrue((checksum.validate(validAccountNumberAlternative3_1, null))
				&& (checksum.getAlternative() == 2));
		assertTrue((checksum.validate(validAccountNumberAlternative3_2, null))
				&& (checksum.getAlternative() == 2));

		// Should be invalid using alternative 3
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative3_1, null))
				&& (checksum.getAlternative() == 2));
		assertFalse((checksum
				.validate(invalidAccountNumberAlternative3_2, null))
				&& (checksum.getAlternative() == 2));

		// Should be valid using alternative 4
		assertTrue((checksum.validate(validAccountNumberAlternative4_1, null))
				&& (checksum.getAlternative() <= 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_2, null))
				&& (checksum.getAlternative() == 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_3, null))
				&& (checksum.getAlternative() == 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_4, null))
				&& (checksum.getAlternative() <= 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_5, null))
				&& (checksum.getAlternative() == 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_6, null))
				&& (checksum.getAlternative() == 3));
		assertTrue((checksum.validate(validAccountNumberAlternative4_7, null))
				&& (checksum.getAlternative() == 3));

		// Should be invalid
		assertFalse(checksum.validate(invalidAccountNumberAlternative4_1, null));
		assertFalse(checksum.validate(invalidAccountNumberAlternative4_2, null));

	}
}
