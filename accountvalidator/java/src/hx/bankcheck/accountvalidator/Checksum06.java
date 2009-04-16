package hx.bankcheck.accountvalidator;

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
public class Checksum06 implements IAccountChecksum {
	private final static Logger LOG = Logger.getLogger(Checksum02.class.getName());
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int sum = 0;
		for(int i=0; i<9; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		int checksum = 11 - (sum % 11);
		
		if (checksum == 10 || checksum == 11) 
			checksum = 0;
		
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum == accountNumber[9];
	}

}
