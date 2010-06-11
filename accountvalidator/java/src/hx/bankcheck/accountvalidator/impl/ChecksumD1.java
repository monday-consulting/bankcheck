/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist 10-stellig, ggf. ist die Kontonummer für die
 * Prüfzifferberechnung durch linksbündige Auffüllung mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Konto-nummer ist die Prüfziffer. <br/>
 * <br/>
 * Die Kontonummern sind wie folgt zu prüfen: Für die Berechnung der Prüfziffer
 * werden die Stellen 1 bis 9 der Kontonummer von links verwendet. Diese Stellen
 * sind links um eine Zahl (Konstante) gemäß der folgenden Tabelle zu ergänzen.<br/>
 * 
 * <table border="1">
 * <tr>
 * <th>1. Stelle von links der 10-stelligen Kontonummer</th>
 * <th>Zahl (Konstante)</th>
 * </tr>
 * <tr>
 * <td>0, 3, 4, 5,9</td>
 * <td>436338</td>
 * <tr>
 * <td>1, 2, 6, 7, 8</td>
 * <td>428259</td>
 * </tr>
 * </table>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * <br/>
 * Beispiel:<br/>
 * Kontonummer: 7000005021 <br/>
 * Stellen 1 bis 9: 700000502 <br/>
 * Ergänzt um Konstante (15 Stellen): 428259700000502 <br/>
 * 
 * <table border="1">
 * <tr>
 * <th>15 Stellen</th>
 * <td>
 * 
 * <pre>
 * 4 2  8 2  5 9  7 0 0 0 0 0  5 0 2
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Gewichtung</th>
 * <td>
 * 
 * <pre>
 * 2 1  2 1  2 1  2 1 2 1 2 1  2 1 2
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Produkt</th>
 * <td>
 * 
 * <pre>
 * 8 2 16 2 10 9 14 0 0 0 0 0 10 0 4
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Quersumme</th>
 * <td>
 * 
 * <pre>
 * 8 2  7 2  1 9  5 0 0 0 0 0  1 0 4
 * </pre>
 * 
 * <td>
 * </tr>
 * </table>
 * 
 * Summe: 39<br/>
 * <br/>
 * 10 - 9 (Einerstelle) = 1 = Prüfziffer<br/>
 * <br/>
 * <br/>
 * Testkontonummern (richtig): 3002000027, 9000430223, 2003455182, 1031405201,
 * 0082012203, 5000065514<br/>
 * <br/>
 * Testkontonummern (falsch): 7000062025, 4006003027, 8003306026, 2001501026,
 * 9000641509, 0000260986
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @author Tobias Mayer (backcheck@tobiasm.de)
 * @version 1.1
 * 
 */
public class ChecksumD1 extends Checksum00 {

	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
			2, 1, 2 };

	public ChecksumD1() {
		super(WEIGHTS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int[] mergedAccountNumber = new int[15];
		if ((accountNumber[0] == 0) || (accountNumber[0] == 3)
				|| (accountNumber[0] == 4) || (accountNumber[0] == 5)
				|| (accountNumber[0] == 9)) {
			mergedAccountNumber[0] = 4;
			mergedAccountNumber[1] = 3;
			mergedAccountNumber[2] = 6;
			mergedAccountNumber[3] = 3;
			mergedAccountNumber[4] = 3;
			mergedAccountNumber[5] = 8;
		} else {
			mergedAccountNumber[0] = 4;
			mergedAccountNumber[1] = 2;
			mergedAccountNumber[2] = 8;
			mergedAccountNumber[3] = 2;
			mergedAccountNumber[4] = 5;
			mergedAccountNumber[5] = 9;
		}
		for (int i = 0; i < accountNumber.length - 1; i++) {
			mergedAccountNumber[i + 6] = accountNumber[i];
		}
		return (accountNumber[9] == calcChecksum(mergedAccountNumber));
	}

}
