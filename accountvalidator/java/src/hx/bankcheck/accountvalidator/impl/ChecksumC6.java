package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Kontonummer ist 10-stellig, ggf. ist die Kontonummer für die
 * Prüfzifferberechnung durch linksbündige Auffüllung mit Nullen 10-stellig
 * darzustellen. Die 10. Stelle der Konto- nummer ist die Prüfziffer.
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Wert 4 oder 8 beinhalten sind falsch.
 * 
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Werte 0, 1, 2, 3, 5, 6, 7 oder 9 beinhalten sind wie folgt zu
 * prüfen:
 * 
 * 
 * Für die Berechnung der Prüfziffer werden die Stellen 2 bis 9 der Kontonummer
 * verwendet. Diese Stellen sind links um eine Zahl (Konstante) gemäß der
 * folgenden Tabelle zu ergänzen.
 * 
 * <table>
 * <tr>
 * <th>1.Stelle von links</th>
 * <th>Zahl</th>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>4451970</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>4451981</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>4451992</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>4451993</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>4344990</td>
 * </tr>
 * <tr>
 * <td>6</td>
 * <td>4344991</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>5499570</td>
 * </tr>
 * <tr>
 * <td>9</td>
 * <td>5499579</td>
 * </tr>
 * 
 * Die Berechnung und m�gliche Ergebnisse entsprechen der Methode 00.
 * 
 * Testkontonummern (richtig): 7008199027, 7002000023, 9000430223, 9000781153,
 * 2003455189, 2004001016, 1031405209, 1082012201, 0000065516, 0203178249,
 * 3110150986, 3068459207 <br/>
 * Testkontonummern (falsch): 7000062022, 7006003027, 9000641509, 9000260986,
 * 2004306518, 2016001206, 1082311275, 1000118821, 0525111212, 0091423614,
 * 3462816371, 3622548632, 4642847318, 8348300005<br/>
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * @version 1.1
 * 
 */
public class ChecksumC6 extends AbstractChecksumValidator {

	private Map<Integer, int[]> constants;
	private int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };

	public ChecksumC6() {
		initConstants();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int[] additionalDigits = this.constants.get(new Integer(
				accountNumber[0]));
		if (additionalDigits != null) {
			int[] mergedAccoundNumber = new int[15];
			for (int i = 0; i < additionalDigits.length; i++) {
				mergedAccoundNumber[i] = additionalDigits[i];
			}
			for (int i = 1; i < accountNumber.length - 1; i++) {
				mergedAccoundNumber[i + (additionalDigits.length - 1)] = accountNumber[i];
			}
			return accountNumber[9] == new Checksum00(WEIGHTS)
					.calcChecksum(mergedAccoundNumber);
		} else {
			return false;
		}
	}

	private void initConstants() {
		this.constants = new HashMap<Integer, int[]>();

		this.constants.put(new Integer(0), new int[] { 4, 4, 5, 1, 9, 7, 0 });
		this.constants.put(new Integer(1), new int[] { 4, 4, 5, 1, 9, 8, 1 });
		this.constants.put(new Integer(2), new int[] { 4, 4, 5, 1, 9, 9, 2 });
		this.constants.put(new Integer(3), new int[] { 4, 4, 5, 1, 9, 9, 3 });
		this.constants.put(new Integer(5), new int[] { 4, 3, 4, 4, 9, 9, 0 });
		this.constants.put(new Integer(6), new int[] { 4, 3, 4, 4, 9, 9, 1 });
		this.constants.put(new Integer(7), new int[] { 5, 4, 9, 9, 5, 7, 0 });
		this.constants.put(new Integer(9), new int[] { 5, 4, 9, 9, 5, 7, 9 });
	}

}
