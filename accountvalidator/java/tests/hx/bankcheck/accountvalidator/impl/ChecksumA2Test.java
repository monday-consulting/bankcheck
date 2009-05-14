package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumA2;

/**
 * Testclass for testing algorithm A2.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA2Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumA2();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 5, 6, 7, 8, 9, 0, 1, 2, 3, 1 };
		int[] validAccountNumberAlternative1_2 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 9 };
		int[] validAccountNumberAlternative1_3 = { 6, 7, 8, 9, 0, 1, 2, 3, 4, 8 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 };

		// Invalid account numbers
		int[] invalidAccountNumber1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] invalidAccountNumber2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_3, null))
				&& (validator.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((validator.validate(invalidAccountNumberAlternative1, null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((validator.validate(validAccountNumberAlternative2_1, null))
				&& (validator.getAlternative() == 1));

		// Should be invalid
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));

	}

}
