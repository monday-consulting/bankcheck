package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.util.logging.Logger;

/**
 * Modulus 10, Gewichtung 3, 1, 3, 1, 3, 1, 3, 1, 3 Die einzelnen Stellen der
 * Kontonummer sind von rechts nach links mit den Ziffern 3, 1, 3, 1 usw. zu
 * multiplizieren. Von den jeweiligen Produkten bleiben die Zehnerstellen
 * unberücksichtigt. Die verbleibenden Zahlen (Einerstellen) werden addiert. Die
 * Differenz bis zum nächsten Zehner ist die Prüfziffer.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum22 implements ChecksumValidator {
	private final static Logger LOG = Logger.getLogger(Checksum22.class.getName());

	protected final static int[] WEIGHTS = { 3,1,3,1,3,1,3,1,3 };
	
	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for(int i=0; i<9; i++) {
			int x = accountNumber[i] * WEIGHTS[i];
			sum += x % 10;
		}
		
		int checksum = 10 - (sum % 10);
		
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum;
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);
		
		return checksum == accountNumber[9];
	}

}
