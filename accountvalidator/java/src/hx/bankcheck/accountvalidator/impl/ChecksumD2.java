/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4<br/>
 * 
 * Die Berechnung, Ausnahmen und möglichenErgebnisse entsprechen der Methode 95.
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, so ist nach
 * Variante 2 zu prüfen. <br/>
 * 
 * Testkontonummern (richtig): 189912137, 235308215<br/>
 * Testkontonummern (falsch): 4455667784, 1234567897, 51181008, 71214205
 * 6414241, 179751314 <br/>
 * 
 * <b>Variante 2:</b> <br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Berechnung und möglichen Ergebnisse entsprechen der Methode 00. Führt
 * auch die Berechnung nach Variante 2 zu einem Prüfzifferfehler, so ist nach
 * Variante 3 zu prüfen. <br/>
 * 
 * Testkontonummern (richtig): 4455667784, 1234567897<br/>
 * Testkontonummern (falsch): 51181008, 71214205, 6414241, 179751314<br/>
 * 
 * <b>Variante 3:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Berechnung, Ausnahmen und möglichen Ergebnisse entsprechen der Methode
 * 68. Führt auch die Berechnung nach Variante 3 zu einem Prüfzifferfehler, so
 * ist die Kontonummer falsch. <br/>
 * 
 * Testkontonummern (richtig): 51181008, 71214205 <br/>
 * Testkontonummern (falsch): 6414241, 179751314<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD2 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(new Checksum95().validate(accountNumber)){
			setAlternative(0);
			return true;
		}else{
			if (new Checksum00().validate(accountNumber)) {
				setAlternative(1);
				return true;
			}else{
				return new Checksum68().validate(accountNumber);
			}
		}
	}

}
