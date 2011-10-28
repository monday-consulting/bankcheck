/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2
 * 
 * Die Kontonummer ist 10-stellig, ggf. ist die Kontonummer für die
 * Prüfzifferberechnung durch linksbündige Auffüllung mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Konto- nummer ist die Prüfziffer.
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Wert 7 oder 8 beinhalten sind falsch.
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Werte 0, 1, 2, 3, 4, 5, 6 oder 9 beinhalten sind wie folgt zu
 * prüfen:
 * 
 * Für die Berechnung der Prüfziffer werden die Stellen 2 bis 9 der Kontonummer
 * von links verwendet. Diese Stellen sind links um eine Zahl (Konstante) gemäß
 * der folgenden Tabelle zu ergänzen.
 * 
 * <br/>
 * <table border="1">
 * <tr>
 * <th>1. Stelle von links der 10-stelligen Kontonummer</th>
 * <th>Zahl (Konstante)</th>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>4363380</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>4363381</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>4363382</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>4363383</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>4363384</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>4363385</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>4363386</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>4363389</td>
 * </tr>
 * </table>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * <br/>
 * Beispiel:<br/>
 * Kontonummer: 3002000027 <br/>
 * Stellen 2 bis 9: 00200002 <br/>
 * Ergänzt um Konstante (15 Stellen): 436338300200002 <br/>
 * 
 * <table border="1">
 * <tr>
 * <th>15 Stellen</th>
 * <td>
 * 
 * <pre>
 * 4 3  6 3 3 8 3 0 0 2 0 0 0 0 2
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Gewichtung</th>
 * <td>
 * 
 * <pre>
 * 2 1  2 1 2 1 2 1 2 1 2 1 2 1 2
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Produkt</th>
 * <td>
 * 
 * <pre>
 * 8 3 12 3 6 8 6 0 0 2 0 0 0 0 4
 * </pre>
 * 
 * </td>
 * </tr>
 * <tr>
 * <th>Quersumme</th>
 * <td>
 * 
 * <pre>
 * 8 3  3 3 6 8 6 0 0 2 0 0 0 0 4
 * </pre>
 * 
 * <td>
 * </tr>
 * </table>
 * 
 * Summe: 43<br/>
 * <br/>
 * 10 - 3 (Einerstelle) = 7 = Prüfziffer<br/>
 * <br/>
 * <br/>
 * Testkontonummern (richtig): 0082012203, 1452683581, 2129642505, 3002000027,
 * 4230001407, 5000065514, 6001526215, 9000430223
 * 
 * <br/>
 * <br/>
 * Testkontonummern (falsch): 0000260986, 1062813622, 2256412314, 3012084101,
 * 4006003027, 5814500990, 6128462594, 7000062025, 8003306026, 9000641509
 * 
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @author Tobias Mayer (backcheck@tobiasm.de)
 * @version 1.2
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
		int[] extension;
		switch (accountNumber[0]) {
		case 0:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 0 };
			break;
		case 1:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 1 };
			break;
		case 2:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 2 };
			break;
		case 3:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 3 };
			break;
		case 4:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 4 };
			break;
		case 5:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 5 };
			break;
		case 6:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 6 };
			break;
		case 7:
			return false;
		case 8:
			return false;
		case 9:
			extension = new int[] { 4, 3, 6, 3, 3, 8, 9 };
			break;
		default:
			return false;
		}

		int[] mergedAccountNumber = new int[15];
		for (int i = 0; i < extension.length; i++) {
			mergedAccountNumber[i] = extension[i];
		}

		for (int i = 0; i < accountNumber.length - 2; i++) {
			mergedAccountNumber[i + extension.length] = accountNumber[i + 1];
		}

		return (accountNumber[9] == calcChecksum(mergedAccountNumber));
	}

}
