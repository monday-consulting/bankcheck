package hx.bankcheck.accountvalidator;

public class ChecksumUtils {
	
	/**
	 * Calculates the crossfoot of a number
	 * @param number
	 * @return
	 */
	public static int qs(int number) {
		int qs = 0;
		
		while(number != 0) {
			qs = qs + (number % 10);
			number = number / 10;
		}
		
		return qs;
	}
	
}
