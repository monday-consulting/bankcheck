package hx.bankcheck.accountvalidator;

public class ChecksumUtils {

	/**
	 * Calculates the crossfoot of a number
	 * 
	 * @param number
	 * @return
	 */
	public static int qs(int number) {
		int qs = 0;

		while (number != 0) {
			qs = qs + (number % 10);
			number = number / 10;
		}

		return qs;
	}

	/**
	 * Fills up the account number by adding '0' to the left until size is
	 * reached.
	 * 
	 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
	 * @version 1.0
	 * 
	 * @param sizeOfAccountNumber
	 *            new size of the account number
	 * @param orignalAccountNumber
	 *            account number to be filled
	 * @return array containing the new account number
	 */
	public static int[] getFilledAcountNumber(int sizeOfAccountNumber,
			int[] orignalAccountNumber) {
		if (sizeOfAccountNumber > orignalAccountNumber.length) {
			int[] filledAccountNumber = new int[sizeOfAccountNumber];
			int offset = sizeOfAccountNumber - orignalAccountNumber.length;
			for (int i = 0; i < orignalAccountNumber.length; i++) {
				filledAccountNumber[i + offset] = orignalAccountNumber[i];
			}
			return filledAccountNumber;
		} else
			return orignalAccountNumber;
	}
}
