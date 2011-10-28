package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm C5.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC5Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC5();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 0, 3, 0, 1, 1, 6, 8 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 0, 3, 0, 2, 5, 5, 4 };
		int[] validAccountNumberAlternative1_3 = { 0, 3, 0, 0, 0, 2, 0, 0, 5, 0 };
		int[] validAccountNumberAlternative1_4 = { 0, 3, 0, 0, 5, 6, 6, 0, 0, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 0, 3, 0, 2, 5, 8,
				9 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 0, 0, 5, 0, 7, 3, 3,
				6 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 3, 0, 2, 5, 5, 5, 0, 0,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 3, 0, 2, 5, 8, 9, 0, 0,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 1, 0, 0, 0, 0, 6, 1, 3, 7, 8 };
		int[] validAccountNumberAlternative2_2 = { 1, 0, 0, 0, 0, 6, 1, 4, 1, 2 };
		int[] validAccountNumberAlternative2_3 = { 4, 4, 5, 0, 1, 6, 4, 0, 6, 4 };
		int[] validAccountNumberAlternative2_4 = { 4, 8, 6, 3, 4, 7, 6, 1, 0, 4 };
		int[] validAccountNumberAlternative2_5 = { 5, 0, 0, 0, 0, 0, 0, 0, 2, 8 };
		int[] validAccountNumberAlternative2_6 = { 5, 0, 0, 0, 0, 0, 0, 3, 9, 1 };
		int[] validAccountNumberAlternative2_7 = { 6, 4, 5, 0, 0, 0, 8, 1, 4, 9 };
		int[] validAccountNumberAlternative2_8 = { 6, 8, 0, 0, 0, 0, 1, 0, 1, 6 };
		int[] validAccountNumberAlternative2_9 = { 9, 0, 0, 0, 1, 0, 0, 0, 1, 2 };
		int[] validAccountNumberAlternative2_X = { 9, 0, 0, 0, 2, 1, 0, 0, 1, 7 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 1, 0, 0, 0, 0, 6, 1, 4, 5,
				7 };
		int[] invalidAccountNumberAlternative2_2 = { 1, 0, 0, 0, 0, 6, 1, 4, 9,
				8 };
		int[] invalidAccountNumberAlternative2_3 = { 4, 8, 6, 4, 4, 4, 6, 0, 1,
				5 };
		int[] invalidAccountNumberAlternative2_4 = { 4, 8, 6, 5, 0, 3, 8, 0, 1,
				2 };
		int[] invalidAccountNumberAlternative2_5 = { 5, 0, 0, 0, 0, 0, 1, 0, 2,
				8 };
		int[] invalidAccountNumberAlternative2_6 = { 5, 0, 0, 0, 0, 0, 1, 0, 7,
				5 };
		int[] invalidAccountNumberAlternative2_7 = { 6, 4, 5, 0, 0, 0, 8, 1, 5,
				0 };
		int[] invalidAccountNumberAlternative2_8 = { 6, 5, 4, 2, 8, 1, 2, 8, 1,
				8 };
		int[] invalidAccountNumberAlternative2_9 = { 9, 0, 0, 0, 1, 1, 0, 0, 1,
				2 };
		int[] invalidAccountNumberAlternative2_X = { 9, 0, 0, 0, 3, 0, 0, 3, 1,
				0 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 3, 0, 6, 0, 1, 8, 8, 1, 0, 3 };
		int[] validAccountNumberAlternative3_2 = { 3, 0, 7, 0, 4, 0, 2, 0, 2, 3 };

		// Invalid account numbers for alternative 3
		int[] invalidAccountNumberAlternative3_1 = { 3, 0, 8, 1, 0, 0, 0, 7, 8,
				3 };
		int[] invalidAccountNumberAlternative3_2 = { 3, 0, 8, 1, 3, 0, 8, 8, 7,
				1 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_3, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_4, null))
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
		assertTrue((validator.validate(validAccountNumberAlternative2_6, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_7, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_8, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_9, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_X, null))
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
		assertFalse((validator.validate(invalidAccountNumberAlternative2_6,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_7,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_8,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_9,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_X,
				null))
				&& (validator.getAlternative() == 1));

		// Should be valid using alternative 3
		assertTrue((validator.validate(validAccountNumberAlternative3_1, null))
				&& (validator.getAlternative() == 2));
		assertTrue((validator.validate(validAccountNumberAlternative3_2, null))
				&& (validator.getAlternative() == 2));

		// Should be invalid using alternative 3
		assertFalse((validator.validate(invalidAccountNumberAlternative3_1,
				null))
				&& (validator.getAlternative() == 2));
		assertFalse((validator.validate(invalidAccountNumberAlternative3_2,
				null))
				&& (validator.getAlternative() == 2));

		// Should be valid using alternative 4
		checkRangeOfAccountNumbers(30000000l, 59999999l, null, 3, false, validator, 10000);

	}
}
