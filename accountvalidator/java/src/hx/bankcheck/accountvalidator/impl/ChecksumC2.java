package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen. Die 10. Stelle der Kontonummer ist die
 * Prüfziffer.<br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 10, Gewichtung 3, 1, 3, 1, 3, 1, 3, 1, 3<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 22. Führt die
 * Berechnung nach Variante 1 zu einem Prüfzifferfehler, so ist nach Variante 2
 * zu prüfen.<br/>
 * 
 * Testkontonummern (richtig): 2394871426, 4218461950, 7352569148<br/>
 * Testkontonummern (falsch): 5127485166, 8738142564, 0328705282, 9024675131<br/>
 * 
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * 
 * Testkontonummern (richtig): 5127485166, 8738142564 <br/>
 * Testkontonummern (falsch): 0328705282, 9024675131 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC2 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERNATIVE1 = { 3, 1, 3, 1, 3, 1, 3, 1,
			3 };
	private static final int[] WEIGHTS_ALTERNATIVE2 = { 2, 1, 2, 1, 2, 1, 2, 1,
			2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if (new Checksum01(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
			setAlternative(0);
			return true;
		} else {
			setAlternative(1);
			return new Checksum00(WEIGHTS_ALTERNATIVE2).validate(accountNumber);
		}
	}

}
