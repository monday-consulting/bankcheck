/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 ff. <br/>
 * 
 * Die Kontonummer (2- bis 10-stellig) ist durch linksbündige Nullenauffüllung
 * 10-stellig darzustellen. Die 10. Stelle ist per Definition die Prüfziffer.
 * Die für die Berechnung relevanten Stellen werden von rechts nach links mit
 * den Ziffern 2, 1, 2, 1, 2 ff. multipliziert. Dieweitere Berechnung und die
 * Ergebnisse entsprechen dem Verfahren 00. <br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Bei 6-stelligen Kontonummern ist folgende Besonderheit zu beachten: <br/>
 * 
 * Ergibt die erste Berechnung der Prüfziffer nach dem Verfahren 00 einen
 * Prüfzifferfehler, so ist eine weitere Berechnung vorzunehmen. Hierbei ist die
 * Summe der Produkte auf die nächste Halbdekade hochzurechnen. Die Differenz
 * ist die Prüfziffer.<br/>
 * 
 * Beispiel: <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: 2 3 9 3 1 P<br/>
 * Gewichtung: 2 1 2 1 2 4 + 3 + 9 + 3 + 2 = 21 (Q) (Q = Quersumme) <br/>
 * 
 * 1. Berechnung (Verfahren 00) 10 - 1 = 9 <br/>
 * 2. Berechnung 21 + 4 = 25 (nächste Halbdekade) <br/>
 * 
 * In diesem Fall kann die Prüfziffer 4 oder 9 lauten. <br/>
 * 
 * Testkontonummern (richtig): 1016, 26260, 242243, 242248, 18002113, 1821200043 <br/>
 * Testkontonummern (falsch): 1011, 26265, 18002118, 6160000024 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum74 extends Checksum00 {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(ChecksumUtils.countNeutralLeadingDigits(accountNumber)==4){
			if(super.validate(accountNumber)){
				return true;
			}else{
				setException(true);
				return accountNumber[9]==ChecksumUtils.getDiffToHalfDecade(calcSum(accountNumber));
			}
		}else{
			return super.validate(accountNumber);
		}
	}
	
	private int calcSum(int[] accountNumber) {
		int sum = 0;
		for(int i=getWeights().length-1; i>=0; i--) {
			sum += ChecksumUtils.qs(accountNumber[i] * getWeights()[i]);
		}
		return sum;
	}

}
