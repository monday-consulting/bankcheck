package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 1, 2, 3, 1, 2, 3, 1, 2, 3 Die für die Berechnung
 * relevanten Stellen der Kontonummer befinden sich - von links nach rechts
 * gelesen -in den Stellen 1 - 9; die Prüfziffer in Stelle 10. Die Kontonummer
 * ist rechtsbündig zu interpretieren und ggf. mit Nullen aufzufüllen. Die
 * einzelnen Ziffern der Kontonummer sind, beginnend mit der ersten Ziffer
 * ungleich 0, von links nach rechts bis einschließlich Stelle 9 mit den o. g.
 * Gewichtungsfaktoren zu multiplizieren. Zum jeweiligen Produkt ist der
 * zugehörige Gewichtungsfaktor zu addieren (zum ersten Produkt +1, zum zweiten
 * +2, zum dritten +3, zum Vierten +1 usw.). Das jeweilige Ergebnis ist durch 11
 * zu dividieren (5 : 11 = 0 Rest 5). Die sich aus der Division ergebenden Reste
 * sind zu summieren. Die letzte Ziffer dieser Summe ist die Prüfziffer. <br>
 * <br>
 * <b>Ausnahmen:</b>
 * <ol>
 * <li>Eine ggf. in Stelle 1 vorhandene Ziffer 3, 4, 5 oder 6 wird als 0
 * gewertet. Der o. g. Prüfalgorithmus greift erst ab der ersten Stelle ungleich
 * 0.</li>
 * <li>Eine ggf. in Stelle 1 vorhandene Ziffer 9 wird als 0 gewertet und führt
 * dazu, dass auch die beiden nachfolgenden Ziffern in den Stellen 2 und 3 der
 * Kontonummer als 0 gewertet werden müssen. Der o. g. Prüfalgorithmus greift in
 * diesem Fall also erst ab Stelle 4 der 10stelligen Kontonummer. Die Stelle 4
 * ist ungleich 0.</li>
 * </ol>
 * <br>
 * Prüfnummern: 138301, 1306118605, 3307118608, 9307118603
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 *         $Id$
 */
public class Checksum24 extends AbstractChecksumValidator {

	private final static int[] WEIGHTS = { 1, 2, 3, 1, 2, 3, 1, 2, 3 };
	
	private int[] prepareNumber(int[] accountNumber) {
		// Copy number
		int[] n = new int[10];
		System.arraycopy(accountNumber, 0, n, 0, 10);
		
		// Exception 1
		if (n[0] == 3 || n[0] == 4 || n[0] == 5 || n[0] == 6) {
			n[0] = 0;
		}
		
		// Exception 2
		if (n[0] == 9) {
			n[0] = 0;
			n[1] = 0;
			n[2] = 0;
		}
		
		return n;
	}
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		accountNumber = prepareNumber(accountNumber);
		
		int sum = 0;
		int weightIdx = 0;
		for(int i=0; i<9; i++) {
			if (accountNumber[i] != 0 || weightIdx != 0) {
				sum += ((accountNumber[i]+1) * WEIGHTS[weightIdx++]) % 11;
			}
		}
		
		int checksum = sum % 10;
		
		return checksum == accountNumber[9];
	}

}
