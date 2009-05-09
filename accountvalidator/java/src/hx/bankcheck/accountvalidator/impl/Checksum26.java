package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11. Gewichtung 2, 3, 4, 5, 6, 7, 2 Die Kontonummer ist 10-stellig.
 * Sind Stelle 1 und 2 mit Nullen gefüllt ist die Kontonummer um 2 Stellen nach
 * links zu schieben und Stelle 9 und 10 mit Nullen zu füllen. Die Berechnung
 * erfolgt wie bei Verfahren 06 mit folgender Modifizierung: für die Berechnung
 * relevant sind die Stellen 1 - 7; die Prüfziffer steht in Stelle 8. Bei den
 * Stellen 9 und 10 handelt es sich um eine Unterkontonummer, welche für die
 * Berechnung nicht berücksichtigt wird. <br>
 * <br>
 * Testkontonummern: 0520309001, 1111118111, 0005501024
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum26 extends Checksum06 {

	private static int[] WEIGHTS = { 2, 7, 6, 5, 4, 3, 2, 0, 0 };
	
	public Checksum26() {
		super(WEIGHTS);
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int[] n = new int[10];
		if (accountNumber[0] == 0 && accountNumber[1] == 0) {
			System.arraycopy(accountNumber, 2, n, 0, 8);
		} else {
			System.arraycopy(accountNumber, 0, n, 0, 10);
		}
		
		int checksum = super.calcChecksum(n);
		
		return checksum == n[7]; 
	}

}
