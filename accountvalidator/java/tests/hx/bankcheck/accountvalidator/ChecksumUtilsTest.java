package hx.bankcheck.accountvalidator;

import static org.junit.Assert.*;

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
}
