package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * <b>Variante 1: </b><br/>
 * 
 * Modulus 11, Gewichtung 2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,3<br/>
 * 
 * Kontonummern,die an der 1. Stelle der 10-stelligen Kontonummer den Wert 1-9
 * beinhalten, sind nach der Methode 20 zu prüfen. Alle anderen Kontonummern
 * sind nach der Variante 2 zu prüfen. <br/>
 * 
 * Testkontonummer (richtig): 9110000000<br/>
 * Testkontonummer (falsch): 9111000000 <br/>
 * 
 * <b>Variante 2: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 4,8, 5, 10, 9, 7, 3, 6, 1, 2, 4 <br/>
 * 
 * Die Berechnung erfolgt nach der Methode 53.<br/>
 * 
 * Testkontonummer (richtig) mit BLZ 80053782: 487310018 <br/>
 * Testkontonummer (falsch) mit BLZ 80053762: 467310018 <br/>
 * Testkontonummer (falsch) mit BLZ 80053772: 477310018<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB6 extends AbstractChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		// TODO Auto-generated method stub
		return false;
	}

}
