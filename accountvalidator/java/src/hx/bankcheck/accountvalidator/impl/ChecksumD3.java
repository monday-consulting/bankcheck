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
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 00.<br/>
 * 
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, so ist nach
 * Variante 2 zu prüfen. <br/>
 * 
 * Testkontonummern (richtig): 1600169591, 1600189151, 1800084079 <br/>
 * Testkontonummern (falsch): 1600166307, 1600176485, 1600201934<br/>
 * 
 * <b>Variante 2:</b> <br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)<br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 27. <br/>
 * 
 * Testkontonummer (richtig): 6019937007, 6021354007, 6030642006 <br/>
 * Testkontonummer (falsch) : 6025017009, 6028267003, 6019835001<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD3 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(new Checksum00().validate(accountNumber)){
			setAlternative(0);
			return true;
		}else{
			setAlternative(1);
			return new Checksum27().validate(accountNumber);
		}
	}

}
