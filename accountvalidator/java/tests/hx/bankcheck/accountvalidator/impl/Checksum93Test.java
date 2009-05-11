package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum93;

/**
 * Testclass for testing algorithm 93.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum93Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		Checksum93 checksum = new Checksum93();

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
		assertTrue((checksum.validate(validAccountNumberAlternative1a))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 1 case b)
		assertTrue((checksum.validate(validAccountNumberAlternative1b))
				&& (checksum.getAlternative() == 1));

		// Should be valid using alternative 2 case a)
		assertTrue((checksum.validate(validAccountNumberAlternative21))
				&& (checksum.getAlternative() == 2));

		assertTrue((checksum.validate(validAccountNumberAlternative23))
				&& (checksum.getAlternative() == 2));

		// Should be valid using alternative 2 case a)
		assertTrue((checksum.validate(validAccountNumberAlternative2b1))
				&& (checksum.getAlternative() == 3));

		assertTrue((checksum.validate(validAccountNumberAlternative2b2))
				&& (checksum.getAlternative() == 3));

		// Should be valid without regarding the alternatives
		assertTrue(checksum.validate(validAccountNumber1));
		assertTrue(checksum.validate(validAccountNumber2));

	}

}
