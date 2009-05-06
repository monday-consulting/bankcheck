package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.util.logging.Logger;

/**
 * Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1, 3, 7, 1
 * Die Stellen der Kontonummer sind von rechts nach links 
 * mit den Ziffern 3, 7, 1, 3, 7, 1 usw. zu multiplizieren. 
 * Die jeweiligen Produkte werden addiert. Nach der Addition 
 * bleiben außerder Einerstelle alle anderen Stellen 
 * unberücksichtigt. Die Einerstelle wird von dem Wert 10 
 * subtrahiert. Das Ergebnis ist die Prüfziffer (10. Stelle 
 * der Kontonummer). Ergibt sich nach der Subtraktion der 
 * Rest 10, ist die Prüfziffer 0.
 * 
 * @author tma
 */
public class Checksum01 extends AbstractChecksumValidator {
	private final static Logger LOG = Logger.getLogger(Checksum00.class.getName());
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 1, 7, 3, 1, 7, 3, 1, 7, 3 };
	
	private int[] weights;
	
	public Checksum01() {
		this(WEIGHTS);
	}
	
	public Checksum01(int[] weights) {
		this.weights = weights;
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		return checksum == accountNumber[9];
	}
	
	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for(int i=0; i<WEIGHTS.length; i++) {
			sum += accountNumber[i] * weights[i];
		}
		int checksum = (10 - (sum % 10)) % 10;
		
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum;
	}
}
