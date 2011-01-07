package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig,
 * ggf. ist die Kontonummer für die Prüfzifferberechnung durch
 * linksbündige Auffüllung mit Nullen 10-stellig darzustellen.
 * <br/>
 * <h1>Variante 1:</h1>
 * Die Berechnung entspricht der Methode 07.
 * Führt die Berechnung nach Variante 1 zu einem Prüfziffer-
 * fehler, so ist nach Variante 2 zu prüfen.
 * Testkontonummern richtig: 3409, 585327, 1650513
 * Testkontonummern falsch: 33394, 595795, 16400501
 * <br/>
 * <h1>Variante 2</h1>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * Die Berechnung entspricht der Methode 03.
 * Führt die Berechnung nach Variante 2 zu einem Prüfziffer-
 * fehler, so ist nach Variante 3 zu prüfen.
 * Testkontonummern richtig: 3601671056, 4402001046,
 * 6100268241
 * Testkontonummern falsch: 3615071237, 6039267013,
 * 6039316014
 * <br/>
 * <h1>Variante 3</h1>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * Die Berechnung entspricht der Methode 00.
 * Führt auch die Berechnung nach Variante 3 zu einem
 * Prüfzifferfehler, so ist die Kontonummer falsch.
 * Testkontonummern richtig: 7001000681, 9000111105,
 * 9001291005
 * Testkontonummern falsch: 7004017653, 9002720007,
 * 9017483524
 *
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class ChecksumD6 extends AbstractChecksumValidator {

	private Checksum00 check00 = new Checksum00();
	private Checksum03 check03 = new Checksum03();
	private Checksum07 check07 = new Checksum07();
	
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return check07.validate(accountNumber) ||
				check03.validate(accountNumber) ||
				check00.validate(accountNumber);
	}
	
	
	
}
