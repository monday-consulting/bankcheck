/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. <br/>
 * 
 * <b>Variante 1:</b> <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Kontonummern, die an der 1. Stelle von links
 * der 10-stelligen Kontonummer den Wert 0 bis 8 beinhalten sind nach der
 * Methode 32 zu rechen. <br/>
 * 
 * Testkontonummern (richtig): 1000000060, 0000000140, 0000000019, 1002798417,
 * 8409915001 <br/>
 * Testkontonummern (falsch): 0002799899, 1000000111 <br/>
 * 
 * <b>Variante 2: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer den
 * Wert 9 beinhalten sind nach der Methode 06 zu rechen. <br/>
 * 
 * Testkontonummern (richtig): 9635000101, 9730200100 <br/>
 * Testkontonummern (falsch): 9635100101, 9730300100 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB3 extends AbstractChecksumValidator {

	private int alternative = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] < 9) {
			setAlternative(0);
			return new Checksum32().validate(accountNumber);
		} else {
			setAlternative(1);
			return new Checksum06().validate(accountNumber);
		}
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}

}
