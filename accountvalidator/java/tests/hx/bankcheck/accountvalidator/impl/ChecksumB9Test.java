package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB9;

/**
 * Testclass for testing algorithm B9.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB9Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumB9();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 8, 7, 9, 2, 0, 1, 8, 7 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 4, 1, 2, 0, 3, 7, 5, 5 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 8, 1, 0, 6, 9, 5, 7, 7 };
		int[] validAccountNumberAlternative1_4 = { 0, 0, 6, 1, 2, 8, 7, 9, 5, 8 };
		int[] validAccountNumberAlternative1_5 = { 0, 0, 5, 8, 4, 6, 7, 2, 3, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 8, 8, 0, 3, 4, 0, 2,
				3 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 4, 3, 0, 2, 5, 4, 3,
				2 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 8, 6, 5, 2, 1, 3, 6,
				2 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 0, 6, 1, 2, 5, 6, 5, 2,
				3 };
		int[] invalidAccountNumberAlternative1_5 = { 0, 0, 5, 4, 3, 5, 2, 6, 8,
				4 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 7, 1, 2, 5, 6, 3, 3 };
		int[] validAccountNumberAlternative2_2 = { 0, 0, 0, 1, 2, 5, 3, 6, 5, 7 };
		int[] validAccountNumberAlternative2_3 = { 0, 0, 0, 4, 3, 5, 3, 6, 3, 1 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 0, 0, 2, 3, 5, 6, 4, 1,
				2 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 0, 0, 5, 4, 3, 5, 8, 8,
				6 };
		int[] invalidAccountNumberAlternative2_3 = { 0, 0, 0, 9, 4, 3, 5, 4, 1,
				4 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_3, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_4, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_5, null))
				&& (validator.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((validator.validate(invalidAccountNumberAlternative1_1,
				null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_2,
				null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_3,
				null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_4,
				null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_5,
				null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((validator.validate(validAccountNumberAlternative2_1, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_2, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_3, null))
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

	}
}
