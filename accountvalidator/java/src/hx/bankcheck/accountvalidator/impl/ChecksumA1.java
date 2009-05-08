package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 0, 0 <br/>
 * 
 * Die Kontonummern sind 8- oder 10-stellig. Kontonummern (ohne führende Nullen
 * gezählt) mit 9 oder weniger als 8 Stellen sind falsch. 8-stellige
 * Kontonummern sind für die Prüfzifferberechnung durch linksbündige Auffüllung
 * mit Nullen 10-stellig darzustellen.<br/> *
 * Die Berechnung erfolgt wie beim Verfahren 00.<br/>
 * 
 * Beispiel: Kontonr.: 0 0 1 0 0 3 0 9 9 7 <br/>
 * Gewichtung: 0 0 2 1 2 1 2 1 2 P <br/>
 * Produkte: 0 0 2 0 0 3 0 9 18<br/>
 * Quersummen:0+ 0+ 2+ 0+ 0+ 3+ 0+ 9+ 9= 23 10-3 = 7 = P<br/>
 * 
 * Testkontonummern (richtig): 0010030005, 0010030997, 1010030054<br/>
 * 
 * Testkontonummern (falsch): 0110030005, 0010030998, 0000030005<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA1 extends Checksum00 {

	// Weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 2, 1, 2, 1, 2, 1, 2 };

	public ChecksumA1() {
		super(WEIGHTS);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] == 0) && (accountNumber[1] != 0)) {
			return false;
		} else {
			if ((accountNumber[0] == 0) && (accountNumber[1] == 0)
					&& (accountNumber[2] == 0)) {
				return false;
			}
		}
		return super.validate(accountNumber);
	}

}
