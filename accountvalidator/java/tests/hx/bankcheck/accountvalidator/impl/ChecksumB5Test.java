package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB5;

/**
 * Testclass for testing algorithm B5.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB5Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumB5 checksum = new ChecksumB5();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 1, 5, 9, 0, 0, 6, 9, 5, 5 };
		int[] validAccountNumberAlternative1_2 = { 2, 0, 0, 0, 1, 2, 3, 4, 5, 1 };
		int[] validAccountNumberAlternative1_3 = { 1, 1, 5, 1, 0, 4, 3, 2, 1, 6 };
		int[] validAccountNumberAlternative1_4 = { 9, 0, 0, 0, 9, 3, 9, 0, 3, 3 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 7, 4, 1, 4, 3, 9, 8, 2, 6,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 8, 3, 4, 7, 2, 5, 1, 6, 9,
				3 };
		int[] invalidAccountNumberAlternative1_3 = { 1, 1, 5, 1, 0, 4, 3, 2, 1,
				1 };
		int[] invalidAccountNumberAlternative1_4 = { 2, 3, 4, 5, 6, 7, 8, 9, 0,
				1 };
		int[] invalidAccountNumberAlternative1_5 = { 5, 6, 7, 8, 9, 0, 1, 2, 3,
				4 };
		int[] invalidAccountNumberAlternative1_6 = { 9, 0, 0, 0, 2, 9, 3, 7, 0,
				7 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 2 };
		int[] validAccountNumberAlternative2_2 = { 0, 1, 3, 0, 0, 9, 8, 7, 6, 7 };
		int[] validAccountNumberAlternative2_3 = { 1, 0, 4, 5, 0, 0, 0, 2, 5, 2 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 1, 5, 9, 0, 0, 4, 1, 6,
				5 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 2, 3, 4, 5, 6, 7, 8,
				7 };
		int[] invalidAccountNumberAlternative2_3 = { 0, 0, 5, 6, 7, 8, 9, 0, 1,
				8 };
		int[] invalidAccountNumberAlternative2_4 = { 3, 0, 4, 5, 0, 0, 0, 3, 3,
				3 };

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
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_6))
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
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_4))
				&& (checksum.getAlternative() == 1));

	}
}
