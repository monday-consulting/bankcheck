package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1
 * Die Berechnung erfolgt wie bei Verfahren 00. Die für 
 * die Berechnung relevante sechsstellige Grundnummer 
 * befindet sich in den Stellen 2 bis 7, die Prüfziffer 
 * in Stelle 8 (von links nach rechts gezählt). Die 
 * zweistellige Unterkontonummer (Stellen 9 und 10) 
 * darf nicht in das Prüfzifferberechnungs-verfahren 
 * einbezogen werden. Ist die Unterkontonummer »00», 
 * kommt es vor,dass sie nicht angegeben ist. Ergibt 
 * die erste Berechnung einen Prüfzifferfehler, wird 
 * empfohlen, die Prüfzifferberechnung ein zweites Mal 
 * durchzuführen und dabei die »gedachte« Unterkontonummer 00 
 * an die Stellen 9 und 10 zu setzen und die vorhandene 
 * Kontonummer vorher um zwei Stellen nach links zu 
 * verschieben.
 *
 * @author tma
 *
 */
public class Checksum13 extends Checksum00 {

	@Override
	public boolean validate(int[] num) throws ValidationException {
		int[] n = { 0, 0, 0, num[1], num[2], num[3], num[4], num[5], num[6], 0 };
		
		int checksum = calcChecksum(n);
		boolean checkOk = checksum == num[7];
		if (!checkOk && num[0] + num[1] == 0) {
			n = new int[] { 0, 0, 0, num[3], num[4], num[5], num[6], num[7], num[8], 0 };
			
			checksum = calcChecksum(n);
			checkOk = (checksum == num[9]);
		}
		
		return checkOk;
	}

}
