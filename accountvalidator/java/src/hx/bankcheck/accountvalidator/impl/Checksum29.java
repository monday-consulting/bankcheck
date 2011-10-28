package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 10, iterierte Transformation
 * 
 * Die einzelnen Ziffern der Kontonummer werden über eine Tabelle in andere
 * Werte transformiert. Jeder einzelnen Stelle der Kontonummer ist hierzu eine
 * der Zeilen 1 bis 4 der Transformationstabelle fest zugeordnet. Die
 * Transformationswerte werden addiert. Die Einerstelle der Summe wird von 10
 * subtrahiert. Das Ergebnis ist die Prüfziffer. (Ist das Ergebnis = 10, ist die
 * Prüfziffer = 0). </br>
 * 
 * <b>Beispiel:</b> </br>
 * 
 * Kontonummer: 3 1 4 5 8 6 3 0 2 P (P = Prüfziffer) </br> Die Kontonummer ist
 * 10-stellig. Die 10. Stelle ist die Prüfziffer. </br>
 * 
 * Zugeordnete Zeile der Transformationstabelle: </br>
 * 
 * 1 4 3 2 1 4 3 2 1 </br>
 * 
 * Transformationstabelle: </br>
 * 
 * Ziffer: 0 1 2 3 4 5 6 7 8 9 </br>
 * 
 * Zeile 1: 0 1 5 9 3 7 4 8 2 6 </br>
 * 
 * Zeile 2: 0 1 7 6 9 8 3 2 5 4 </br>
 * 
 * Zeile 3: 0 1 8 4 6 2 9 5 7 3 </br>
 * 
 * Zeile 4: 0 1 2 3 4 5 6 7 8 9 </br>
 * 
 * Transformation von rechts nach links: </br>
 * 
 * Ziffer 2 wird 5 (Tabelle: Zeile 1)
 * 
 * </br> " 0 wird 0 (" " 2)
 * 
 * </br> " 3 wird 4 (" " 3)
 * 
 * </br> " 6 wird 6 (" " 4)
 * 
 * </br> " 8 wird 2 (" " 1)
 * 
 * </br> " 5 wird 8 (" " 2)
 * 
 * </br> " 4 wird 6 (" " 3)
 * 
 * </br> " 1 wird 1 (" " 4)
 * 
 * </br> " 3 wird 9 (" " 1)
 * 
 * </br> ___ Summe: 41
 * 
 * </br> (Einerstelle = 1) </br>
 * 
 * Subtraktion : (10 - 1) = 9 (= Prüfziffer) </br>
 * 
 * Kontonummer mit Prüfziffer: 3 1 4 5 8 6 3 0 2 9 </br>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum29 extends AbstractChecksumValidator {

	private static final int[][] transformationTable = {
			{ 0, 1, 5, 9, 3, 7, 4, 8, 2, 6 }, { 0, 1, 7, 6, 9, 8, 3, 2, 5, 4 },
			{ 0, 1, 8, 4, 6, 2, 9, 5, 7, 3 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[9] == calcChecksum(accountNumber);
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		int j = 0;
		String a = "KTO:";
		for (int i = accountNumber.length - 2; i >= 0; i--) {
			sum += transformationTable[j][accountNumber[i]];
			a += transformationTable[j][accountNumber[i]];
			j++;
			j %= 4;
		}
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}
}
