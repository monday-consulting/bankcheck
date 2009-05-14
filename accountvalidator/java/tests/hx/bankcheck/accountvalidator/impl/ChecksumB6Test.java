package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB6;

/**
 * Testclass for testing algorithm B7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB6Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumB6();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 9, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 9, 1, 1, 1, 0, 0, 0, 0, 0,
				0 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 4, 8, 7, 3, 1, 0, 0, 1, 8 };
		int[] validBankNumberAlternative2_1 = { 8, 0, 0, 5, 3, 7, 8, 2 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 4, 6, 7, 3, 1, 0, 0, 1,
				8 };
		int[] invalidAccountNumberAlternative2_2 = { 0, 4, 7, 7, 3, 1, 0, 0, 1,
				8 };
		int[] invalidBankNumberAlternative2_1 = { 8, 0, 0, 5, 3, 7, 6, 2 };
		int[] invalidBankNumberAlternative2_2 = { 8, 0, 0, 5, 3, 7, 6, 2 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((validator
				.validate(invalidAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((validator.validate(validAccountNumberAlternative2_1, validBankNumberAlternative2_1))
				&& (validator.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((validator.validate(invalidAccountNumberAlternative2_1, invalidBankNumberAlternative2_1))
				&& (validator.getAlternative() == 1));
		assertFalse((validator.validate(invalidAccountNumberAlternative2_2, invalidBankNumberAlternative2_2))
				&& (validator.getAlternative() == 1));

	}
}
