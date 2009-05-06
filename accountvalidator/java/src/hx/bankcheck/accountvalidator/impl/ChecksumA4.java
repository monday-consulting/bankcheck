/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * 1. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0 <br/>
 * 2. Modulus 7, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0 <br/>
 * 3. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 0, 0 <br/>
 * 4. Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. Zur Prüfung einerKontonummer sind die
 * folgenden Varianten zu rechnen. Dabei ist zu beachten, dass Kontonummern mit
 * der Ziffernfolge 99 an den Stellen 3 und 4 (XX99XXXXXX) nur nach Variante 3
 * und ggf. 4 zu prüfen sind. Alle anderen Kontonummern sind nacheinander nach
 * den Varianten 1, ggf. 2 und ggf. 4 zu prüfen.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0<br/>
 * 
 * In die Prüfzifferberechnung werden nur die Stellen 4 bis 9 einbezogen. Die
 * Stelle 10 ist die Prüfziffer.Die weitere Berechnung erfolgt nach dem
 * Verfahren 06.<br/>
 * 
 * Beispiel: <br/>
 * 
 * Kontonr.: 0 0 0 4 7 1 1 1 7 3 <br/>
 * Gewichtung: 0 0 0 7 6 5 4 3 2 P <br/>
 * Produkt: 0+ 0+ 0+28+42+ 5+ 4+ 3+14=96<br/>
 * 96 : 11 = 8, Rest 8<br/>
 * 11 - 8 = 3 = P
 * 
 * Testkontonummern (richtig): 0004711173, 0007093330<br/>
 * Testkontonummern (falsch): 0004711172, 8623420004, 0001123458 <br/>
 * 
 * Führt die Berechnung zu einem Fehler, ist nach Variante 2 zu prüfen. <br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6, 7, 0, 0, 0<br/>
 * 
 * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den
 * Gewichten multipliziert. Die jeweiligen Produkte werden addiert. Die Summe
 * ist durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist die Prüfziffer (Stelle 10). Verbleibt nach der
 * Division kein Rest, ist die Prüfziffer 0.<br/>
 * 
 * Beispiel:<br/>
 * 
 * Kontonr.: 0 0 0 4 7 1 1 1 7 2 <br/>
 * Gewichtung: 0 0 0 7 6 5 4 3 2 P <br/>
 * Produkt: 0+ 0+ 0+28+42+ 5+ 4+ 3+ 14 =96 96 : 7 = 13, Rest 5<br/>
 * 7 - 5 = 2 = P<br/>
 * 
 * Testkontonummern (richtig): 0004711172, 0007093335<br/>
 * Testkontonummern (falsch): 8623420000, 0001123458<br/>
 * 
 * Führt die Berechnung zu einem Fehler, ist nach Variante 4 zu prüfen.
 * 
 * <b>Variante 3: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 0, 0<br/>
 * 
 * In die Prüfzifferberechnung werden nur die Stellen 5 bis 9 einbezogen. Die
 * Stelle 10 ist die Prüfziffer. Die weitere Berechnung erfolgt nach dem
 * Verfahren 06.<br/>
 * 
 * Beispiel: <br/>
 * 
 * Kontonr.: 1 1 9 9 5 0 3 0 1 0<br/>
 * Gewichtung: 0 0 0 0 6 5 4 3 2 P<br/>
 * Produkt: 0+ 0+ 0+ 0+30+0+12+ 0+ 2 =44<br/>
 * * 44: 11 = 4, Rest <br/>
 * 0 = P <br/>
 * 
 * Testkontonummern(richtig): 1199503010, 8499421235 <br/>
 * Testkontonummern (falsch): 1299503117, 6099702031<br/>
 * 
 * Führt die Berechnung zu einem Fehler, ist nach Variante 4 zu prüfen.
 * 
 * <b>Variante 4:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6<br/>
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Gewichtung und Berechnung erfolgen nach Methode 93.
 * 
 * Testkontonummern (richtig): 0000862342, 8997710000, 0664040000<br/>
 * (Modulus 7) 0000905844,5030101099<br/>
 * (Modulus 11) 0001123458, 1299503117<br/>
 * 
 * Testkontonummern (falsch): 0000399443, 0000553313<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA4 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE3 = { 0, 0, 0, 0, 6, 5, 4, 3,
			2 };

	private int alternative = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[2] == 9) && (accountNumber[3] == 9)) {
			setAlternative(2);
			if (new Checksum06(WEIGHTS_ALTERNATIVE3).validate(accountNumber)) {
				return true;
			} else {
				setAlternative(3);
				return new Checksum93().validate(accountNumber);
			}
		} else {
			setAlternative(0);
			if (new Checksum06(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
				return true;
			} else {
				setAlternative(1);
				if (accountNumber[9] == calcChecksumAlternative2(accountNumber)) {
					return true;
				} else {
					setAlternative(3);
					return new Checksum93().validate(accountNumber);
				}
			}
		}
	}

	private int calcChecksumAlternative2(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < accountNumber.length - 1; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE2[i];
		}
		return (sum % 7 == 0) ? 0 : (7 - sum % 7);
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}
}
