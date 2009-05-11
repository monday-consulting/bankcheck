package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. Die 10. Stelle der Kontonummer ist die
 * Prüfziffer. Variante 1: Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1, 3, 7, 1
 * Kontonummern der Kontenkreise 0001000000 bis 0005999999 sowie 0700000000 bis
 * 0899999999 sind nach der Methode (Kennzeichen) 01 zu prüfen. Führt die
 * Berechnung nach der Variante 1 zu einem Prüfzifferfehler, so ist die
 * Kontonummer falsch. Testkontonummern (richtig): 0700001529, 0730000019,
 * 0001001008, 0001057887, 0001007222, 0810011825, 0800107653, 0005922372
 * Testkontonummern (falsch): 0001057886, 0003815570, 0005620516, 0740912243,
 * 0893524479 Variante 2: Für alle anderen Kontonummern gilt die Methode 09
 * (keine Prüfzifferberechnung).
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB7 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 1, 7, 3, 1, 7, 3, 1, 7, 3 };
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (((ChecksumUtils.parseLong(accountNumber) >= 1000000) && (ChecksumUtils
				.parseLong(accountNumber) <= 5999999))
				|| ((ChecksumUtils.parseLong(accountNumber) >= 700000000) && (ChecksumUtils
						.parseLong(accountNumber) <= 899999999))) {
			setAlternative(0);
			return new Checksum01(WEIGHTS).validate(accountNumber);
		}else{
			setAlternative(1);
			return new Checksum09().validate(accountNumber);
		}
	}

}
