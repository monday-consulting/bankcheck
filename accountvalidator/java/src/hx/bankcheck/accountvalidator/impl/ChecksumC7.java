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
 * <b>Variante 1</b>:<br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 63. Führt die
 * Berechnung nach Variante 1 zu einem Prüfzifferfehler, so ist nach Variante 2
 * zu prüfen.<br/>
 * 
 * Testkontonummern (richtig): 3500022, 38150900, 600103660, 39101181 <br/>
 * Testkontonummern (falsch): 94012341, 5073321010<br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 (modifiziert) Die Berechnung und
 * mögliche Ergebnisse entsprechen der Methode 06.<br/>
 * 
 * Testkontonummern (richtig): 94012341, 5073321010<br/>
 * Testkontonummern (falsch): 1234517892, 987614325<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC7 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(new Checksum63().validate(accountNumber)){
			setAlternative(0);
			return true;
		}else{
			setAlternative(1);
			return new Checksum06().validate(accountNumber);
		}
	}

}
