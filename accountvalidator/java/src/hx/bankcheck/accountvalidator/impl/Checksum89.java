/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * 8- und 9-stellige Kontonummern sind mit dem Berechnungs-verfahren 10 zu
 * prüfen.<br/>
 * 
 * 7-stellige Kontonummern sind wie folgt zu prüfen:<br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br/>
 * 
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die für die Berechnung relevante 6- stellige Stammnummer (x)
 * befindet sich in den Stellen 4 bis 9, die Prüfziffer in Stelle 10 der
 * Kontonummer. Die einzelnen Stellen der Stammnummer sind von rechts nach links
 * mit den Ziffern 2, 3, 4, 5, 6, 7 zu multiplizieren. Die jeweiligen Produkte
 * werden addiert, nachdem jeweils aus den 2-stelligen Produkten Quersummen
 * gebildet wurden. Die Summe ist durch 11 zu dividieren.<br/>
 * 
 * Die weiteren Berechnungen und Ergebnisse entsprechen dem Verfahren 06.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * 7-stell. Kontonr.: 0 0 0 x x x x x x<br/>
 * P Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * 1- bis 6- und 10-stellige Kontonummern sind nicht zu prüfen, da diese keine
 * Prüfziffer enthalten. <br/>
 * 
 * Testkontonummern: 1098506, 32028008, 218433000<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum89 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int leadingNeutralDigits = ChecksumUtils
				.countNeutralLeadingDigits(accountNumber);
		if ((accountNumber[0] != 0) || (leadingNeutralDigits > 3)) {
			return false;
		} else {
			if (leadingNeutralDigits == 3) {
				setAlternative(1);
				return accountNumber[9] == calcChecksum(accountNumber);
			} else {
				setAlternative(0);
				return new Checksum10().validate(accountNumber);
			}

		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * WEIGHTS[i]);
		}
		return ((sum % 11 == 0) || (sum % 11 == 1)) ? 0 : (11 - sum % 11);
	}

}
