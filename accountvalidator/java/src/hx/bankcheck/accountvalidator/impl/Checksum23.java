package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7
 * 
 * Das Berechnungsverfahren entspricht dem des Kennzeichens 16, wird jedoch nur
 * auf die ersten sechs Ziffern der Kontonummer angewandt. Die Prüfziffer
 * befindet sich an der 7. Stelle der Kontonummer. Die Stellen 8 bis 10 bleiben
 * ungeprüft.
 * <br><br> 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10
 * <br>
 * Kontonummer: x x x x x x P x x x
 * <br>
 * Gewichtung: 7 6 5 4 3 2
 * <br><br>
 * Summe geteilt durch 11 = x, Rest
 * <table>
 * <tr>
 * <td>Rest = 0</td>
 * <td>Prüfziffer = 0</td>
 * </tr>
 * <tr>
 * <td>Rest = 1</td>
 * <td>Prüfziffer = 6. und 7. Stelle der Kontonummer müssen identisch sein</td>
 * </tr>
 * <tr>
 * <td>Rest = 2 bis 10</td>
 * <td>Prüfziffer = 11 minus Rest</td>
 * </tr>
 * </table>
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * $Id$
 */
public class Checksum23 extends AbstractChecksumValidator {

	private final static int[] WEIGHTS = { 7, 6, 5, 4, 3, 2 };

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for(int i=0; i<6; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		
		int rest = sum % 11;
		
		if (rest == 0)
			return 0;
		
		if (rest == 1)
			return accountNumber[5];
		
		return 11 - rest;
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		return checksum == accountNumber[6];
	}

}
