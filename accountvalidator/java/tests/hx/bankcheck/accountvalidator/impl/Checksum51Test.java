package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum51;

/**
 * Testclass for testing algorithm 51.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum51Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum51();

		// Valid account numbers for alternative A
		int[] validAccountNumberAlternativeA_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };
		int[] validAccountNumberAlternativeA_2 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };

		// Valid account numbers for alternative B
		int[] validAccountNumberAlternativeB_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 8 };

		// Valid account numbers for alternative C
		int[] validAccountNumberAlternativeC_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 1 };

		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 2 };
		int[] validAccountNumberExceptionAlternative1_2 = { 0, 0, 9, 9, 1, 0,
				0, 0, 1, 0 };
		int[] validAccountNumberExceptionAlternative1_3 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 2 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 4 };
		int[] invalidAccountNumberExceptionAlternative1_2 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 3 };
		int[] invalidAccountNumberExceptionAlternative1_3 = { 0, 0, 9, 9, 3, 4,
				5, 6, 7, 8 };

		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative2_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 4 };
		int[] validAccountNumberExceptionAlternative2_2 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 3 };
		int[] validAccountNumberExceptionAlternative2_3 = { 3, 1, 9, 9, 2, 0,
				4, 0, 9, 0 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative2_1 = { 0, 0, 9, 9, 3, 4,
				5, 6, 7, 8 };
		int[] invalidAccountNumberExceptionAlternative2_2 = { 0, 0, 9, 9, 1, 0,
				0, 1, 1, 0 };
		int[] invalidAccountNumberExceptionAlternative2_3 = { 0, 1, 9, 9, 1, 0,
				0, 0, 4, 0 };

		// Should be valid for alternative A
		assertTrue(validator.validate(validAccountNumberAlternativeA_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternativeA_2, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative B (also valid for alternative A)
		assertTrue(validator.validate(validAccountNumberAlternativeB_1, null)
				&& (validator.getAlternative() <= 1));

		// Should be valid for alternative C
		assertTrue(validator.validate(validAccountNumberAlternativeC_1, null)
				&& (validator.getAlternative() == 2));

		// Should be valid for alternative 1
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative1_3, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 1
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative1_3, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative2_2, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(
				validAccountNumberExceptionAlternative2_3, null)
				&& (validator.getAlternative() == 1));

		// Should be valid for alternative 2
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative2_2, null)
				&& (validator.getAlternative() == 1));
		assertFalse(validator.validate(
				invalidAccountNumberExceptionAlternative2_3, null)
				&& (validator.getAlternative() == 1));

	}
}
