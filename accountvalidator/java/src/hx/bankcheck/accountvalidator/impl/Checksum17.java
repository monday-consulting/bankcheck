package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 1, 2, 1, 2, 1, 2 Die Kontonummer ist 10-stellig mit
 * folgendem Aufbau: KSSSSSSPUU 
 * K = Kontoartziffer 
 * S = Stammnummer 
 * P = Prüfziffer 
 * U = Unterkontonummer 
 * 
 * Die für die Berechnung relevante 6-stellige
 * Stammnummer (Kundennummer) befindet sich in den Stellen 2 bis 7 der
 * Kontonummer, die Prüfziffer in der Stelle 8. Die einzelnen Stellen der
 * Stammnummer (S) sind von links nach rechts mit den Ziffern 1, 2, 1, 2, 1, 2
 * zu multiplizieren. Die jeweiligen Produkte sind zu addieren, nachdem aus
 * eventuell zweistelligen Produkten der 2., 4. und 6. Stelle der Stammnummer
 * die Quersumme gebildet wurde. Von der Summe ist der Wert "1" zu subtrahieren.
 * Das Ergebnis ist dann durch 11 zu dividieren. Der verbleibende Rest wird von
 * 10 subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach der Division
 * durch 11 kein Rest, ist die Prüfziffer 0.
 * 
 * Testkontonummer: 0446786040
 * 
 * @author tma
 * 
 */
public class Checksum17 extends AbstractChecksumValidator {
	private final static int[] WEIGHTS = { 1, 2, 1, 2, 1, 2 };
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		return (checksum == accountNumber[7]);
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for(int i=1; i<=WEIGHTS.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * WEIGHTS[i-1]);
		}
		
		sum--;
		
		int x = sum % 11;
		int checksum = 0;
		if  (x != 0)
			checksum = 10 - x;
		
		return checksum;
	}

}
