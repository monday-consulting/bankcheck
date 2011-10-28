package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumC2;

/**
 * Testclass for testing algorithm C2.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC2Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumC2();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 2, 3, 9, 4, 8, 7, 1, 4, 2, 6 };
		int[] validAccountNumberAlternative1_2 = { 4, 2, 1, 8, 4, 6, 1, 9, 5, 0 };
		int[] validAccountNumberAlternative1_3 = { 7, 3, 5, 2, 5, 6, 9, 1, 4, 8 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 5, 1, 2, 7, 4, 8, 5, 1, 6,
				6 };
		int[] invalidAccountNumberAlternative1_2 = { 8, 7, 3, 8, 1, 4, 2, 5, 6,
				4 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 3, 2, 8, 7, 0, 5, 2, 8,
				2 };
		int[] invalidAccountNumberAlternative1_4 = { 9, 0, 2, 4, 6, 7, 5, 1, 3,
				1 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 5, 1, 2, 7, 4, 8, 5, 1, 6, 6 };
		int[] validAccountNumberAlternative2_2 = { 8, 7, 3, 8, 1, 4, 2, 5, 6, 4 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 3, 2, 8, 7, 0, 5, 2, 8,
				2 };
		int[] invalidAccountNumberAlternative2_2 = { 9, 0, 2, 4, 6, 7, 5, 1, 3,
				1 };

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

		// Should be invalid using alternative 2
		assertFalse((validator.validate(invalidAccountNumberAlternative2_1,
				null))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_2,
				null))
				&& (validator.getAlternative() == 1));

	}
}
