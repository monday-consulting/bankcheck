/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Kontonummer ist zehnstellig. Darstellung der Kontonummer:<br/>
 * 
 * G G G S S S S P K U <br/>
 * 
 * G = Geschäftsstellennummer <br/>
 * S = Stammnummer <br/>
 * P = Prüfziffer <br/>
 * K = Kontenartziffer <br/>
 * U = Unterkontonummer <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 00 über Geschäftsstellennummer und
 * Stammnummer mit der Gewichtung 2, 1, 2, 1, 2, 1, 2. <br/>
 * 
 * Stellen: G G G S S S S P K U <br/>
 * Kontonr.: 1 2 3 4 5 6 7 0 0 <br/>
 * Gewichtung: 2 1 2 1 2 1 2 2 + 2 + 6 + 4 + 1 + 6 + 5 = 26 <br/>
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 6 = 4). Die Prüfziffer ist
 * in diesem Fall die 4 und die vollständige Kontonummer lautet: 1 2 3 4 5 6 7 4
 * 0 0 <br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist die Kontenartziffer (neunte Stelle der Kontonummer) eine 9, so werden die
 * neunte und zehnte Stelle der Kontonummer in die Prüfzifferermittlung
 * einbezogen.<br/>
 * 
 * Die Berechnung erfolgt dann über Geschäftsstellennummer, Stammnummer,
 * Kontenartziffer und Unterkontonummer mit der Gewichtung 2, 1, 2, 1, 2, 1, 2,
 * 1, 2. <br/>
 * 
 * Stellen: G G G S S S S P K U <br/>
 * Kontonr.: 1 2 3 4 5 6 7 9 0<br/>
 * Gewichtung: 2 1 2 1 2 1 2 1 2 2 + 2 + 6 + 4 + 1 + 6 + 5 + 9 + 0 = 35<br/>
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 5 = 5).<br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 5 und die vollständige Kontonummer
 * lautet: 1 2 3 4 5 6 7 5 9 0<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum65 extends Checksum00 {

	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 0, 0, 0 };
	private static final int[] WEIGHTS_EXCEPTION = { 2, 1, 2, 1, 2, 1, 2, 0, 1,
			2 };

	public Checksum65() {
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
		if (accountNumber[8] == 9) {
			setException(true);
			setWeights(WEIGHTS_EXCEPTION);
			return accountNumber[7] == calcChecksum(accountNumber);
		} else {
			return accountNumber[7] == calcChecksum(accountNumber);
		}
	}

}
