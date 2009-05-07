package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertArrayEquals;
import hx.bankcheck.accountvalidator.exceptions.IllegalAccountNumberException;
import hx.bankcheck.accountvalidator.impl.Checksum53;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 53.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum53Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum53 checksum = new Checksum53();

		// Valid account number + bank number pair
		int[] validAccountNumber = { 0, 3, 8, 2, 4, 3, 2, 2, 5, 6 };
		int[] validbankNumber = { 1, 6, 0, 5, 2, 0, 7, 2 };

		// invalid account number + bank number pair
		int[] invalidAccountNumber = { 0, 3, 8, 1, 4, 3, 2, 2, 5, 6 };
		int[] invalidBankNumber = { 1, 6, 0, 5, 2, 0, 7, 2 };

		assertTrue(checksum.validate(validAccountNumber, validbankNumber));
		assertFalse(checksum.validate(invalidAccountNumber, invalidBankNumber));
	}

	@Test
	public void testGenerateEserAccountNumber()
			throws IllegalAccountNumberException {

		Checksum53 checksum = new Checksum53();

		int[] accountNumber = { 0, 3, 8, 0, 4, 3, 2, 2, 5, 6 };
		int[] bankNumber = { 1, 6, 0, 5, 2, 0, 7, 2 };
		int[] expectedOldBankAccount = { 2, 0, 8, 2, 3, 0, 4, 3, 2, 2, 5, 6 };

		assertArrayEquals(expectedOldBankAccount, checksum
				.generateEserAccountNumber(accountNumber, bankNumber));
	}

	@Test
	public void resetChecksumDigit() {

		Checksum53 checksum = new Checksum53();

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
