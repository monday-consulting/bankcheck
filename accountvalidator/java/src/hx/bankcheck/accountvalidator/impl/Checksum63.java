/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Aufbau der 9-stelligen Kontonummer (innerhalb des zwischenbetrieblichen
 * 10-stelligen Feldes)</br>
 * 
 * Stelle 1 = gehört nicht zur Kontonummer, muss daher »0« oder »blank« sein<br/>
 * 
 * Stelle 2-7 = Grundnummer (Kundennummer; kann auch führende Nullen enthalten) <br/>
 * 
 * Stelle 8 = Prüfziffer 9-10 = Unterkontonummer Die für die Berechnung
 * relevante 6-stellige Grundnummer (Kundennummer) befindet sich in den Stellen
 * 2 bis 7, die Prüfziffer in Stelle 8 der Kontonummer. <br/>
 * 
 * Die zweistellige Unterkontonummer (Stellen 9 und 10) ist nicht in das
 * Prüfzifferverfahren mit einzubeziehen.<br/>
 * 
 * Die einzelnen Stellen der Grundnummer sind von rechts nach links mit den
 * Ziffern 2, 1, 2, 1, 2, 1 zu multiplizieren. Die jeweiligen Produkte werden
 * addiert, nachdem jeweils aus den zweistelligen Produkten die Quersumme
 * gebildet wurde (z. B. Produkt 16 = Quersumme 7). Nach der Addition bleiben
 * außer der Einerstelle alle anderen Stellen unberücksichtigt. Die Einerstelle
 * wird von dem Wert 10 subtrahiert. Das Ergebnis ist die Prüfziffer (Stelle 8).
 * Hat die Einerstelle den Wert »0«, ist die Prüfziffer »0«. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: 0 1 2 3 4 5 6 P 0 0<br/>
 * Gewichtung: 1 2 1 2 1 2 1 + 4 + 3 + 8 + 5 + 3 = 24 (Q) (Q = Quersumme)
 * 
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 4 = 6).<br/>
 * Die Prüfziffer ist in dem Beispiel die 6 und die vollständige Kontonummer
 * lautet: 1 2 3 4 5 6 6 0 0<br/>
 * 
 * <b>Ausnahmen: </b><br/>
 * 
 * Ist die Ziffer in Stelle 1 vor der sechsstelligen Grundnummer nicht »0« (oder
 * »blank«), ist das Ergebnis als falsch zu werten. <br/>
 * 
 * Ist die Unterkontonummer »00«, kann es vorkommen, dass sie auf den
 * Zahlungsverkehrsbelegen nicht angegeben ist, die Kontonummer jedoch um
 * führende Nullen ergänzt wurde. In diesem Fall sind z. B. die Stellen 1 bis 3
 * »000« (oder »blank«), die Prüfziffer ist an der Stelle 10 und die Berechnung
 * ist wie folgt durchzuführen: <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: 0 0 0 1 2 3 4 5 6 6 <br/>
 * Gewichtung: 0 0 0 1 2 1 2 1 2 1 + 4 + 3 + 8 + 5 + 3 = 24 (Q) (Q = Quersumme) <br/>
 * 10 - 4 = 6 Prüfziffer richtig
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum63 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 1, 2, 1, 2, 1, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (accountNumber[0] != 0) {
			return false;
		} else {
			if (accountNumber[7] == calcChecksum(accountNumber)) {
				setAlternative(0);
				return true;
			} else {
				if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) > 1) {
					setAlternative(1);
					return accountNumber[9]==calcChecksumForExceptions(accountNumber);
				} else {
					return false;
				}
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i + 1] * WEIGHTS[i]);
		}
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}

	private int calcChecksumForExceptions(int[] accountNumber) {
		int sum = 0;
		for (int i = WEIGHTS.length - 1; i >= 0; i--) {
			sum += ChecksumUtils.qs(accountNumber[accountNumber.length
					- WEIGHTS.length + i-1]
					* WEIGHTS[i]);
		}
		return (sum % 10 == 0) ? 0 : (10 - sum % 10);
	}

}
