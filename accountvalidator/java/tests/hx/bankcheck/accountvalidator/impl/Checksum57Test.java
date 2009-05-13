package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Testclass for testing algorithm 58.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum57Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum57();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 7, 5, 0, 0, 0, 2, 1, 7, 6, 6 };
		int[] validAccountNumberAlternative1_2 = { 9, 4, 0, 0, 0, 0, 1, 7, 3, 4 };
		int[] validAccountNumberAlternative1_3 = { 7, 8, 0, 0, 0, 2, 8, 2, 8, 2 };
		int[] validAccountNumberAlternative1_4 = { 8, 1, 0, 0, 2, 4, 4, 1, 8, 6 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 3, 2, 5, 1, 0, 8, 0, 3, 7, 1 };
		int[] validAccountNumberAlternative2_2 = { 3, 8, 9, 1, 2, 3, 4, 5, 6, 7 };
		int[] validAccountNumberAlternative2_3 = { 9, 3, 2, 2, 1, 1, 1, 0, 3, 0 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 5, 0, 0, 1, 0, 5, 0, 3, 5, 2 };
		int[] validAccountNumberAlternative3_2 = { 5, 0, 4, 5, 0, 9, 0, 0, 9, 0 };

		// Valid account numbers for alternative 4
		int[] validAccountNumberAlternative4_1 = { 1, 9, 0, 9, 7, 0, 0, 8, 0, 5 };

		// Valid exceptional account Numbers
		int[] validException1 = { 7, 7, 7, 7, 7, 7, 8, 8, 0, 0 };
		int[] validException2 = { 0, 1, 8, 5, 1, 2, 5, 4, 3, 4 };

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberAlternative1_1, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_2, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_3, null)
				&& (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternative1_4, null)
				&& (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberAlternative2_1, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_2, null)
				&& (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternative2_3, null)
				&& (validator.getAlternative() == 1));

		// Should be valid for alternative 3
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternative3_2, null)
				&& (validator.getAlternative() == 2));

		// Should be valid for alternative 4
		assertTrue(validator.validate(validAccountNumberAlternative4_1, null)
				&& (validator.getAlternative() == 3));

		// Should be valid as exception
		assertTrue(validator.validate(validException1, null)
				&& (validator.getAlternative() == 3));
		assertTrue(validator.validate(validException2, null)
				&& (validator.getAlternative() == 3));

	}
}
