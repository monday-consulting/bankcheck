package hx.bankcheck.accountvalidator;

import java.util.logging.Logger;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * Die Stellen der Kontonummer sind von rechts nach links
 * mit den Ziffern 2, 1, 2, 1, 2 usw. zu multiplizieren. 
 * Die jeweiligen Produkte werden addiert, nachdem jeweils 
 * aus den zweistelligen Produkten die Quersumme gebildet 
 * wurde (z. B. Produkt 16 = Quersumme 7). Nach der Addition 
 * bleiben außer der Einerstelle alle anderen Stellen 
 * unberücksichtigt. Die Einerstelle wird von dem Wert 10 
 * subtrahiert. Das Ergebnis ist die Prüfziffer (10. Stelle 
 * der Kontonummer). Ergibt sich nach der Subtraktion der 
 * Rest 10, ist die Prüfziffer 0.
 * 
 * Testkontonummern: 9290701, 539290858, 1501824, 1501832
 *  
 * @author tma
 */
public class Checksum00 implements IAccountChecksum {
	private final static Logger LOG = Logger.getLogger(Checksum00.class.getName());
	
	private int[] weights = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		
		int sum = 0;
		for(int i=8; i>=0; i--) {
			sum += ChecksumUtils.qs(accountNumber[i] * weights[i]);
		}
		int checksum = (10 - (sum % 10)) % 10;
	
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum == accountNumber[9];
	}
	
}
