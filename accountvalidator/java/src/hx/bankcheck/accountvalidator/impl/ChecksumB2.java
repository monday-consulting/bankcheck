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
 * <b>Variante 1: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 2<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer den
 * Wert 0 bis 7 beinhalten, sind nach der Methode 02 zu rechnen. <br/>
 * 
 * Testkontonummern (richtig): 0020012357, 0080012345, 0926801910, 1002345674<br/>
 * Testkontonummern (falsch): 0020012399, 0080012347, 0080012370, 0932100027,
 * 3310123454<br/>
 * 
 * <b>Variante 2: </b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer den
 * Wert 8 oder 9 beinhalten, sind nach der Methode 00 zu rechnen.v
 * 
 * Testkontonummern (richtig): 8000990054, 9000481805<br/>
 * Testkontonummern (falsch): 8000990057, 8011000126, 9000481800, 9980480111<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB2 extends AbstractChecksumValidator {

	private int alternative = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] < 8) {
			setAlternative(0);
			return new Checksum02().validate(accountNumber);
		} else {
			setAlternative(1);
			return new Checksum00().validate(accountNumber);
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
