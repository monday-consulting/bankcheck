package hx.bankcheck.accountvalidator;

import java.util.logging.Logger;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 2
 * Die Stellen der Kontonummer sind von rechts nach links 
 * mit den Ziffern 2, 3, 4, 5, 6, 7, 8, 9, 2 zu multiplizieren. 
 * Die jeweiligen Produkte werden addiert. Die Summe ist durch 
 * 11 zu dividieren. Der verbleibende Rest wirdvom Divisor (11) 
 * subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach 
 * der Division durch 11 kein Rest, ist die Prüfziffer 0. Ergibt 
 * sich als Rest 1, ist die Prüfziffer zweistellig und kann nicht 
 * verwendet werden. Die Kontonummer ist dann nicht verwendbar.
 * 
 * @author tma
 */
public class Checksum02 implements IAccountChecksum {
	private final static Logger LOG = Logger.getLogger(Checksum02.class.getName());
	
	private int[] weights = { 2, 3, 4, 5, 6, 7, 8, 9, 2 };
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		
		int sum = 0;
		for(int i=8; i>=0; i--) {
			sum += accountNumber[i] * weights[i];
		}
		int checksum = 11 - (sum % 11);
		
		if (checksum == 11) 
			checksum = 0;
		
		LOG.finer("Calculated Checksum is: " + checksum);
		
		return checksum == accountNumber[9];
	}
	
}
