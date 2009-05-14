package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum90;

/**
 * Testclass for testing algorithm 90.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum90Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator validator = new Checksum90();

		// Valid account numbers for testing method A
		int[] validAccountNumberA1 = { 0, 0, 0, 1, 9, 7, 5, 6, 4, 1 };
		int[] validAccountNumberA2 = { 0, 0, 0, 1, 9, 8, 8, 6, 5, 4 };
		int[] validAccountNumberNotUsingA = { 0, 0, 0, 0, 6, 5, 4, 3, 2, 1 };

		// Valid account numbers for testing method B
		int[] validAccountNumberB1 = { 0, 0, 0, 0, 8, 6, 3, 5, 3, 0 };
		int[] validAccountNumberB2 = { 0, 0, 0, 0, 7, 8, 4, 4, 5, 1 };
		int[] validAccountNumberNotUsingB1 = { 0, 0, 0, 0, 9, 9, 7, 6, 6, 4 };
		int[] validAccountNumberNotUsingB2 = { 0, 0, 0, 0, 8, 6, 3, 5, 3, 6 };

		// Valid account numbers for testing method C
		int[] validAccountNumberC1 = { 0, 0, 0, 0, 6, 5, 4, 3, 2, 1 };
		int[] validAccountNumberC2 = { 0, 0, 0, 0, 8, 2, 4, 4, 9, 1 };
		int[] validAccountNumberNotUsingC1 = { 0, 0, 0, 0, 8, 2, 0, 4, 8, 4 };
		int[] validAccountNumberNotUsingC2 = { 0, 0, 0, 0, 6, 5, 4, 3, 2, 8 };

		// Valid account numbers for testing method D
		int[] validAccountNumberD1 = { 0, 0, 0, 0, 6, 7, 7, 7, 4, 7 };
		int[] validAccountNumberD2 = { 0, 0, 0, 0, 8, 4, 0, 5, 0, 7 };
		int[] validAccountNumberNotUsingD1 = { 0, 0, 0, 0, 6, 7, 7, 7, 4, 2 };
		int[] validAccountNumberNotUsingD2 = { 0, 0, 0, 0, 7, 2, 6, 3, 9, 1 };

		// Valid account numbers for testing method E
		int[] validAccountNumberE1 = { 0, 0, 0, 0, 9, 9, 6, 6, 6, 3 };
		int[] validAccountNumberE2 = { 0, 0, 0, 0, 6, 6, 6, 0, 3, 4 };

		// Valid account numbers for testing method F
		int[] validAccountNumberF1 = { 0, 0, 9, 9, 1, 0, 0, 0, 0, 2 };
		int[] validAccountNumberF2 = { 0, 0, 9, 9, 1, 0, 0, 0, 0, 2 };

		// Invalid AccoutNumbers for all Methods
		int[] invalidAccountNumber1 = { 0, 0, 0, 1, 9, 2, 4, 5, 9, 2 };
		int[] invalidAccountNumber2 = { 0, 0, 0, 0, 9, 0, 1, 5, 6, 8 };
		int[] invalidAccountNumber3 = { 0, 0, 0, 0, 8, 2, 0, 4, 8, 7 };
		int[] invalidAccountNumber4 = { 0, 0, 0, 0, 7, 2, 6, 3, 9, 3 };
		int[] invalidAccountNumber5 = { 0, 0, 0, 0, 9, 2, 4, 5, 9, 1 };

		// Should be valid using method A
		assertTrue(validator.validate(validAccountNumberA1, null)
				&& (validator.getAlternative() <= 0));
		assertTrue(validator.validate(validAccountNumberA2, null)
				&& (validator.getAlternative() <= 0));
		// Should be valid but not using method A
		assertTrue(validator.validate(validAccountNumberNotUsingA, null)
				&& (validator.getAlternative() == 2));

		// Should be valid using method B
		assertTrue(validator.validate(validAccountNumberB1, null)
				&& (validator.getAlternative() <= 1));
		assertTrue(validator.validate(validAccountNumberB2, null)
				&& (validator.getAlternative() <= 1));
		// Should be valid but not using method B
		assertTrue(validator.validate(validAccountNumberNotUsingB1, null)
				&& (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberNotUsingB2, null)
				&& (validator.getAlternative() == 3));

		// Should be valid using method C
		assertTrue(validator.validate(validAccountNumberC1, null)
				&& (validator.getAlternative() <= 2));
		assertTrue(validator.validate(validAccountNumberC2, null)
				&& (validator.getAlternative() <= 2));
		// Should be valid but not using method C
		assertTrue(validator.validate(validAccountNumberNotUsingC1, null)
				&& (validator.getAlternative() == 3));
		assertTrue(validator.validate(validAccountNumberNotUsingC2, null)
				&& (validator.getAlternative() == 4));

		// Should be valid using method D
		assertTrue(validator.validate(validAccountNumberD1, null)
				&& (validator.getAlternative() <= 3));
		assertTrue(validator.validate(validAccountNumberD2, null)
				&& (validator.getAlternative() <= 3));
		// Should be valid but not using method D
		assertTrue(validator.validate(validAccountNumberNotUsingD1, null)
				&& (validator.getAlternative() == 4));
		assertTrue(validator.validate(validAccountNumberNotUsingD2, null)
				&& (validator.getAlternative() == 4));

		// Should be valid using method E
		assertTrue(validator.validate(validAccountNumberE1, null)
				&& (validator.getAlternative() <= 4));
		assertTrue(validator.validate(validAccountNumberE2, null)
				&& (validator.getAlternative() <= 4));

		// Should be valid using method F
		assertTrue(validator.validate(validAccountNumberF1, null)
				&& (validator.getAlternative() == 5));
		assertTrue(validator.validate(validAccountNumberF2, null)
				&& (validator.getAlternative() == 5));

		// Should be invalid in any case
		assertFalse(validator.validate(invalidAccountNumber1, null));
		assertFalse(validator.validate(invalidAccountNumber2, null));
		assertFalse(validator.validate(invalidAccountNumber3, null));
		assertFalse(validator.validate(invalidAccountNumber4, null));
		assertFalse(validator.validate(invalidAccountNumber5, null));
	}
}
