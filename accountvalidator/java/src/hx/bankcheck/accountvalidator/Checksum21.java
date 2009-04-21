package hx.bankcheck.accountvalidator;

import java.util.logging.Logger;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert) Die Berechnung
 * erfolgt wie bei Verfahren 00. Nach der Addition der Produkte werden neben der
 * Einerstelle jedoch alle Stellen berücksichtigt, indem solange Quersummen
 * gebildet werden, bis ein einstelliger Wert verbleibt. Die Differenz zwischen
 * diesem Wert und dem Wert 10 ist die Prüfziffer.
 * 
 * @author tma
 */
public class Checksum21 extends Checksum00 {
	private final static Logger LOG = Logger.getLogger(Checksum21.class.getName());
	
	@Override
	public int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 8; i >= 0; i--) {
			sum += ChecksumUtils.qs(accountNumber[i] * WEIGHTS[i]);
		}
		while(sum > 9) {
			sum = ChecksumUtils.qs(sum);
		}
		int checksum = 10 - sum;
		
		// checksum is always != except accountNumber is 0000000000
		assert checksum != 0;

		LOG.finer("Calculated Checksum is: " + checksum);

		return checksum;
	}

}
