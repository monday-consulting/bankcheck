package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. <br/>
 * 
 * <b>Variante 1: </b><br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 00. <br/>
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, ist nach
 * Variante 2 zu prüfen. <br/>
 * 
 * Testkontonummern (richtig): 19010008,19010438<br/>
 * Testkontonummern (falsch): 19010660, 19010876, 209010892, 209010893 <br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 03.<br/>
 * 
 * Testkontonummern (richtig): 19010660,19010876, 209010892 <br/>
 * Testkontonummer (falsch): 209010893<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA7 extends AbstractChecksumValidator {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (new Checksum00().validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else {
			setAlternative(1);
			return new Checksum03().validate(accountNumber);
		}
	}

}
