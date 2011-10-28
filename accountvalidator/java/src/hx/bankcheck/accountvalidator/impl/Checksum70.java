/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die einzelnen Stellen der Kontonummer sind
 * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 2, 3, 4 zu
 * multiplizieren. Die Berechnung erfolgt wie bei Verfahren 06. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 4 3 2 7 6 5 4 3 2 <br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * Ist die 4. Stelle der Kontonummer = 5 oder die 4. bis 5. Stelle der
 * Kontonummer= 69, so werden die Stellen 1 bis 3 nicht in die
 * Prüfzifferermittlung einbezogen.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x 5 x x x x x P <br/>
 * Kontonr.: x x x 6 9 x x x x P <br/>
 * Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum70 extends Checksum06 {

	private static final int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS_EXCEPTION = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[3] == 5)
				|| ((accountNumber[3] == 6) && (accountNumber[4] == 9))) {
			setException(true);
			setWeights(WEIGHTS_EXCEPTION);
			return accountNumber[9] == calcChecksum(accountNumber);
		} else {
			setWeights(WEIGHTS);
			return accountNumber[9] == calcChecksum(accountNumber);
		}
	}
}
