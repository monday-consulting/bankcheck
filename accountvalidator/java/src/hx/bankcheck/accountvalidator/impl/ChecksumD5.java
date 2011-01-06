package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 1. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 0, 0
 * 2. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
 * 3. Modulus 7, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
 * 4. Modulus 10, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
 * Die Kontonummer ist einschließlich der Prüfziffer (P)
 * 10-stellig, ggf. ist die Kontonummer für die Prüfziffer-
 * berechnung durch linksbündige Auffüllung mit Nullen
 * 10-stellig darzustellen.
 * Konten mit der Ziffernfolge 99 an Stelle 3 und 4 (xx99xxxxxx)
 * sind nur nach Variante 1 zu prüfen. Alle übrigen Konten sind
 * nacheinander nach den Varianten 2, ggf. 3 und ggf. 4 zu
 * prüfen.
 *
 * $Id$
 */
public class ChecksumD5 extends AbstractChecksumValidator {
	
	private static final int[] VARIANT1_WEIGHTS = {0,0,8,7,6,5,4,3,2};
	private static final int[] VARIANT234_WEIGHTS = {0,0,0,7,6,5,4,3,2};

	/**
	 * Variant 1
	 * 
	 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 0, 0
     * In die Prüfzifferberechnung werden nur die Stellen 3 bis 9
     * einbezogen. Die Stelle 10 ist die Prüfziffer (P). Die weitere
     * Berechnung erfolgt nach dem Verfahren 06.
	 * 
	 * @param accountNumber
	 * @return
	 */
	private int checksumVariant1(int[] accountNumber) {
		int sum = ChecksumUtils.calcWeightedSum(accountNumber, VARIANT1_WEIGHTS, 0, 8);
		int checksum = 11 - (sum % 11);
		switch(checksum) {
		case 10:
		case 11: return 0;
		default: return checksum;
		}
	}
	
	/**
	 * Variant 2
	 * 
	 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
     * In die Prüfzifferberechnung werden nur die Stellen 4 bis 9
     * einbezogen. Die Stelle 10 ist die Prüfziffer (P). Die weitere
     * Berechnung erfolgt nach dem Verfahren 06.
     * 
	 * @param accountNumber
	 * @return
	 */
	private int checksumVariant2(int[] accountNumber) {
		int sum = ChecksumUtils.calcWeightedSum(accountNumber, VARIANT234_WEIGHTS, 0, 8);
		int checksum = 11 - (sum % 11);
		switch(checksum) {
		case 10:
		case 11: return 0;
		default: return checksum;
		}
	}

	/**
	 * Variant 3
	 * 
	 * Modulus 7, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
     * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach
     * links mit den Gewichten multipliziert. Die jeweiligen Produkte
     * werden addiert. Die Summe ist durch 7 zu dividieren. Der
     * verbleibende Rest wird vom Divisor (7) subtrahiert. Das
     * Ergebnis ist die Prüfziffer (Stelle 10). Verbleibt nach der
     * Division durch 7 kein Rest, ist die Prüfziffer 0.
     * 
	 * @param accountNumber
	 * @return
	 */
	private int checksumVariant3(int[] accountNumber) {
		int sum = ChecksumUtils.calcWeightedSum(accountNumber, VARIANT234_WEIGHTS, 0, 8);
		int checksum = 7 - (sum % 7);
		switch(checksum) {
		case 7: return 0;
		default: return checksum;
		}
	}
	
	/**
	 * Variant 4
	 * 
	 * Modulus 10, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0
     * Die Berechnung erfolgt analog zu Variante 3, jedoch ist als
     * Divisor der Wert 10 zu verwenden. Verbleibt nach der
     * Division durch 10 kein Rest, ist die Prüfziffer 0.
	 * 
	 * @param accountNumber
	 * @return
	 */
	private int checksumVariant4(int[] accountNumber) {
		int sum = ChecksumUtils.calcWeightedSum(accountNumber, VARIANT234_WEIGHTS, 0, 8);
		int checksum = 10 - (sum % 10);
		switch(checksum) {
		case 10: return 0;
		default: return checksum;
		}
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[2]==9 && accountNumber[3]==9) {
			return accountNumber[9] == checksumVariant1(accountNumber);
		} else {
			boolean ok = (checksumVariant2(accountNumber) == accountNumber[9]);
			if (ok) return ok;
			
			ok = (checksumVariant3(accountNumber) == accountNumber[9]);
			if (ok) return ok;
			
			return checksumVariant4(accountNumber) == accountNumber[9];
		}
	}

}
