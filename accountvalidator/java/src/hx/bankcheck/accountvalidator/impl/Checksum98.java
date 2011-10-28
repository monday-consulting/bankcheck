/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, Gewichtung 3, 1, 7, 3, 1, 7, 3</br>
 * 
 * Die Kontonummer ist 10-stellig. Die Berechnung erfolgt wie bei Verfahren 01.
 * Es ist jedoch zu beachten, dass nur die Stellen 3 bis 9 in die
 * Prï¿½fzifferberechnung einbezogen werden. Die Stelle 10 der Kontonummer ist
 * die Prï¿½fziffer. </br>
 * 
 * Fï¿½hrt die Berechnung zu einem falschen Ergebnis, so ist alternativ das
 * Verfahren 32 anzuwenden.</br>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 </br> Kontonr.: x x x x x x x x x P </br>
 * Gewichtung: 3 7 1 3 7 1 3 </br> Beispiel: P </br> Kontonr.: 9 6 1 9 6 0 8 1 1
 * 8</br> Gewichtung: 3 7 1 3 7 1 3 </br> 3 + 63 + 6 + 0 + 56 + 1 + 3 = 132</br>
 * 10 - 2 = 8 </br> 8 = Prï¿½fziffer </br>
 * 
 * Testkontonummern: </br>
 * 
 * 9619439213, 3009800016, 9619509976, 5989800173, 9619319999, 6719430018
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum98 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 3, 7, 1, 3, 7, 1,
			3 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 3, 7, 1, 3, 7, 1,
			3 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (new Checksum01(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
			return true;
		} else {
			setAlternative(1);
			return new Checksum32(WEIGHTS_ALTERNATIVE2).validate(accountNumber);
		}
	}

}
