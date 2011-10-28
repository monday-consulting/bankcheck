/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.Checksum57Util;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. <br/>
 * 
 * Die Berechnung der Prüfziffer und die möglichen Ergebnisse richten sich nach
 * dem jeweils bei der entsprechenden Variante angegebenen Kontonummernkreis.
 * Führt die Berechnung der Prüfziffer nach der vorgegebenen Variante zu einem
 * Prüfzifferfehler, so ist die Kontonummer ungültig. <br/>
 * 
 * Kontonummern, die mit 00 beginnen sind immer als falsch zu bewerten.<br/>
 * 
 * <b>Variante 1: </b><br/>
 * 
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Anzuwenden ist dieses Verfahren für Kontonummern, die mit den folgenden
 * Zahlen beginnen: 51, 55, 61, 64, 65, 66, 70, 73, 75 bis 82, 88, 94 und 95.<br/>
 * 
 * Die Stellen 1 bis 9 der Kontonummer sind von links beginnend mit den
 * Gewichten zu multiplizieren. Die 10. Stelle ist die Prüfziffer. Die
 * Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * 
 * Stellen-Nr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Konto-Nr.: X X X X X X X X X P <br/>
 * Gewichtung 1 2 1 2 1 2 1 2 1<br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * Kontonummern, die mit den Zahlen 777777 oder 888888 beginnen sind immer als
 * richtig (= Methode 09; keine Prüfzifferberechnung) zu bewerten. <br/>
 * 
 * <b>Variante 2</b>:<br/>
 * 
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Anzuwenden ist dieses Verfahren für Kontonummern, die mit den folgenden
 * Zahlen beginnen: 32 bis 39, 41 bis 49, 52, 53, 54, 56 bis 60, 62, 63, 67, 68,
 * 69, 71, 72, 74, 83 bis 87, 89, 90, 92, 93, 96, 97 und 98.<br/>
 * 
 * Die Stellen 1, 2, 4, 5, 6, 7, 8, 9 und 10 der Kontonummer sind von links
 * beginnend mit den Gewichten zu multiplizieren. Die 3. Stelle ist die
 * Prüfziffer. Die Berechnung und mögliche Ergebnisse entsprechen der Methode
 * 00.<br/>
 * 
 * Stellen-Nr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Konto-Nr.: X X P X X X X X X X<br/>
 * Gewichtung 1 2 1 2 1 2 1 2 1 <br/>
 * 
 * <b>Variante 3: </b><br/>
 * 
 * Für die Kontonummern, die mit den folgenden Zahlen beginnen gilt die Methode
 * 09 (keine Prüfzifferberechnung): 40, 50, 91 und 99 <br/>
 * 
 * <b>Variante 4: </b><br/>
 * 
 * Kontonummern die mit 01 bis 31 beginnen haben an der dritten bis vierten
 * Stelle immer einen Wert zwischen 01 und 12 und an der siebten bis neunten
 * Stelle immer einen Wert kleiner 500. <br/>
 * 
 * <b>Ausnahme:</b><br/>
 * 
 * Die Kontonummer 0185125434 ist als richtig zu bewerten. <br/>
 * 
 * Testkontonummern (richtig): 7500021766, 9400001734, 7800028282, 8100244186,
 * 3251080371, 3891234567, 7777778800, 5001050352, 5045090090, 1909700805,
 * 9322111030 <br/>
 * Testkontonummern (falsch): 5302707782, 6412121212,
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum57 extends Checksum00 {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 1, 2, 1, 2, 1, 2, 1, 2,
			1, 0 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 1, 2, 0, 1, 2, 1, 2, 1,
			2, 1 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] == 0) && (accountNumber[1] == 0)) {
			return false;
		} else {
			if (((accountNumber[0] == 7) && (accountNumber[1] == 7)
					&& (accountNumber[2] == 7) && (accountNumber[3] == 7)
					&& (accountNumber[4] == 7) && (accountNumber[5] == 7))
					|| ((accountNumber[0] == 8) && (accountNumber[1] == 8)
							&& (accountNumber[2] == 8)
							&& (accountNumber[3] == 8)
							&& (accountNumber[4] == 8) && (accountNumber[5] == 8))
					|| (ChecksumUtils.parseLong(accountNumber) == 185125434l)) {
				setException(true);
				return new Checksum09().validate(accountNumber);
			} else {
				int first2Digits = accountNumber[0] * 10 + accountNumber[1];
				setAlternative(Checksum57Util.getAlternative(first2Digits));
				switch (getAlternative()) {
				case 0:
					setWeights(WEIGHTS_ALTERNATIVE1);
					return accountNumber[9] == super
							.calcChecksum(accountNumber);
				case 1:
					setWeights(WEIGHTS_ALTERNATIVE2);
					return accountNumber[2] == super
							.calcChecksum(accountNumber);
				case 2:
					return new Checksum09().validate(accountNumber);
				case 3:
					int a = accountNumber[2] * 10 + accountNumber[3];
					int b = accountNumber[6] * 100 + accountNumber[7] * 10
							+ accountNumber[8];
					return (a > 0 && a < 13) && (b < 500);
				default:
					return false;
				}
			}
		}
	}
}
