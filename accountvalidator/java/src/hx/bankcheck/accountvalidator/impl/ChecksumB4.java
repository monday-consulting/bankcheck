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
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer den
 * Wert 9 beinhalten, sind nach der Methode 00 zu rechnen.
 * 
 * Testkontonummern (richtig): 9941510001, 9961230019 9380027210, 9932290910<br/>
 * Testkontonummern (falsch): 9941510002, 9961230020 <br/>
 * 
 * <b>Variante 2:</b> <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer den
 * Wert 0 bis 8 beinhalten, sind nach der Methode 02 zu rechnen. <br/>
 * 
 * Testkontonummern (richtig): 0000251437, 0007948344, 0000051640<br/>
 * Testkontonummern (falsch): 0000251438, 0007948345, 0000159590<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB4 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] == 9) {
			setAlternative(0);
			return new Checksum00().validate(accountNumber);
		} else {
			setAlternative(1);
			return new Checksum02().validate(accountNumber);
		}
	}

}
