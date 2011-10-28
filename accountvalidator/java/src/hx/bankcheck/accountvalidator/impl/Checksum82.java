/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. Die für die
 * Berechnung relevanten Stellen werden von rechts nach links mit den Ziffern 2,
 * 3, 4, 5, 6 multipliziert. Die weitere Berechnung und die möglichen Ergebnisse
 * entsprechen dem Verfahren 33.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 6 5 4 3 2 <br/>
 * 
 * <b>Ausnahme:</b> <br/>
 * 
 * Sind die 3. und 4. Stelle der Kontonummer = 99, so erfolgt die Berechnung
 * nach Verfahren 10. <br/>
 * 
 * Testkontonummern: 123897, 3199500501<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum82 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 0, 0, 0, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if((accountNumber[2]==9)&&(accountNumber[3]==9)){
			return new Checksum10().validate(accountNumber);
		}else{
			setException(true);
			return new Checksum33(WEIGHTS).validate(accountNumber);
		}
	}

}
