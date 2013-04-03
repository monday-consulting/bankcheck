package hx.bankcheck.ibanvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.math.BigInteger;

public class ChecksumIBAN {
	
	private static final BigInteger BI_97 = new BigInteger("97");
	
	private BigInteger convertToNumeric(String iban) {
		char[] ibanChars = (iban.substring(4) + iban.substring(0, 4)).toCharArray();	
		StringBuilder buffer = new StringBuilder();
		
		for (Character ch : ibanChars) {
			buffer.append(getAsNumeric(ch));
		}
		return new BigInteger(buffer.toString());
	}
	
    private static int getAsNumeric(char letter) {
    	return Character.digit(letter, 36);
    }	
    
	/**
	 * Checks an IBAN for correctness
	 * 
	 * @param iban The IBAN.
	 * @return true, if the IBAN is valid, otherwise false.
	 */
	public boolean validate(String iban) throws ValidationException {	
		BigInteger ibanNumeric = convertToNumeric(iban);
		return ibanNumeric.mod(BI_97).intValue() == 1;
	}
}
