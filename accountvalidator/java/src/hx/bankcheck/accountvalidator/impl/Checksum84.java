/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. 
 * 
 * Es ist zu beachten, dass nur die Stellen 5 bis 9 in das 
 * Prüfzifferberechnungsverfahren einbezogen werden.
 * 
 * <b>Methode A </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.<br/>
 * 
 * Testkontonummern richtig: 240699, 350982, 461059 <br/>
 * Testkontonummern falsch: 240965, 350980, 461053 <br/>
 * 
 * Führt die Berechnung nach Methode A zu einem Prüfzifferfehler,
 * ist die Berechnung nach Methode B vorzunehmen.<br/>
 * 
 * <b>Methode B </b><br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06. 
 * Dabei ist zu beachten, dass als Divisor 7 zu verwenden ist.<br/>
 * 
 * Testkontonummern richtig: 240692, 350985, 461052 <br/>
 * Testkontonummern falsch: 240965, 350980, 461053 <br/>
 * 
 * Ergibt auch die Berechnung der Prüfziffer nach Methode B
 * einen Prüfzifferfehler, ist eine weitere Berechnung mit der
 * Methode C vorzunehmen. <br/>
 * 
 * <b>Methode C </b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.<br/>
 * 
 * Testkontonummern richtig: 240961, 350984, 461054 <br/>
 * Testkontonummern falsch: 240965, 350980, 461053 <br/>
 * 
 * <b>Ausnahme </b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die
 * 3. Stelle der Kontonummer = 9 (Sachkonten), so erfolgt die
 * Berechnung gemäß der Ausnahme in Methode 51 mit den gleichen 
 * Ergebnissen und Testkontonummern. <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @author Dirk Schrödter (ds@monday-consulting.com) - Monday Consulting GmbH
 * 
 * @version 1.1
 * 
 */
public class Checksum84 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_METHOD_A = { 0, 0, 0, 0, 6, 5, 4, 3,	2 };
	private static final int[] WEIGHTS_METHOD_B = { 0, 0, 0, 0, 6, 5, 4, 3,	2 };
	private static final int[] WEIGHTS_METHOD_C = { 0, 0, 0, 0, 2, 1, 2, 1,	2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		
		if (accountNumber[2] == 9) {
			setException(true);
			return new Checksum51().validate(accountNumber);
		} else {
			if (new Checksum06(WEIGHTS_METHOD_A).validate(accountNumber)) {
				setAlternative(0);
				return true;
			} else if (new Checksum06(WEIGHTS_METHOD_B, 7).validate(accountNumber)){
				setAlternative(1);
				return true;
			} else if (new Checksum06(WEIGHTS_METHOD_C).validate(accountNumber)){
				setAlternative(2);
				return true;
			}
			
			setAlternative(0);
			return false;
		}
	}
}
