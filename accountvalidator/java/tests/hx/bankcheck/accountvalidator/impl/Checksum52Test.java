package hx.bankcheck.accountvalidator.impl;

import static org.junit.Assert.assertArrayEquals;
import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.IllegalAccountNumberException;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum52;

import org.junit.Test;

/**
 * Testclass for testing algorithm 52.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum52Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		Checksum52 checksum = new Checksum52();

		// Valid account number + bank number pair
		int[] validAccountNumber = { 0, 0, 4, 3, 0, 0, 1, 5, 0, 0 };
		int[] validbankNumber = { 1, 3, 0, 5, 1, 1, 7, 2 };

		// invalid account number + bank number pair
		int[] invalidAccountNumber = { 0, 0, 4, 2, 0, 0, 1, 5, 0, 0 };
		int[] invalidBankNumber = { 1, 3, 0, 5, 1, 1, 7, 2 };

		assertTrue(checksum.validate(validAccountNumber, validbankNumber));
		assertFalse(checksum.validate(invalidAccountNumber, invalidBankNumber));
	}

	@Test
	public void testGenerateEserAccountNumber()
			throws IllegalAccountNumberException {

		Checksum52 checksum = new Checksum52();

		int[] accountNumber = { 0, 0, 4, 0, 0, 0, 1, 5, 0, 0 };
		int[] bankNumber = { 1, 3, 0, 5, 1, 1, 7, 2 };
		int[] expectedOldBankAccount = { 0, 0, 1, 1, 7, 2, 4, 0, 1, 5, 0, 0 };

		assertArrayEquals(expectedOldBankAccount, checksum
				.generateEserAccountNumber(accountNumber, bankNumber));
	}

	@Test
	public void resetChecksumDigit() {

		Checksum52 checksum = new Checksum52();

		int[] accountNumber1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] accountNumber2 = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] accountNumber3 = { 0, 0, 3, 0, 0 };
		int[] accountNumber4 = { 0, 9, 1 };
		int[] accountNumber5 = { 0 };

		int[] expectedAccountNumber1 = { 1, 2, 3, 4, 5, 0, 7, 8, 9, 0 };
		int[] expectedAccountNumber2 = { 0, 2, 3, 4, 5, 6, 0, 8, 9, 0 };
		int[] expectedAccountNumber3 = { 0, 0, 3, 0, 0 };
		int[] expectedAccountNumber4 = { 0, 9, 0 };
		int[] expectedAccountNumber5 = { 0 };

		assertArrayEquals(expectedAccountNumber1, checksum.resetChecksumDigit(
				accountNumber1, 6));
		assertEquals(5, checksum.getChecksumDigitIndex());

		assertArrayEquals(expectedAccountNumber2, checksum.resetChecksumDigit(
				accountNumber2, 6));
		assertEquals(6, checksum.getChecksumDigitIndex());

		assertArrayEquals(expectedAccountNumber3, checksum.resetChecksumDigit(
				accountNumber3, 6));
		assertEquals(-1, checksum.getChecksumDigitIndex());

		assertArrayEquals(expectedAccountNumber4, checksum.resetChecksumDigit(
				accountNumber4, 2));
		assertEquals(2, checksum.getChecksumDigitIndex());

		assertArrayEquals(expectedAccountNumber5, checksum.resetChecksumDigit(
				accountNumber5, 6));
		assertEquals(-1, checksum.getChecksumDigitIndex());
	}
}
