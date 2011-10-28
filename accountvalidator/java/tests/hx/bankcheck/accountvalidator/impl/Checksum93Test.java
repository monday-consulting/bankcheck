package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum93;

/**
 * Testclass for testing algorithm 93.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum93Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum93();

		// Valid account numbers for testing alternative 1
		int[] validAccountNumberAlternative1a = { 6, 7, 1, 4, 7, 9, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative1b = { 0, 0, 0, 0, 6, 7, 1, 4, 7, 9 };

		// Valid account numbers for testing alternative 2
		int[] validAccountNumberAlternative21 = { 1, 2, 7, 7, 8, 3, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative2b1 = { 0, 0, 0, 0, 1, 2, 7, 7, 8, 3 };
		int[] validAccountNumberAlternative23 = { 1, 2, 7, 7, 9, 1, 0, 0, 0, 0 };
		int[] validAccountNumberAlternative2b2 = { 0, 0, 0, 0, 1, 2, 7, 7, 9, 1 };

		// Valid account numbers for testing alternative 1 and alternative 2
		int[] validAccountNumber1 = { 3, 0, 6, 7, 5, 4, 0, 0, 0, 0 };
		int[] validAccountNumber2 = { 0, 0, 0, 0, 3, 0, 6, 7, 5, 4 };

		// Should be valid using alternative 1 case a)
		assertTrue((validator.validate(validAccountNumberAlternative1a, null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 1 case b)
		assertTrue((validator.validate(validAccountNumberAlternative1b, null))
				&& (validator.getAlternative() == 1));

		// Should be valid using alternative 2 case a)
		assertTrue((validator.validate(validAccountNumberAlternative21, null))
				&& (validator.getAlternative() == 2));

		assertTrue((validator.validate(validAccountNumberAlternative23, null))
				&& (validator.getAlternative() == 2));

		// Should be valid using alternative 2 case a)
		assertTrue((validator.validate(validAccountNumberAlternative2b1, null))
				&& (validator.getAlternative() == 3));

		assertTrue((validator.validate(validAccountNumberAlternative2b2, null))
				&& (validator.getAlternative() == 3));

		// Should be valid without regarding the alternatives
		assertTrue(validator.validate(validAccountNumber1, null));
		assertTrue(validator.validate(validAccountNumber2, null));

	}

}
