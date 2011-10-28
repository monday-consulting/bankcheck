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
 * Modulus 10, Gewichtung 7, 3, 1 ,7 , 3, 1, 7, 3, 1<br/>
 * 
 * Die Gewichtung entspricht der Methode (Kennzeichen) 05. Die Berechnung
 * entspricht der Methode (Kennzeichen) 01. Führt die Berechnung nach der
 * Variante 1 zu einem Prüfzifferfehler, so sind Kontonummern, die an der 1.
 * Stelle von links der 10-stelligen Kontonummer den Wert 8 oder 9 beinhalten,
 * falsch. Alle anderen Kontonummern sind nach der Variante 2 zu prüfen.<br/>
 * 
 * Testkontonummern (richtig): 0159006955, 2000123451, 1151043216, 9000939033<br/>
 * 
 * Testkontonummern (falsch): 7414398260, 8347251693, 1151043211, 2345678901,
 * 5678901234, 9000293707<br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode (Kennzeichen) 00.<br/>
 * 
 * Testkontonummern (richtig): 0123456782, 0130098767, 1045000252<br/>
 * Testkontonummern (falsch): 0159004165, 0023456787, 0056789018, 3045000333<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB5 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (new Checksum05().validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else {
			if ((accountNumber[0] == 9) || (accountNumber[0] == 8)) {
				return false;
			} else {
				setAlternative(1);
				return new Checksum00().validate(accountNumber);
			}
		}
	}

}
