package hx.bankcheck.accountvalidator.utils;

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

	/**
	 * Parses the given account number as long value for comparing it.
	 * 
	 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
	 * @version 1.0
	 * 
	 * @param acccountNumber
	 *            account number to parse
	 * @return long value of the given account number
	 */
	public static long parseLong(int[] accountNumber) {
		int[] filledAccountNumber = ChecksumUtils.getFilledAcountNumber(10,
				accountNumber);
		String tmp = "";
		for (int i = 0; i < filledAccountNumber.length; i++) {
			tmp += filledAccountNumber[i];
		}
		return Long.parseLong(tmp);
	}

	/**
	 * Parses the given account number as int[].
	 * 
	 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
	 * @version 1.0
	 * 
	 * @param accountNumberAsLong
	 *            account number to parse
	 * @return int[] containing the account number
	 */
	public static int[] parseAccountNumber(long accountNumberAsLong) {
		int[] result=null;
		do {
			int intValue=new Long(accountNumberAsLong%10).intValue();
			result=addIntToArray(intValue, result);
			accountNumberAsLong/=10;
		} while (accountNumberAsLong!=0);
		return result;
	}

	private static int[] addIntToArray(int intValue, int[] intArray) {
		int[] tmp; 
		if(intArray==null){
			tmp=new int[1];
		}else {
			tmp=new int[intArray.length + 1];
		}
		tmp[0] = intValue;
		for (int i = 0; i < tmp.length-1; i++) {
			tmp[i+1] = intArray[i];
		}
		return tmp;
	}

}
