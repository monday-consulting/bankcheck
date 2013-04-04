package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen.
 * <p>
 * Die Berechnung der Prüfziffer und die möglichen Ergebnisse richten sich nach
 * dem jeweils bei der entsprechenden Variante angegebenen Kontonummernkreis.
 * Entspricht eine Kontonummer keinem der vorgegebenen Kontonummernkreise oder
 * führt die Berechnung der Prüfziffer nach der Variante 1 zu einem
 * Prüfzifferfehler, so ist die Kontonummer ungültig.
 * <p>
 * <b>Variante 1:</b><br>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br>
 * Für Kontonummern aus dem Kontonummernkreis 1000000000 bis 9999999999
 * entsprechen die Berechnung und mögliche Ergebnisse der Methode 00.
 * <p>
 * <b>Variante 2:</b><br>
 * Für den Kontonummernkreis 0010000000 bis 0099999999 gilt die Methode 09
 * (keine Prüfzifferberechnung, alle Kontonummern sind als richtig zu werten).
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 */
public class ChecksumD8 extends AbstractChecksumValidator {
	//private final static Logger LOG = Logger.getLogger(ChecksumD8.class.getName());

	private Checksum00 cs00 = new Checksum00();
	
	public ChecksumD8() {
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		// 1000000000 bis 9999999999
		if (accountNumber[0] != 0) {
			// Variante 1
			return cs00.validate(accountNumber);
		}
		// 0000000000 bis 0009999999
		else if (accountNumber[0] == 0 && accountNumber[1] == 0 && accountNumber[2] == 0) {
			return false;
		} else {
			// Variante 2
			return true;
		}
	}
}
