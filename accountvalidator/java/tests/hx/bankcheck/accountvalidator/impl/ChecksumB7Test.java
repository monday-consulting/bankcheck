package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB7;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import java.util.Random;

/**
 * Testclass for testing algorithm B7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB7Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		ChecksumB7 checksum = new ChecksumB7();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 7, 0, 0, 0, 0, 1, 5, 2, 9 };
		int[] validAccountNumberAlternative1_2 = { 0, 7, 3, 0, 0, 0, 0, 0, 1, 9 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 0, 1, 0, 0, 1, 0, 0, 8 };
		int[] validAccountNumberAlternative1_4 = { 0, 0, 0, 1, 0, 5, 7, 8, 8, 7 };
		int[] validAccountNumberAlternative1_5 = { 0, 0, 0, 1, 0, 0, 7, 2, 2, 2 };
		int[] validAccountNumberAlternative1_6 = { 0, 8, 1, 0, 0, 1, 1, 8, 2, 5 };
		int[] validAccountNumberAlternative1_7 = { 0, 8, 0, 0, 1, 0, 7, 6, 5, 3 };
		int[] validAccountNumberAlternative1_8 = { 0, 0, 0, 5, 9, 2, 2, 3, 7, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 1, 0, 5, 7, 8, 8,
				6 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 0, 3, 8, 1, 5, 5, 7,
				0 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 0, 5, 6, 2, 0, 5, 1,
				6 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 7, 4, 0, 9, 1, 2, 2, 4,
				3 };
		int[] invalidAccountNumberAlternative1_5 = { 0, 8, 9, 3, 5, 2, 4, 4, 7,
				9 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_4))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_5))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_6))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_7))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_8))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_4))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_5))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		Random rnd = new Random();
		int range = 1000000 - 1;
		int i = 0;

		while (i++ < 10000) {
			long testNumber = 1 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils
					.parseAccountNumber(testNumber))
					&& checksum.getAlternative() == 1);
		}

		range = 700000000 - 6000000;
		i = 0;

		while (i++ < 10000) {
			long testNumber = 6000000 + (int) (rnd.nextDouble() * range);
			assertTrue(checksum.validate(ChecksumUtils
					.parseAccountNumber(testNumber))
					&& checksum.getAlternative() == 1);
		}
		
	}
}
