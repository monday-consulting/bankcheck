package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschlie�lich der Pr�fziffer 10-stellig, ggf. ist die
 * Kontonummer f�r die Pr�fzifferberechnung durch linksb�ndige Auff�llung mit
 * Nullen 10-stellig darzustellen. Die 10. Stelle der Kontonummer ist die
 * Pr�fziffer.<br/>
 * 
 * <b>Variante 1:</b> <br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 3 (modifiziert)
 * 
 * Die Berechnung und m�gliche Ergebnisse entsprechen der Methode 20. F�hrt die
 * Berechnung nach Variante 1 zu einem Pr�fzifferfehler, so ist nach Variante 2
 * zu pr�fen. <br/>
 * 
 * Testkontonummern (richtig): 0734192657, 6932875274<br/>
 * Testkontonummern (falsch): 3145863029, 2938692523, 0132572975, 5432198760,
 * 9070873333, 5011654366 9000412340, 9310305011 <br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 10,iterierte Transformation <br/>
 * 
 * Die Berechnung und m�gliche Ergebnisse entsprechen der Methode 29. <br/>
 * 
 * Testkontonummern (richtig): 3145863029, 2938692523 <br/>
 * Testkontonummern (falsch): 0132572975, 5432198760, 9070873333, 9000412340,
 * 9310305011 <br/>
 * 
 * <b>Variante 3:</b><br/>
 * Für die folgenden Kontonummernkreise gilt die Methode 09 (keine
 * Prüfzifferberechnung).
 * <ul>
 * <li>10-stellige Kontonummer; 1. + 2. Stelle = 51 - 59 Kontonummernkreis
 * 5100000000 – 5999999999</li>
 * <li>10-stellige Kontonummer; Stellen 1 - 3 = 901 - 910 Kontonummernkreis
 * 9010000000 – 9109999999</li>
 * </ul>
 * 
 * 
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB8 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 3, 9, 8, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (new Checksum20(WEIGHTS).validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else if (new Checksum29().validate(accountNumber)) {
			setAlternative(1);
			return true;
		} else if ((accountNumber[0] == 5 && accountNumber[1] != 0)) {
			setAlternative(2);
			return true;
		} else {
			int i = accountNumber[0] * 100 + accountNumber[1] * 10
					+ accountNumber[2];
			if (i >= 901 && i <= 910) {
				setAlternative(2);
				return true;
			} else {
				return false;
			}
		}
	}

}
