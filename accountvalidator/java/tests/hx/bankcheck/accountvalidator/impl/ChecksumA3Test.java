package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA3;

/**
 * Testclass for testing algorithm A3.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA3Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumA3();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 7 };
		int[] validAccountNumberAlternative1_2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 9, 8, 7, 6, 5, 4, 3, 2, 1,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative1_3 = { 6, 5, 4, 3, 2, 1, 7, 8, 9,
				0 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 5, 4, 3, 2, 1, 6, 7, 8,
				9 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int[] validAccountNumberAlternative2_2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] validAccountNumberAlternative2_3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 6, 5, 4, 3, 2, 1, 7, 8, 9, 0 };
		int[] invalidAccountNumber2 = { 0, 5, 4, 3, 2, 1, 6, 7, 8, 9 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
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

		// Should be invalid
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));

	}

}
