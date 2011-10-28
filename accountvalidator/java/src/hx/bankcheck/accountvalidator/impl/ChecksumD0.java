/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * 
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. <br/>
 * 
 * Kontonummern, die an der 1. und 2. Stelle der 10-stelligen Kontonummer einen
 * Wert ungleich „57“ beinhalten, sind nach der Variante 1 zu prüfen.
 * Kontonummern, die an der 1. und 2. Stelle der 10-stelligen Kontonummer den
 * Wert „57“ beinhalten, sind nach der Variante 2 zu prüfen. <br/>
 * 
 * <b>Variante 1:</b> <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 3 (modifiziert) <br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 20. Führt die
 * Berechnung nach der Variante 1 zu einem Prüfzifferfehler, so ist die
 * Kontonummer falsch. <br/>
 * 
 * Testkontonummern (richtig): 6100272324, 6100273479 <br/>
 * Testkontonummern (falsch): 6100272885, 6100273377, 6100274012 <br/>
 * 
 * <b>Variante 2:</b> <br/>
 * 
 * Für den Kontonummernkreis 5700000000 bis 5799999999 gilt die Methode 09
 * (keine Prüfzifferberechnung, alle Kontonummern sind als richtig zu werten).<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD0 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if((accountNumber[0]==5)&&(accountNumber[1]==7)){
			setAlternative(1);
			return new Checksum09().validate(accountNumber);
		}else{
			setAlternative(0);
			return new Checksum20().validate(accountNumber);
		}
	}

}
