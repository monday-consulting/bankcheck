/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 10, Gewichtung 7, 3, 1, 7, 3, 1, 7, 3, 1<br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 05. Führt die Berechnung
 * nach Variante 1 zu einem Prüfzifferfehler, so ist nach Variante 2 zu prüfen.<br/>
 * 
 * Testkontonummern (richtig): 1434253150, 2746315471<br/>
 * Testkontonummern (falsch): 7414398260, 8347251693 0123456789, 2345678901,<br/>
 * 5678901234
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1, 3, 7, 1<br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 01.<br/>
 * 
 * Testkontonummern (richtig): 7414398260, 8347251693<br/>
 * Testkontonummern (falsch): 0123456789, 2345678901, 5678901234<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB1 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (new Checksum05().validate(accountNumber)) {
			return true;
		} else {
			setAlternative(1);
			return new Checksum01().validate(accountNumber);
		}
	}

}
