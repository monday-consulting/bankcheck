package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumC0;

/**
 * Testclass for testing algorithm C0.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC0Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumC0 checksum = new ChecksumC0();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 0, 4, 3, 0, 0, 1, 5, 0, 0 };
		int[] validAccountNumberAlternative1_2 = { 0, 0, 4, 8, 7, 2, 6, 4, 5, 8 };
		int[] validBankNumberAlternative1 = { 1, 3, 0, 5, 1, 1, 7, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 8, 2, 3, 3, 5, 7, 2,
				9 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 2, 9, 8, 3, 7, 5, 2,
				1 };
		int[] invalidBankNumberAlternative1 = { 1, 3, 0, 5, 1, 1, 7, 2 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 0, 0, 8, 2, 3, 3, 5, 7, 2, 9 };
		int[] validAccountNumberAlternative2_2 = { 0, 7, 3, 4, 1, 9, 2, 6, 5, 7 };
		int[] validAccountNumberAlternative2_3 = { 6, 9, 3, 2, 8, 7, 5, 2, 7, 4 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 0, 1, 3, 2, 5, 7, 2, 9, 7,
				5 };
		int[] invalidAccountNumberAlternative2_2 = { 3, 0, 3, 8, 7, 5, 2, 3, 7,
				1 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1,
				validBankNumberAlternative1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2,
				validBankNumberAlternative1))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_1,
				invalidBankNumberAlternative1))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_2,
				invalidBankNumberAlternative1))
				&& (checksum.getAlternative() == 0));

		 // Should be valid using alternative 2
		 assertTrue((checksum.validate(validAccountNumberAlternative2_1,validBankNumberAlternative1))
		 && (checksum.getAlternative() == 1));
		 assertTrue((checksum.validate(validAccountNumberAlternative2_2,validBankNumberAlternative1))
		 && (checksum.getAlternative() == 1));
		 assertTrue((checksum.validate(validAccountNumberAlternative2_3,validBankNumberAlternative1))
		 && (checksum.getAlternative() == 1));
		
		 // Should be invalid using alternative 2
		 assertFalse((checksum.validate(invalidAccountNumberAlternative2_1,invalidBankNumberAlternative1))
		 && (checksum.getAlternative() == 1));
		 assertFalse((checksum.validate(invalidAccountNumberAlternative2_2,invalidBankNumberAlternative1))
		 && (checksum.getAlternative() == 1));

	}
}
