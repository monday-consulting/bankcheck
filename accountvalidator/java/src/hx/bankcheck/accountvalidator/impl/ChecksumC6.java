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
 * darzustellen. Die 10. Stelle der Konto-nummer ist die Prüfziffer.<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Wert 3, 4, 5, 6 oder 8 beinhalten sind falsch.<br/>
 * 
 * Kontonummern, die an der 1. Stelle von links der 10-stelligen Kontonummer
 * einen der Werte 0, 1, 2, 7 oder 9 beinhalten sind wie folgt zu prüfen:<br/>
 * 
 * Für die Berechnung der Prüfziffer werden die Stellen 2 bis 9 der Kontonummer
 * verwendet. Diese Stellen sind links um eine Zahl (Konstante) gemäß der
 * folgenden Tabelle zu ergänzen.
 * 
 * 1. Stelle von links der 10-stelligen Kontonummer --> Zahl (Konstante)<br/>
 * 
 * 0--> 4451970 <br/>
 * 1--> 4451981 <br/>
 * 2--> 4451992 <br/>
 * 7--> 5499570 <br/>
 * 9--> 5499579 <br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.
 * 
 *Testkontonummern (richtig): 7008199027, 7002000023, 7000202027, 9000430223,
 * 9000781153, 2003455189, 2004001016, 1031405209, 1082012201, 0000065516,
 * 0203178249 <br/>
 * Testkontonummern (falsch): 7000062022, 7006003027, 7003306026, 7001501029,
 * 9000641509, 9000260986, 2004306518, 2016001206, 1082311275, 1000118821,
 * 0525111212, 0091423614<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
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
		if (((accountNumber[0] > 2) && (accountNumber[0] < 7))
				|| (accountNumber[0] == 8)) {
			return false;
		} else {
			int[] additionalDigits=this.constants.get(new Integer(accountNumber[0]));
			int[] mergedAccoundNumber=new int[15];
			for (int i = 0; i < additionalDigits.length; i++) {
				mergedAccoundNumber[i]=additionalDigits[i];
			}
			for (int i = 1; i < accountNumber.length-1; i++) {
				mergedAccoundNumber[i+(additionalDigits.length-1)]=accountNumber[i];
			}
			return accountNumber[9]==new Checksum00(WEIGHTS).calcChecksum(mergedAccoundNumber);
		}
	}

	private void initConstants() {
		this.constants = new HashMap<Integer, int[]>();
		int[][] tmp = { { 4, 4, 5, 1, 9, 7, 0 }, { 4, 4, 5, 1, 9, 8, 1, },
				{ 4, 4, 5, 1, 9, 9, 2 }, { 5, 4, 9, 9, 5, 7, 0 },
				{ 5, 4, 9, 9, 5, 7, 9 } };
		this.constants.put(new Integer(0), tmp[0]);
		this.constants.put(new Integer(1), tmp[1]);
		this.constants.put(new Integer(2), tmp[2]);
		this.constants.put(new Integer(7), tmp[3]);
		this.constants.put(new Integer(9), tmp[4]);
	}

}
