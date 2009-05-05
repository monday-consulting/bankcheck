package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * <b>Modulus 11:</b><br/>
 * 
 * Die Kontonummer (5, 6, 7, 8, 9 o. 10-stellig) ist durch linksbündige
 * Nullenauffüllung 10-stellig darzustellen.Danach ist die 10. Stelle die
 * Prüfziffer.<br/>
 * 
 * Die Kontonummer ist unter Weglassung der Prüfziffer (= Wert X) durch 11 zu
 * teilen. Das Ergebnis der Division ist ohne die Nachkomma-Stellen mit 11 zu
 * multiplizieren. Das Produkt ist vom 'Wert X' zu subtrahieren. <br/>
 * 
 * Ist das Ergebnis < 10, so entspricht das Ergebnis der Prüfziffer. <br/>
 * Ist das Ergebnis = 10, so ist die Prüfziffer = 0<br/>
 * 
 * Beispiel: 2 4 0 1 0 0 1 9 (8-stellige Kontonummer)<br/>
 * 1) 2 401 001 : 11 = 218 272,81<br/>
 * 2) 218 272 x 11 = 2 400 992 <br/>
 * 3) 2 401 001 - 2 400 992 = 9<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum97 implements ChecksumValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return (accountNumber[9] == calcChecksum(accountNumber));
	}

	protected int calcChecksum(int[] accountNumber) {
		int[] tmpIntArray = new int[accountNumber.length - 1];
		for (int i = 0; i < accountNumber.length - 1; i++) {
			tmpIntArray[i] = accountNumber[i];
		}
		Long valueX = new Long(ChecksumUtils.parseLong(tmpIntArray));
		return ((valueX - (new Long((valueX / 11) * 11))) == 10) ? 0
				: new Long(valueX - (new Long((valueX / 11) * 11))).intValue();
	}

}
