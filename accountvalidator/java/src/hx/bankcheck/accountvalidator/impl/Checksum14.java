package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7
 * Die Berechnung erfolgt wie bei Verfahren 02. Es ist 
 * jedoch zu beachten, dass die zweistellige Kontoart 
 * nicht in das Prüfzifferberechnungsverfahren mit 
 * einbezogen wird. Die Kontoart belegt die Stellen 2 
 * und 3, die zu berechnende Grundnummer die Stellen 4 
 * bis 9. Die Prüfziffer befindet sich in Stelle 10.
 * 
 * @author tma
 *
 */
public class Checksum14 extends Checksum02 {

	@Override
	public boolean validate(int[] num) throws ValidationException {
		int[] n = { 0, 0, 0, num[3], num[4], num[5], num[6], num[7], num[8], num[9] };
		
		return super.validate(n);
	}
	
}
