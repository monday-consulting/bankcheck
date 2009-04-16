package hx.bankcheck.accountvalidator;

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
public class Checksum01 implements IAccountChecksum {
	private final static Logger LOG = Logger.getLogger(Checksum00.class.getName());
	
	private int[] weights = { 3, 7, 1, 3, 7, 1, 3, 7, 1 };
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		
		int sum = 0;
		for(int i=8; i>=0; i--) {
			sum += accountNumber[i] * weights[i];
		}
		int checksum = (10 - (sum % 10)) % 10;
	
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum == accountNumber[9];
	}
}
