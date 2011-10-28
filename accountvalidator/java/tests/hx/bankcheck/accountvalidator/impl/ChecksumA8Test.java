package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA8;

/**
 * Testclass for testing algorithm A8.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA8Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumA8();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 7, 4, 3, 6, 6, 6, 1 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 7, 4, 3, 6, 6, 7, 0 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 0, 1, 3, 5, 9, 1, 0, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 7, 4, 3, 6, 6, 6,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 0, 7, 4, 3, 6, 6, 7,
				8 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 7, 4, 3, 6, 6, 6, 0 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 0, 7, 4, 3, 6, 6, 7, 8 };
		int[] validAccountNumberAlternative2_3 = { 0, 0, 0, 3, 5, 0, 3, 3, 9, 8 };
		int[] validAccountNumberAlternative2_4 = { 0, 0, 0, 1, 3, 4, 0, 9, 6, 7 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 0, 0, 7, 4, 3, 6, 6, 6,
				6 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 0, 7, 4, 3, 6, 6, 7,
				7 };
		int[] invalidAccountNumberAlternative2_3 = { 0, 0, 0, 3, 5, 0, 3, 3, 9,
				1 };
		int[] invalidAccountNumberAlternative2_4 = { 0, 0, 0, 1, 3, 4, 0, 9, 6,
				6 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_3, null))
				&& (validator.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((validator.validate(invalidAccountNumberAlternative1_1,
				null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_2,
				null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((validator.validate(validAccountNumberAlternative2_1, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_2, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_3, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_4, null))
				&& (validator.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((validator.validate(invalidAccountNumberAlternative2_1,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_2,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_3,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_4,
				null))
				&& (validator.getAlternative() == 1));

	}
}
