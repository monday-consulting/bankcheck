package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Position der einzelnen Ziffern von rechts nach links innerhalb der
 * Kontonummer gibt die Zeile 1 bis 4 der Transformationstabelle noch an. Aus
 * ihr sind die Übersetzungswerte zu summieren. Die Einerstelle wird von 10
 * subtrahiert. Die Differenz stellt die Prüfziffer dar. 
 * <br><br>
 * Beispiel: Kontonummer 2<br>
 * 8 4 7 1 6 9 4 8 P (P = Prüfziffer)<br> 
 * 1 4 3 2 1 4 3 2 1 (Transf.-Zeile)<br><br>
 * Transformationstabelle: <br>
 * Ziffer: 0 1 2 3 4 5 6 7 8 9<br> 
 * Zeile 1: 0 1 5 9 3 7 4 8 2 6 <br>
 * Zeile 2: 0 1 7 6 9 8 3 2 5 4 <br>
 * Zeile 3: 0 1 8 4 6 2 9 5 7 3 <br>
 * Zeile 4: 0 1 2 3 4 5 6 7 8 9 <br><br>
 * Von rechts nach links:<br> 
 * Ziffer 8 wird 2 aus Transformationszeile 1<br> 
 * Ziffer 4 wird 9 aus Zeile 2 <br>
 * Ziffer 9 wird 3 aus Zeile 3 <br>
 * Ziffer 6 wird 6 aus Zeile 4 <br>
 * Ziffer 1 wird 1 aus Zeile 1 <br>
 * Ziffer 7 wird 2 aus Zeile 2 <br>
 * Ziffer 4 wird 6 aus Zeile 3 <br>
 * Ziffer 8 wird 8 aus Zeile 4 <br>
 * Ziffer 2 wird 5 aus Zeile 1 <br>
 * <br>
 * Summe = 42 <br><br>
 * Die Einerstelle wird vom Wert 10 subtrahiert. Das Ergebnis ist
 * die Prüfziffer, in unserem Beispiel also 10 – 2 = Prüfziffer 8, die
 * Kontonummer lautet somit 2847169488.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class ChecksumM10H extends AbstractChecksumValidator {

	private final static int[][] TRANSFORM_LINES = {
			{ 0, 1, 5, 9, 3, 7, 4, 8, 2, 6 }, 
			{ 0, 1, 7, 6, 9, 8, 3, 2, 5, 4 },
			{ 0, 1, 8, 4, 6, 2, 9, 5, 7, 3 }, 
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

	// Identifies the Line-Number for every digit
	private final static int[] TRANSFORM_ORDER = { 1, 4, 3, 2, 1, 4, 3, 2, 1 };

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int sum = 0;
		
		for(int i=0; i<9; i++) {
			int line = TRANSFORM_ORDER[i];
			int value = accountNumber[i];
			int newValue = TRANSFORM_LINES[line-1][value];
			sum += newValue;
		}
		
		int checksum = (10 - (sum % 10) % 10);
		
		return checksum == accountNumber[9];
	}

}
