package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumC3;

/**
 * Testclass for testing algorithm C3.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC3Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumC3();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 9, 2, 9, 4, 1, 8, 2 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 0, 4, 4, 3, 1, 2, 7, 6 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 0, 0, 0, 1, 9, 9, 1, 9 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 0, 0, 1, 7, 0, 0,
				2 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 0, 0, 1, 2, 3, 4, 5,
				1 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 0, 1, 2, 2, 4, 4, 8,
				2 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 9, 0, 0, 0, 4, 2, 0, 5, 3, 0 };
		int[] validAccountNumberAlternative2_2 = { 9, 0, 0, 0, 0, 1, 0, 0, 0, 6 };
		int[] validAccountNumberAlternative2_3 = { 9, 0, 0, 0, 5, 7, 7, 6, 5, 0 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 9, 0, 0, 0, 7, 3, 4, 0, 2,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 9, 0, 0, 0, 7, 3, 3, 2, 2,
				7 };
		int[] invalidAccountNumberAlternative2_3 = { 9, 0, 0, 0, 7, 3, 1, 1, 2,
				0 };

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
