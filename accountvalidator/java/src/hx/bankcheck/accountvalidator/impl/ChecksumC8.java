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
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 00. Führt die Berechnung
 * nach Variante 1 zu einem Prüfzifferfehler, so ist nach Variante 2 zu prüfen. <br/>
 * 
 * Testkontonummern (richtig): 3456789019, 5678901231<br/>
 * Testkontonummern (falsch): 3456789012, 0123456789, 1234567890, 9012345678<br/>
 * 
 * <b>Variante 2: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4 <br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 04. Führt auch die
 * Berechnung nach Variante 2 zu einem Prüfzifferfehler, so ist nach Variante 3
 * zu prüfen.<br/>
 * 
 * Testkontonummer (richtig): 3456789012 <br/>
 * Testkontonummern (falsch): 0123456789, 1234567890, 9012345678 <br/>
 * 
 * <b>Variante 3:</b> <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10 <br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 07.<br/>
 * 
 * Testkontonummer (richtig): 0123456789 <br/>
 * Testkontonummer (falsch) : 1234567890, 9012345678 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC8 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (new Checksum00().validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else {
			if (new Checksum04().validate(accountNumber)) {
				setAlternative(1);
				return true;
			} else {
				setAlternative(2);
				return new Checksum07().validate(accountNumber);
			}
		}
	}

}
