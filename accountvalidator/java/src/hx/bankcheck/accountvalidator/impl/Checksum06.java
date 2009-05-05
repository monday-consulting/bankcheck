package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.util.logging.Logger;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 (modifiziert)
 * Die einzelnen Stellen der Kontonummer sind von rechts 
 * nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 2, 3 ff. 
 * zu multiplizieren. Die jeweiligen Produkte werden 
 * addiert. Die Summe ist durch 11 zu dividieren. Der 
 * verbleibende Rest wird vom Divisor (11) subtrahiert. 
 * Das Ergebnis ist die Prüfziffer. Ergibt sich als 
 * Rest 1, findet von dem Rechenergebnis 10 nur die 
 * Einerstelle (0) als Prüfziffer Verwendung. Verbleibt 
 * nach der Division durch 11 kein Rest, dann ist auch 
 * die Prüfziffer 0. 
 * Die Stelle 10 der Kontonummer ist 
 * die Prüfziffer.
 * 
 * Testkontonummern: 94012341, 5073321010
 * @author tma
 *
 */
public class Checksum06 implements ChecksumValidator {
	private final static Logger LOG = Logger.getLogger(Checksum02.class.getName());
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };

	private int[] weights;
	
	public Checksum06() {
		this(WEIGHTS);
	}
	
	public Checksum06(int[] weights) {
		this.weights = weights;
	}
	
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		return checksum == accountNumber[9];
	}
	
	protected int adjustChecksum(int checksum) {
		if (checksum == 10 || checksum == 11) 
			checksum = 0;
		
		return checksum;
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for(int i=0; i<weights.length; i++) {
			sum += accountNumber[i] * weights[i];
		}
		int checksum = 11 - (sum % 11);
		
		checksum = adjustChecksum(checksum);
		
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum;
	}

}
