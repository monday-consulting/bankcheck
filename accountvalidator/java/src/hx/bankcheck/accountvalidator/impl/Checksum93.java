package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * <b>Variante 1</b><br/>
 * 
 * Die Kontonummer ist 10-stellig.<br/>
 * 
 * Die für die Berechnung relevante Kundennummer (K) befindet sich entweder<br/>
 * 
 * a) in den Stellen 1 bis 5, die Prüfziffer in Stelle 6 der Kontonummer oder<br/>
 * b) in den Stellen 5 bis 9, die Prüfziffer in Stelle 10<br/>
 * 
 * der Kontonummer.<br/>
 * 
 * Die 2-stellige Unternummer (U) und die 2-stellige Kontoartnummer (A) werden
 * nicht in die Berechnung einbezogen.<br/>
 * 
 * Sie befinden sich im Fall a) an Stelle 7 bis 10 (UUAA).<br/>
 * Im Fall b) befinden sie sich an Stelle 1 bis 4 und müssen "0000" lauten.<br/>
 * 
 * Die 5-stellige Kundennummer wird von rechts nach links mit den Gewichten
 * multipliziert.<br/>
 * Die weitere Berechnung und die möglichen Ergebnisse entsprechen dem Verfahren
 * 06.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: Fall a) K K K K K P U U A A<br/>
 * Gewichtung: 6 5 4 3 2 <br/>
 * 
 * Kontonr.: Fall b) 0 0 0 0 K K K K K P<br/>
 * Gewichtung: 6 5 4 3 2<br/>
 * 
 * Führt die Berechnung nach Variante 1 zu einem Prüfziffer-fehler, so ist die
 * Berechnung nach Variante 2 vorzunehmen.<br/>
 * 
 * <b>Variante 2 </b><br/>
 * 
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6 <br/>
 * 
 * Das Berechnungsverfahren entspricht Variante 1. Die Summe der Produkte ist
 * jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7)
 * subtrahiert. Das Ergebnis ist dann die Prüfziffer. Verbleibt nach der
 * Division durch 7 kein Rest, lautet die Prüfziffer 0.<br/>
 * 
 * Testkontonummern:<br/>
 * 
 * Modulus 11: 6714790000 bzw. 0000671479<br/>
 * Modulus 7: 1277830000 bzw. 0000127783<br/>
 * 1277910000 bzw. 0000127791 <br/>
 * 
 * Modulus 11 und 7: 3067540000 bzw. 0000306754
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum93 implements ChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERNATIVE1_A = { 6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS_ALTERNATIVE1_B = { 6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS_ALTERNATIVE2_A = { 6, 5, 4, 3, 2 };
	private static final int[] WEIGHTS_ALTERNATIVE2_B = { 6, 5, 4, 3, 2 };

	private int methodFlag = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public int calcChecksum(int[] accountNumber) {
		switch (methodFlag) {
		case 0:
			return calcChecksumAlternative1A(accountNumber);
		case 1:
			return calcChecksumAlternative1B(accountNumber);
		case 2:
			return calcChecksumAlternative2A(accountNumber);
		case 3:
			return calcChecksumAlternative2B(accountNumber);
		default:
			return -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber.length != 10) {
			return false;
		} else {
			methodFlag = 0;
			if (accountNumber[5] == calcChecksum(accountNumber)) {
				return true;
			} else {
				methodFlag = 1;
				if ((accountNumber[0] == 0) && (accountNumber[1] == 0)
						&& (accountNumber[2] == 0) && (accountNumber[3] == 0)
						&& (accountNumber[9] == calcChecksum(accountNumber))) {
					return true;
				} else {
					methodFlag = 2;
					if (accountNumber[5] == calcChecksum(accountNumber)) {
						return true;
					} else {
						methodFlag = 3;
						if ((accountNumber[0] == 0)
								&& (accountNumber[1] == 0)
								&& (accountNumber[2] == 0)
								&& (accountNumber[3] == 0)
								&& (accountNumber[9] == calcChecksum(accountNumber))) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
	}

	private int calcChecksumAlternative1A(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE1_A.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE1_A[i];
		}
		return (sum % 11 == 1) || (sum % 11 == 0) ? 0 : (11 - sum % 11);
	}

	private int calcChecksumAlternative1B(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE1_B.length; i++) {
			sum += accountNumber[i + 4] * WEIGHTS_ALTERNATIVE1_B[i];
		}
		return (sum % 11 == 1) || (sum % 11 == 0) ? 0 : (11 - sum % 11);
	}

	private int calcChecksumAlternative2A(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2_A.length; i++) {
			sum += accountNumber[i] * WEIGHTS_ALTERNATIVE2_A[i];
		}
		return ((sum % 7 == 0) ? 0 : (7 - sum % 7));
	}

	private int calcChecksumAlternative2B(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS_ALTERNATIVE2_B.length; i++) {
			sum += accountNumber[i + 4] * WEIGHTS_ALTERNATIVE2_B[i];
		}
		return ((sum % 7 == 0) ? 0 : (7 - sum % 7));
	}

	/**
	 * @return the methodFlag
	 */
	public int getMethodFlag() {
		return this.methodFlag;
	}

}
