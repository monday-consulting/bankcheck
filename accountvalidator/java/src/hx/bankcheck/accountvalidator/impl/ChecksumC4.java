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
 * Nullen 10-stellig darzustellen.<br/>
 * 
 * Die 10. Stelle der Kontonummer ist die Prüfziffer. Kontonummern, die an der
 * 1. Stelle der 10-stelligen Kontonummer einen Wert ungleich 9 beinhalten,
 * sind nach der Variante 1 zu prüfen. Kontonummern, die an der 1. Stelle der
 * 10-stelligen Kontonummer den Wert 9 beinhalten, sind nach der Variante 2 zu
 * prüfen.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 15.<br/>
 * 
 * Testkontonummern (richtig): 0000000019, 0000292932, 0000094455<br/>
 * Testkontonummern (falsch): 0000000017, 0000292933, 0000094459<br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 0, 0<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 58.<br/>
 * 
 * Testkontonummern (richtig): 9000420530, 9000010006, 9000577650<br/>
 * Testkontonummern (falsch): 9000726558, 9001733457, 9000732000<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC4 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(accountNumber[0]!=9){
			setAlternative(0);
			return new Checksum15().validate(accountNumber);
		}else{
			setAlternative(1);
			return new Checksum58().validate(accountNumber);
		}
	}

}
