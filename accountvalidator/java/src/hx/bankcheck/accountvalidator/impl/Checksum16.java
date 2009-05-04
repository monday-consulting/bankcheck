package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4 Die Berechnung erfolgt wie
 * bei Verfahren 06. Sollte sich jedoch nach der Division der Rest 1 ergeben, so
 * ist die Kontonummer unabhängig vom eigentlichen Berechnungs-ergebnis richtig,
 * wenn die Ziffern an 10. und 9. Stelle identisch sind.
 * 
 * @author tma
 */

public class Checksum16 extends Checksum06 {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		if (checksum % 10 == 1) {
			if (accountNumber[8] == accountNumber[9])
				return true;
		}
		
		checksum = super.adjustChecksum(checksum);
		
		return (checksum == accountNumber[9]);
	}
	
	@Override
	protected int adjustChecksum(int checksum) {
		return checksum;
	}
	
}
