package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Testclass for testing algorithm 96.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum96Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum96();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 0, 0, 2, 5, 4, 1, 0, 0 };
		int[] validAccountNumberAlternative1_2 = { 9, 4, 2, 1, 0, 0, 0, 0, 0, 9 };

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative2_1 = { 0, 0, 0, 0, 0, 0, 0, 2, 0, 8 };
		int[] validAccountNumberAlternative2_2 = { 0, 1, 0, 1, 1, 1, 5, 1, 5, 2 };
		int[] validAccountNumberAlternative2_3 = { 0, 3, 0, 1, 2, 0, 4, 3, 0, 1 };

		// Valid account numbers for alternative 3
		int[] validAccountNumberAlternative3_1 = { 0, 0, 0, 1, 3, 0, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative3_2 = { 0, 0, 0, 1, 3, 0, 0, 0, 2, 2 };
		int[] validAccountNumberAlternative3_3 = { 0, 0, 9, 9, 3, 9, 9, 9, 7, 7 };
		int[] validAccountNumberAlternative3_4 = { 0, 0, 9, 9, 3, 9, 9, 9, 9, 9 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1, null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2, null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative2_1, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_2, null))
				&& (validator.getAlternative() == 1));
		assertTrue((validator.validate(validAccountNumberAlternative2_3, null))
				&& (validator.getAlternative() == 1));

		// Exceptions should be valid
		assertTrue(validator.validate(validAccountNumberAlternative3_1, null)
				&& (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternative3_2, null)
				&& (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternative3_3, null)
				&& (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternative3_4, null)
				&& (validator.getAlternative() == 2));

	}
}
