package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9 ohne Quersumme Die einzelnen
 * Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 3, 4,
 * 5, 6, 7, 8 und 9 zu multiplizieren. Die jeweiligen Produkte werden addiert.
 * Die Summe ist durch 11 zu dividieren. Der verbleibende Rest wird vom Divisor
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach der Division
 * durch 11 kein Rest, ist die Prüfziffer = 0. Ergibt sich als Rest 1, so ist
 * die Prüfziffer immer 0 und kann nur für die Arbeitsziffern 8 und 9 verwendet
 * werden. Die Kontonummer ist für die Arbeitsziffern 0, 1, 2, 3, 4, 5, 6 und 7
 * dann nicht verwendbar. Die Arbeitsziffer (Geschäftsbereich oder Kontoart)
 * befindet sich in der 2. Stelle (von links) des 10-stelligen
 * Kontonummernfeldes.<br>
 * <br>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br>
 * Kontonr.: x x x x x x x x x P <br>
 * Gewichtung: 9 8 7 6 5 4 3 2 <br>
 * <br>
 * Die Kontonummer ist 9-stellig, wobei die 1. Stelle die Arbeitsziffer und die
 * letzte Stelle die Prüfziffer ist. <br>
 * <br>
 * Testnummern: 521382181
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum25 extends AbstractChecksumValidator {

	private final static int[] WEIGHTS = { 9, 8, 7, 6, 5, 4, 3, 2 };
	
	private int calcRest(int[] accountNumber) {
		int sum = 0;
		for(int i=1; i<9; i++) {
			sum += accountNumber[i] * WEIGHTS[i-1];
		}
		
		int rest = sum % 11;

		return rest;
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int rest = calcRest(accountNumber);
		
		if (rest == 0) {
			if (accountNumber[0] == 8 || accountNumber[0] == 9)
				return accountNumber[9] == 0;
			else
				return false;
		}
		
		int checksum = 11 - rest;
		
		return checksum == accountNumber[9];
	}

}
