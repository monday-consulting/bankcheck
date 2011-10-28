/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)<br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 00 <br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist die 4. Stelle der Kontonummer (von links) = 9, so werden die Stellen 1
 * bis 3 nicht in die Prüfzifferberechnung einbezogen. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: 4 0 1 9 1 1 0 0 0 8<br/>
 * Ktonr. umgesetzt 0 0 0 9 1 1 0 0 0 8 <br/>
 * Gewichtung: 1 2 1 2 1 2 9 + 2 + 1 + 0 + 0 + 0 = 12 10 - 2 = 8 8 = Prüfziffer <br/>
 * 
 * Testkontonummern: 4013410024, 4016660195, 0166805317 4019310079, 4019340829,
 * 4019151002<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum41 extends Checksum00 {

	private static final int[] WEIGHTS_EXCEPTION = { 0, 0, 0, 1, 2, 1, 2, 1, 2 };

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[3] == 9) {
			setException(true);
			setWeights(WEIGHTS_EXCEPTION);
			return super.validate(accountNumber);
		} else {
			return super.validate(accountNumber);
		}
	}
}
