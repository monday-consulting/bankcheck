package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumC1;

/**
 * Testclass for testing algorithm C1.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC1Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumC1();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 4, 4, 6, 7, 8, 6, 0, 4, 0 };
		int[] validAccountNumberAlternative1_2 = { 0, 4, 7, 8, 0, 4, 6, 9, 4, 0 };
		int[] validAccountNumberAlternative1_3 = { 0, 7, 0, 1, 6, 2, 5, 8, 3, 0 };
		int[] validAccountNumberAlternative1_4 = { 0, 7, 0, 1, 6, 2, 5, 8, 4, 0 };
		int[] validAccountNumberAlternative1_5 = { 0, 8, 8, 2, 0, 9, 5, 6, 3, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 4, 4, 6, 7, 8, 6, 2, 4,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 4, 7, 8, 0, 4, 6, 3, 4,
				0 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 7, 0, 1, 6, 2, 5, 7, 3,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 7, 0, 1, 6, 2, 5, 4, 4,
				0 };
		int[] invalidAccountNumberAlternative1_5 = { 0, 8, 8, 2, 0, 9, 5, 1, 3,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 5, 4, 3, 2, 1, 1, 2, 3, 4, 9 };
		int[] validAccountNumberAlternative2_2 = { 5, 5, 4, 3, 2, 2, 3, 4, 5, 6 };
		int[] validAccountNumberAlternative2_3 = { 5, 6, 5, 4, 3, 3, 4, 5, 6, 3 };
		int[] validAccountNumberAlternative2_4 = { 5, 7, 6, 5, 4, 4, 5, 6, 7, 0 };
		int[] validAccountNumberAlternative2_5 = { 5, 8, 7, 6, 5, 5, 6, 7, 8, 8 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 5, 4, 3, 2, 1, 1, 2, 3, 4,
				1 };
		int[] invalidAccountNumberAlternative2_2 = { 5, 5, 4, 3, 2, 2, 3, 4, 5,
				8 };
		int[] invalidAccountNumberAlternative2_3 = { 5, 6, 5, 4, 3, 3, 4, 5, 6,
				5 };
		int[] invalidAccountNumberAlternative2_4 = { 5, 7, 6, 5, 4, 4, 5, 6, 7,
				2 };
		int[] invalidAccountNumberAlternative2_5 = { 5, 8, 7, 6, 5, 5, 6, 7, 8,
				0 };

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
		assertTrue((validator.validate(validAccountNumberAlternative2_4, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_5, null))
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
		assertFalse((validator.validate(invalidAccountNumberAlternative2_5,
				null))
				&& (validator.getAlternative() == 1));

	}
}
