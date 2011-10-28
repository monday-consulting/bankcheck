/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 </br>
 * 
 * Die für die Berechnung relevante Grundnummer befindet sich in den Stellen 1
 * bis 6, die Prüfziffer in Stelle 7 (von links nach rechts gezählt). Die
 * Stellen 1 bis 6 werden mit den Ziffern 7, 6, 5, 4, 3, 2 multipliziert. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die
 * dreistellige Unternummer (Stellen 8 bis 10) darf nicht in das
 * Prüfzifferberechnungs-verfahren einbezogen werden. Ist die Unternummer »000«,
 * so kommt es vor, dass diese nicht angegeben ist. Ergibt die erste Berechnung
 * einen Prüfzifferfehler, wird empfohlen, die Prüfzifferberechnung ein zweites
 * Mal durchzuführen und dabei die »gedachte« Unternummer 000 an die Stellen 8
 * bis 10 zu setzen und die vorhandene Kontonummer vorher um drei Stellen nach
 * links zu verschieben.</br>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) </br>
 * 
 * Kontonr.: x x x x x x P x x x </br>
 * 
 * Gewichtung: 7 6 5 4 3 2 </br>
 * 
 * Testkontonummern: 4000005001,4444442001</br>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum50 extends Checksum06 {

	private static final int[] WEIGHTS = { 7, 6, 5, 4, 3, 2, 0, 0, 0, 0 };

	public Checksum50() {
		super(WEIGHTS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[6] == super.calcChecksum(accountNumber)) {
			return true;
		} else {
			if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) >= 3) {
				int[] tmpAccountNumber = new int[10];
				for (int i = tmpAccountNumber.length - 1; i >= 3; i--) {
					tmpAccountNumber[i - 3] = accountNumber[i];
				}
				tmpAccountNumber[tmpAccountNumber.length - 1] = 0;
				tmpAccountNumber[tmpAccountNumber.length - 2] = 0;
				tmpAccountNumber[tmpAccountNumber.length - 3] = 0;
				return tmpAccountNumber[6] == super.calcChecksum(tmpAccountNumber);
			} else {
				return false;
			}

		}
	}

}
