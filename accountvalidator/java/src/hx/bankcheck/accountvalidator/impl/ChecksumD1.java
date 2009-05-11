/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist 10-stellig, ggf. ist die Kontonummer für die
 * Prüfzifferberechnung durch linksbündige Auffüllung mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Konto-nummer ist die Prüfziffer. <br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Werte 0, 3 oder 9 beinhalten sind falsch. Kontonummern, die an der
 * 1. Stelle von links der 10-stelligen Kontonummer einen der Werte 1, 2, 4, 5,
 * 6, 7, oder 8 beinhalten, sind wie folgt zu prüfen: <br/>
 * 
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Für die Berechnung der Prüfziffer werden die Stellen 1 bis 9 der Kontonummer
 * verwendet. Diese Stellen sind links um Zahl (Konstante) „428259“ zu ergänzen.
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00. <br/>
 * 
 * <b>Beispiel:</b> <br/>
 * 
 * Kontonummer: 1000005016 <br/>
 * Stellen 1 bis 9: 100000501 <br/>
 * Ergänzt um Konstante (15 Stellen): 428259100000501 <br/>
 * 
 * 15 Stellen 428259100000501<br/>
 * Gewichtung 212121212121212<br/>
 * Produkt 821621092000001002<br/>
 * Quersumme 827219200000102<br/>
 * 
 * Summe = 34 <br/>
 * 
 * 10 - 4 (Einerstelle) = 6 = Prüfziffer <br/>
 * 
 * Testkontonummern (richtig): 1000004019, 4000008014, 6100020013, 8300042011 <br/>
 * Testkontonummern (falsch): 2000005018, 5000064015, 7400041011<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumD1 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
			2, 1, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] == 0) || (accountNumber[0] == 3)
				|| (accountNumber[0] == 9)) {
			return false;
		} else {
			int[] mergedAccountNumber = new int[15];
			mergedAccountNumber[0] = 4;
			mergedAccountNumber[1] = 2;
			mergedAccountNumber[2] = 8;
			mergedAccountNumber[3] = 2;
			mergedAccountNumber[4] = 5;
			mergedAccountNumber[5] = 9;
			for (int i = 0; i < accountNumber.length - 1; i++) {
				mergedAccountNumber[i + 6] = accountNumber[i];
			}
			return (accountNumber[9] == new Checksum00(WEIGHTS)
					.calcChecksum(mergedAccountNumber));
		}
	}

}
