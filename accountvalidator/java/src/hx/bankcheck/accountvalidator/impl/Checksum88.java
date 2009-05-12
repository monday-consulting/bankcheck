/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die Stellen 4 bis 9 werden von rechts nach links mit den
 * Gewichten 2, 3, 4, 5, 6, 7 multipliziert.<br/>
 * 
 * Die restliche Berechnung und mögliche Ergebnisse entsprechen dem Verfahren
 * 06. Die Stelle 10 der Kontonummer ist per Definition die Prüfziffer.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P<br/>
 * Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8 <br/>
 * 
 * Ist die 3. Stelle der Kontonummer = 9, so werden die Stellen 3 bis 9 von
 * rechts nach links mit den Gewichten 2, 3, 4, 5, 6, 7, 8 multipliziert. Die
 * weitere Berechnung erfolgt wie bei Verfahren 06.<br/>
 * 
 * Testkontonummern: 2525259, 1000500, 90013000, 92525253, 99913003<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum88 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_EXCEPTION = { 0, 0, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[2] == 9) {
			setException(true);
			return new Checksum06(WEIGHTS_EXCEPTION).validate(accountNumber);
		} else {
			return new Checksum06(WEIGHTS).validate(accountNumber);
		}
	}

}
