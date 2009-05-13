/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Darstellung der Kontonummer: <br/>
 * 
 * B B B S S S S P A U (10-stellig)<br/>
 * B = Betriebsstellennummer <br/>
 * S = Stammnummer <br/>
 * P = Prüfziffer <br/>
 * A = Artziffer <br/>
 * U = Unternummer <br/>
 * 
 * <b>Ausnahme: </b>
 * 
 * Ist die Artziffer (neunte Stelle der Kontonummer) eine 8, so werden die
 * neunte und zehnte Stelle der Kontonummer in die Prüfzifferermittlung
 * einbezogen. Die Berechnung erfolgt dann über Betriebsstellennummer,
 * Stammnummer, Artziffer und Unternummer mit der Gewichtung 2, 1, 2, 1, 2, 1,
 * 2, 1, 2. <br/>
 * 
 * <b>Beispiel 1: </b><br/>
 * 
 * Stellennr.: B B B S S S S P A U <br/>
 * Kontonr.: 2 0 6 3 0 9 9 0 0<br/>
 * Gewichtung: 2 1 2 1 2 1 2 4 + 0 + 3 + 3 + 0 + 9 + 9 = 28 <br/>
 * 
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 8 = 2). Die Prüfziffer ist
 * in diesem Fall die 2 und die vollständige Kontonummer lautet: 2 0 6 3 0 9 9 2
 * 0 0 <br/>
 * 
 * <b>Beispiel 2: </b><br/>
 * 
 * Stellennr.: B B B S S S S P A U <br/>
 * Kontonr.: 0 2 6 0 7 6 0 8 1 <br/>
 * Gewichtung: 2 1 2 1 2 1 2 1 2 0 + 2 + 3 + 0 + 5 + 6 + 0 + 8 + 2 = 26 <br/>
 * 
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 6 = 4). Die Prüfziffer ist
 * in diesem Fall die 4 und die vollständige Kontonummer lautet: 0 2 6 0 7 6 0 4
 * 8 1<br/>
 * s
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum61 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 0, 0, 0 };
	private static final int[] WEIGHTS_EXCEPTION = { 2, 1, 2, 1, 2, 1, 2, 0, 1,
			2 };

	public Checksum61() {
		setWeights(WEIGHTS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[8] == 8) {
			setException(true);
			setWeights(WEIGHTS_EXCEPTION);
		}
		return accountNumber[7] == calcChecksum(accountNumber);
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < getWeights().length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * getWeights()[i]);
		}
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}

}
