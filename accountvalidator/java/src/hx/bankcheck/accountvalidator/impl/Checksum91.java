package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * 
 * 1. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br />
 * 2. Modulus 11, Gewichtung 7, 6, 5, 4, 3, 2 <br />
 * 3. Modulus 11, Gewichtung 2, 3, 4, 0, 5, 6, 7, 8, 9, A (A = 10) <br />
 * 4. Modulus 11, Gewichtung 2, 4, 8, 5, 10, 9<br />
 * 
 * Gemeinsame Hinweise für die Berechnungsvarianten 1 bis 4: <br />
 * 
 * Die Kontonummer ist immer 10-stellig. Die einzelnen Stellen der Kontonummer
 * werden von links nach rechts von 1 bis 10 durchnummeriert. Die Stelle 7 der
 * Kontonummer ist die Prüfziffer. Die für die Berechnung relevanten
 * Kundennummern (K) sind von rechts nach links mit den jeweiligen Gewichten zu
 * multiplizieren. Die restliche Berechnung und möglichen Ergebnisse entsprechen
 * dem Verfahren 06.<br />
 * 
 * Ergibt die Berechnung nach der ersten beschriebenen Variante einen
 * Prüfzifferfehler, so sind in der angegebenen Reihenfolge weitere Berechnungen
 * mit den anderen Varianten vorzunehmen, bis die Berechnung keinen
 * Prüfzifferfehler mehr ergibt. Kontonummern, die endgültig nicht zu einem
 * richtigen Ergebnis führen, sind nicht prüfbar. <br />
 * 
 * <b>Variante 1:</b><br />
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br />
 * 
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.<br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br />
 * Kontonr.: K K K K K K P x x x <br />
 * Gewichtung: 7 6 5 4 3 2 <br />
 * 
 * Testkontonummern (richtig): 2974118000, 5281741000, 9952810000<br />
 * Testkontonummern (falsch): 8840017000, 8840023000, 8840041000<br />
 * 
 * <b>Variante 2:</b><br />
 * 
 * Modulus 11, Gewichtung 7, 6, 5, 4, 3, 2
 * 
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.<br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br />
 * Kontonr.: K K K K K K P x x x<br />
 * Gewichtung: 2 3 4 5 6 7<br />
 * Testkontonummern (richtig): 2974117000, 5281770000,9952812000 <br />
 * Testkontonummern (falsch): 8840014000, 8840026000, 8840045000<br />
 * 
 * <b>Variante 3: </b><br />
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 0, 5, 6, 7, 8, 9, A (A = 10)<br />
 * 
 * Die Stellen 1 bis 10 werden in die Berechnung einbezogen.<br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A= 10)<br />
 * Kontonr.: K K K K K K P x x x <br />
 * Gewichtung: 10 9 8 7 6 5 0 4 3 2<br />
 * 
 * Testkontonummern (richtig): 8840019000, 8840050000, 8840087000, 8840045000<br />
 * Testkontonummern (falsch): 8840011000, 8840025000, 8840062000<br />
 * 
 * <b>Variante 4:</b><br />
 * 
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 9 (A = 10)<br />
 * 
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.<br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)<br />
 * Kontonr.: K K K K K K P x x x<br />
 * Gewichtung: 9 10 5 8 4 2<br />
 * 
 * Testkontonummern (richtig): 8840012000, 8840055000, 8840080000 <br />
 * Testkontonummern (falsch): 8840010000, 8840057000<br />
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum91 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGTHS_1 = { 7, 6, 5, 4, 3, 2 };
	private static final int[] WEIGTHS_2 = { 2, 3, 4, 5, 6, 7 };
	private static final int[] WEIGTHS_3 = { 10, 9, 8, 7, 6, 5, 0, 4, 3, 2 };
	private static final int[] WEIGTHS_4 = { 9, 10, 5, 8, 4, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		while (getAlternative() < 4) {
			if (accountNumber[6] == calcChecksum(accountNumber)) {
				return true;
			}
			setAlternative(getAlternative() + 1);
		}
		return false;
	}

	protected int calcChecksum(int[] accountNumber) {
		switch (getAlternative()) {
		case 0:
			return calcChecksumMethod1(accountNumber); // Method 1
		case 1:
			return calcChecksumMethod2(accountNumber); // Method 2
		case 2:
			return calcChecksumMethod3(accountNumber); // Method 3
		case 3:
			return calcChecksumMethod4(accountNumber); // Method 4
		default:
			return -1;
		}
	}

	private int calcChecksumMethod1(int[] accountNumber) {
		return calcChecksumInternal(accountNumber, WEIGTHS_1);
	}

	private int calcChecksumMethod2(int[] accountNumber) {
		return calcChecksumInternal(accountNumber, WEIGTHS_2);
	}

	private int calcChecksumMethod3(int[] accountNumber) {
		return calcChecksumInternal(accountNumber, WEIGTHS_3);
	}

	private int calcChecksumMethod4(int[] accountNumber) {
		return calcChecksumInternal(accountNumber, WEIGTHS_4);
	}

	private int calcChecksumInternal(int[] accountNumber, int[] weights) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += weights[i] * accountNumber[i];
		}
		return (sum % 11 == 1) || (sum % 11 == 0) ? 0 : (11 - sum % 11);
	}

}
