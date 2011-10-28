/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * 
 * Die Kontonummern sind 6- bis 10-stellig und enthalten keine führenden Nullen.
 * Die erste Stelle von rechts ist die Prüfziffer. Die Berechnung erfolgt wie
 * bei Verfahren 00, hierbei sind jedoch folgende Besonderheiten zu beachten:<br/>
 * 
 * Bei 10-stelligen Kontonummern erfolgt die Berechnung für die 2. bis 7.
 * Stelle. Stelle 7 muss eine »9« sein.<br/>
 * 
 * Stellennr.: A 9 8 7 6 5 4 3 2 1 (A = 10)<br/>
 * Kontonr.: 8 8 8 9 6 5 4 3 2 P <br/>
 * Gewichtung: 1 2 1 2 1 2 9 + 3 + 5 + 8 + 3 + 4 = 32 (Q) (Q = Quersumme)<br/>
 * 
 * Die Einerstelle der Summe wird vom Wert 10 subtrahiert (10 - 2 = 8).<br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 8 und die vollständige Kontonummer
 * lautet: 8 8 8 9 6 5 4 3 2 8<br/>
 * 
 * 6- bis 9-stellige Kontonummern sind in zwei Varianten prüfbar.<br/>
 * 
 * <b>Variante 1: voll prüfbar</b><br/>
 * 
 * Kontonr.: 9 8 7 6 5 4 3 2 P Gewichtung: 1 2 1 2 1 2 1 2 9 + 7 + 7 + 3 + 5 + 8
 * + 3 + 4 = 46 (Q) (Q) (Q = Quersumme)<br/>
 * 
 * Die Einerstelle der Summe wird vom Wert 10 subtrahiert (10 - 6 = 4).<br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 4 und die vollständige Kontonummer
 * lautet: 9 8 7 6 5 4 3 2 4<br/>
 * 
 * Ergibt die Berechnung nach Variante 1 einen Prüfzifferfehler, muss Variante 2
 * zu einer korrekten Prüfziffer führen.<br/>
 * 
 *<b> Variante 2:</b><br/>
 * 
 * Stellen 7 und 8 werden nicht geprüft.<br/>
 * 
 * Kontonr.: 9 8 7 6 5 4 3 2 P Gewichtung: 1 2 1 2 1 2 9 + 3 + 5 + 8 + 3 + 4 =
 * 32 (Q) (Q = Quersumme)<br/>
 * 
 * Die Einerstelle der Summe wird vom Wert 10 subtrahiert (10 - 2 = 8).<br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 8 und die vollständige Kontonummer
 * lautet: 9 8 7 6 5 4 3 2 8<br/>
 * 
 * 9-stellige Kontonummern im Nummernbereich 400 000 000 bis 499 999 999 sind
 * nicht prüfbar, da diese Nummern keine Prüfziffer enthalten.<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum68 extends Checksum00 {

	private static final int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 1, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 0, 1, 2, 1, 2, 1, 2, 1,
			2 };
	private static final int[] WEIGHTS_ALTERNATIVE3 = { 0, 1, 0, 0, 2, 1, 2, 1,
			2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((ChecksumUtils.countNeutralLeadingDigits(accountNumber) > 4)
				|| ((ChecksumUtils.parseLong(accountNumber) >= 400000000l) && (ChecksumUtils
						.parseLong(accountNumber) < 499999999l))) {
			return false;
		} else {
			if (accountNumber[0] != 0) {
				if (accountNumber[3] != 9) {
					return false;
				} else {
					setAlternative(0);
					setWeights(WEIGHTS_ALTERNATIVE1);
					return (accountNumber[9] == calcChecksum(accountNumber));
				}
			} else {
				setWeights(WEIGHTS_ALTERNATIVE2);
				if (accountNumber[9] == calcChecksum(accountNumber)) {
					setAlternative(1);
					return true;
				} else {
					setWeights(WEIGHTS_ALTERNATIVE3);
					setAlternative(2);
					return accountNumber[9] == calcChecksum(accountNumber);
				}
			}
		}
	}

}
