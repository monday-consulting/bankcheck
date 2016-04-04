/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen.<br/>
 * 
 * Kontonummern mit weniger als zwei oder mehr als drei führenden Nullen sind
 * falsch. Die Kontonummern mit zwei führenden Nullen sind nach Variante 1, mit
 * drei führenden Nullen nach Variante 2 zu prüfen.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus (11,10), Gewichtung 1, 3, 2, 1, 3, 2, 1<br/>
 * 
 * Die für die Berechnung relevanten Stellen der Kontonummer befinden sich - von
 * links nach rechts gelesen -in den Stellen 3-9 (die Prüfziffer ist in Stelle
 * 10). Sie sind von rechts nach links  mit den zugehörigen
 * Gewichtungsfaktoren zu multiplizieren.<br/>
 * 
 * Zum jeweiligen Produkt ist der zugehörige Gewichtungsfaktor zu addieren.Das
 * jeweilige Ergebnis ist durch 11 zu dividieren. Die sich aus der Division
 * ergebenden Reste sind zu summieren. Diese Summe ist durch 10 zu dividieren.
 * Der Rest ist die berechnete Prüfziffer.<br/>
 * 
 * Führt die Berechnung zu einem Prüfzifferfehler, so ist die berechnete
 * Prüfziffer um 5 zu erhöhen und erneut zu prüfen. Ist die Prüfziffer größer
 * oder gleich 10, ist 10 abzuziehen und das Ergebnis ist dann die Prüfziffer.<br/>
 * 
 * Rechenbeispiel mit der Testkontonummer 0087920187: <br/>
 * 
 * 8 x 1 = 8 + 1 = 9 9 : 11 = 0 Rest 9 <br/>
 * 7 x 2 = 14 + 2 = 16 16 : 11 = 1 Rest 5 <br/>
 * 9 x 3 = 27 + 3 = 30 30 : 11 = 2 Rest 8 <br/>
 * 2 x 1 = 2 + 1 = 3 3 : 11 = 0 Rest 3 <br/>
 * 0 x 2 = 0 + 2 = 2 2 : 11 = 0 Rest 2 <br/>
 * 1 x 3 = 3 + 3 = 6 6 : 11 = 0 Rest 6 <br/>
 * 8 x 1 = 8 + 1 = 9 9 : 11 = 0 Rest 9 <br/>
 * 
 * Summe der Reste: 42 42 : 10 = 4 Rest 2 (= falsche Prüfziffer) ==> 2 + 5 = 7
 * (= Prüfziffer)<br/>
 * 
 * Testkontonummern (richtig): 87920187, 41203755, 81069577, 61287958, 58467232 <br/>
 * Testkontonummern (falsch): 88034023, 43025432, 86521362, 61256523, 54352684 <br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 11, Gewichtung 1, 2, 3, 4, 5, 6 <br/>
 * 
 * Die für die Berechnung relevanten Stellen der Kontonummer befinden sich - von
 * links nach rechts gelesen-in den Stellen 4-9 (die Prüfziffer ist in Stelle
 * 10).Sie sind von rechts nach links mit den zugehörigen Gewichtungsfaktoren zu
 * multiplizieren.<br/>
 * 
 * Die Summe dieser Produkte ist zu bilden, und das erzielte Ergebnis ist durch
 * 11 zu dividieren. Der Rest ist die berechnete Prüfziffer.<br/>
 * 
 * Führt die Berechnung zu einem Prüfzifferfehler, so ist die berechnete
 * Prüfziffer um 5 zu erhöhen und erneut zu prüfen. <br/>
 * 
 * Ist die Prüfziffer größer oder gleich 10, ist 10 abzuziehen und das Ergebnis
 * ist dann die Prüfziffer.<br/>
 * 
 * Rechenbeispiel mit der Testkontonummer 7125633: <br/>
 * 
 * 7 x 6 = 42 <br/>
 * 1 x 5 = 5 <br/>
 * 2 x 4 = 8 <br/>
 * 5 x 3 = 15<br/>
 * 6 x 2 = 12 <br/>
 * 3 x 1 = 3 <br/>
 * 
 * Summe = 85 <br/>
 * 
 * 85 : 11 = 7 Rest 8 <br/>
 * (= falsche Prüfziffer) ==> 8 + 5 = 13 <br/>
 * (= Prüfziffer größer 10) ==> 13 - 10 = 3 (= Prüfziffer) <br/>
 * 
 * Testkontonummern (richtig): 7125633, 1253657, 4353631<br/>
 * 
 * Testkontonummern (falsch): 2356412, 5435886, 9435414<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB9 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERNATIVE1 = { 1, 2, 3, 1, 2, 3, 1 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 6, 5, 4, 3, 2, 1 };
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int leadingNeutralDigits = ChecksumUtils.countNeutralLeadingDigits(accountNumber);
		if ((leadingNeutralDigits != 2) && (leadingNeutralDigits != 3)) {
			return false;
		} else {
			if (leadingNeutralDigits == 2) {
				setAlternative(0);
				int checksum = calcChecksumAlternative1(accountNumber);
				return ((accountNumber[9] == checksum) || (accountNumber[9] == (checksum + 5) % 10));
			} else {
				setAlternative(1);
				int checksum = calcChecksumAlternative2(accountNumber);
				return ((accountNumber[9] == checksum) || (accountNumber[9] == (checksum + 5) % 10));
			}
		}
	}

	private int calcChecksumAlternative1(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE1.length; i++) {
			sum += (accountNumber[i + 2] * WEIGHTS_ALTERNATIVE1[i] + WEIGHTS_ALTERNATIVE1[i]) % 11;
		}
		return sum % 10;
	}

	private int calcChecksumAlternative2(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2.length; i++) {
			sum += accountNumber[i + 3] * WEIGHTS_ALTERNATIVE2[i];
		}
		return sum % 11;
	}

}
