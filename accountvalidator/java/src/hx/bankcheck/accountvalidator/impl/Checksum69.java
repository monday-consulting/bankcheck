/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8<br/>
 * 
 * Für den Kontonummernkreis 9 300 000 000 bis 9 399 999 999 ist keine
 * Prüfzifferberechnung möglich = Kennzeichen 09.<br/>
 * 
 * Für den Kontonummernkreis 9 700 000 000 bis 9 799 999 999 ist die
 * Prüfzifferberechnung nach Variante 2 vorzunehmen: <br/>
 * 
 * Für alle anderen Kontonummern ist die Prüfziffer nach Variante 1 zu
 * ermitteln. Ergab die Berechnung nach Variante 1 einen Prüfzifferfehler, ist
 * die Prüfziffer nach Variante 2 zu ermitteln. <br/>
 * 
 * <b>Variante 1 </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8 <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 28.
 * 
 * <b>Variante 2 </b><br/>
 * 
 * Die Position der einzelnen Ziffern von rechts nach links innerhalb der
 * Kontonummer gibt die Zeile 1 bis 4 der Transforma-tionstabelle an. Aus ihr
 * sind die Übersetzungswerte zu summieren. Die Einerstelle wird von 10
 * subtrahiert und stellt die Prüfziffer dar. <br/>
 * 
 * Beispiel: <br/>
 * 
 * Kontonr.: 9 7 2 1 1 3 4 8 6 P <br/>
 * Gewichtung: 1 4 3 2 1 4 3 2 1 (P = Prüfziffer) (Transf.-Zeile)<br/>
 * 
 * Transformationstabelle:<br/>
 * 
 * Ziffer : 0 1 2 3 4 5 6 7 8 9 <br/>
 * Zeile 1 : 0 1 5 9 3 7 4 8 2 6 <br/>
 * Zeile 2 : 0 1 7 6 9 8 3 2 5 4 <br/>
 * Zeile 3 : 0 1 8 4 6 2 9 5 7 3 <br/>
 * Zeile 4 : 0 1 2 3 4 5 6 7 8 9 <br/>
 * 
 * Von rechts nach links:
 * 
 * Ziffer 6 wird 4 aus Transformationszeile 1 <br/>
 * Ziffer 8 wird 5 aus Zeile 2 <br/>
 * Ziffer 4 wird 6 aus Zeile 3 <br/>
 * Ziffer 3 wird 3 aus Zeile 4 <br/>
 * Ziffer 1 wird 1 aus Zeile 1 <br/>
 * Ziffer 1 wird 1 aus Zeile 2 <br/>
 * Ziffer 2 wird 8 aus Zeile 3 <br/>
 * Ziffer 7 wird 7 aus Zeile 4 <br/>
 * Ziffer 9 wird 6 aus Zeile 1 <br/>
 * __ Summe 41 == Die Einerstelle wird vom Wert 10 subtrahiert. <br/>
 * 
 * Das Ergebnis ist die Prüfziffer, in unserem Beispiel also 10 - 1 = Prüfziffer
 * 9, die Kontonummer lautet: 9 7 2 1 1 3 4 8 6 9. <br/>
 * 
 * Testkontonummern: 1234567900 (Variante 1) 1234567006 (Variante 2)
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum69 extends AbstractChecksumValidator {

	private static final int[][] TRANSFORMATION_TABLE = {
			{ 0, 1, 5, 9, 3, 7, 4, 8, 2, 6 }, { 0, 1, 7, 6, 9, 8, 3, 2, 5, 4 },
			{ 0, 1, 8, 4, 6, 2, 9, 5, 7, 3 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 8, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 1, 4, 3, 2, 1, 4, 3, 2,
			1 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		long accountNumberAsLong = ChecksumUtils.parseLong(accountNumber);
		if ((accountNumberAsLong >= 9300000000l)
				&& (accountNumberAsLong < 9400000000l)) {
			return new Checksum09().validate(accountNumber);
		} else {
			if ((accountNumberAsLong >= 9700000000l)
					&& (accountNumberAsLong < 9800000000l)) {
				setAlternative(1);
				return accountNumber[9] == calcChecksum(accountNumber);
			} else {
				if (new Checksum28(WEIGHTS_ALTERNATIVE1)
						.validate(accountNumber)) {
					setAlternative(0);
					return true;
				} else {
					setAlternative(1);
					return accountNumber[9] == calcChecksum(accountNumber);
				}
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2.length; i++) {
			sum += TRANSFORMATION_TABLE[WEIGHTS_ALTERNATIVE2[i] - 1][accountNumber[i]];
		}
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}
}
