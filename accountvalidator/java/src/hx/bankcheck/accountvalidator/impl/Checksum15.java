package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5 Die Kontonummer ist 10-stellig. Die
 * Berechnung erfolgt wie bei Verfahren 06; es ist jedoch zu beachten, dass nur
 * die Stellen 6 bis 9 in das Prüfzifferberechnungsverfahren einbezogen werden.
 * Die Stelle 10 der Kontonummer ist die Prüfziffer.
 * 
 * @author tma
 * 
 */
public class Checksum15 extends Checksum06 {

	@Override
	public boolean validate(int[] num) throws ValidationException {
		int n[] = { 0, 0, 0, 0, 0, num[5], num[6], num[7], num[8], num[9] };
		
		return super.validate(n);
	}

}
