package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 4, 8, 5, 10, 0, 0, 0, 0 <br/>
 * 
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. Die Stelle 10 ist die Prüfziffer. Die
 * einzelnen Stellen der Kontonummer (ohne Prüfziffer) sind von rechts nach
 * links mit dem zugehörigen Gewicht (2, 4, 8, 5, 10, 0, 0, 0, 0) zu
 * multiplizieren. Die Produkte werden addiert. Das Ergebnis ist durch 11 zu
 * dividieren. Ergibt sich nach der Division ein Rest von 0 oder 1, so ist die
 * Prüfziffer 0. Ansonsten ist der Rest vom Divisor (11) zu subtrahieren. Das
 * Ergebnis ist die Prüfziffer. <br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * 3-stellige Kontonummern bzw. Kontonummern, deren Stellen 1 bis 7 = 0 sind,
 * enthalten keine Prüfziffer und sind als richtig anzusehen.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 0 0 0 0 10 5 8 4 2<br/>
 * 
 * Summe der Produkte dividiert durch 11 = x,<br/>
 * Rest Rest = 0 oder 1 Prüfziffer = 0<br/>
 * Rest = 2 bis 10 Prüfziffer = 11 – Rest<br/>
 * 
 * Beispiel:<br/>
 * 
 * Kontonr.: 0 5 2 1 0 0 3 2 8 7 <br/>
 * Gewichtung: 0 0 0 0 10 5 8 4 2 P<br/>
 * Produkt: 0+ 0+ 0+ 0+ 0+ 0+ 24+ 8+ 16 =48<br/>
 * 48 : 11 = 4, Rest 4 <br/>
 * 11 - 4 = 7 = P<br/>
 * 
 * Testkontonummern: 521003287, 54500, 3287, 18761, 28290
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA0 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 0, 0, 10, 5, 8, 4, 2 };
	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] == 0) && (accountNumber[1] == 0)
				&& (accountNumber[2] == 0) && (accountNumber[3] == 0)
				&& (accountNumber[4] == 0) && (accountNumber[5] == 0)
				&& (accountNumber[6] == 0)) {
			setException(true);
			return true;
		}
		return accountNumber[9] == calcChecksum(accountNumber);
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < accountNumber.length - 1; i++) {
			sum += accountNumber[i] * WEIGHTS[i];
		}
		return ((sum % 11 == 1) || (sum % 11 == 0)) ? 0 : (11 - sum % 11);
	}

}
