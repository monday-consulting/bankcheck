package hx.bankcheck.accountvalidator.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.IllegalAccountNumberException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import org.junit.Test;

public class ChecksumUtilsTest {

	@Test
	public void testGetFilledAcountNumber() {
		int[] orignalAccountNumber = { 1, 2, 3, 4, 5, 6 };
		int sizeOfAccountNumber = 10;
		int[] actuals = ChecksumUtils.getFilledAcountNumber(
				sizeOfAccountNumber, orignalAccountNumber);
		int[] expecteds = { 0, 0, 0, 0, 1, 2, 3, 4, 5, 6 };

		assertArrayEquals(expecteds, actuals);

		actuals = ChecksumUtils.getFilledAcountNumber(
				orignalAccountNumber.length, orignalAccountNumber);

		assertArrayEquals(orignalAccountNumber, actuals);

		actuals = ChecksumUtils.getFilledAcountNumber(0, orignalAccountNumber);

		assertArrayEquals(orignalAccountNumber, actuals);

	}

	@Test
	public void testQs() {
		int a = 0; // should return 0
		int b = 2; // should return 2
		int c = 13; // should return 4
		int d = 93; // should return 12
		int e = 1234567; // should return 28

		assertTrue(ChecksumUtils.qs(a) == 0);
		assertTrue(ChecksumUtils.qs(b) == 2);
		assertTrue(ChecksumUtils.qs(c) == 4);
		assertTrue(ChecksumUtils.qs(d) == 12);
		assertTrue(ChecksumUtils.qs(e) == 28);

		assertTrue(ChecksumUtils.qs(-a) == -0);
		assertTrue(ChecksumUtils.qs(-b) == -2);
		assertTrue(ChecksumUtils.qs(-c) == -4);
		assertTrue(ChecksumUtils.qs(-d) == -12);
		assertTrue(ChecksumUtils.qs(-e) == -28);

		ChecksumUtils.qs(Integer.MAX_VALUE);
		ChecksumUtils.qs(-Integer.MAX_VALUE);

	}

	@Test
	public void testParseAccountNumber() throws IllegalAccountNumberException {
		Long a = new Long("0000000012");
		Long b = new Long("0123456789");
		Long c = new Long("12345");

		int[] a_expected = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 2 };
		int[] b_expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] c_expected = { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5 };

		assertArrayEquals(a_expected, ChecksumUtils.parseAccountNumber(a));
		assertArrayEquals(b_expected, ChecksumUtils.parseAccountNumber(b));
		assertArrayEquals(c_expected, ChecksumUtils.parseAccountNumber(c));
	}

	@Test
	public void testParseLong() {
		int[] accountNumber1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] accountNumber2 = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int[] accountNumber3 = { 0, 0, 3, 0, 0 };
		int[] accountNumber4 = { 0, 9, 1 };
		int[] accountNumber5 = { 0 };

		assertEquals(1234567890l, ChecksumUtils.parseLong(accountNumber1));
		assertEquals(234567890l, ChecksumUtils.parseLong(accountNumber2));
		assertEquals(300l, ChecksumUtils.parseLong(accountNumber3));
		assertEquals(91l, ChecksumUtils.parseLong(accountNumber4));
		assertEquals(0l, ChecksumUtils.parseLong(accountNumber5));
	}

	@Test
	public void testcountNeutralLeadingDigits(){

		int[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 2 };
		int[] b = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] c = { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5 };

		assertTrue(ChecksumUtils.countNeutralLeadingDigits(a)==8);
		assertTrue(ChecksumUtils.countNeutralLeadingDigits(b)==1);
		assertTrue(ChecksumUtils.countNeutralLeadingDigits(c)==5);
	}

}
