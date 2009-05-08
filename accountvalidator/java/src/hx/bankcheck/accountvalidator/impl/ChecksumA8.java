package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10-stellig
 * darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. <br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * 
 * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den
 * Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die weitere Berechnung und die
 * möglichen Ergebnisse entsprechen dem Verfahren 06. Führt die Berechnung nach
 * Variante 1 zu einem Prüfzifferfehler, so sind die Konten nach Variante 2 zu
 * prüfen. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)<br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 7 6 5 4 3 2 <br/>
 * 
 * Testkontonummern (richtig): 7436661, 7436670, 1359100<br/>
 * Testkontonummern (falsch): 7436660,7436678 <br/>
 * 
 * <b>Variante 2:</b> <br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den
 * Ziffern 2, 1, 2, 1, 2, 1 multipliziert. Die weiter Berechnung und die
 * möglichen Ergebnisse entsprechen dem Verfahren 00. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A=10)<br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 1 2 1 2 1 2 <br/>
 * 
 * Testkontonummern(richtig): 7436660, 7436678, 0003503398, 0001340967 <br/>
 * Testkontonummern(falsch): 7436666, 7436677, 0003503391, 0001340966 <br/>
 * 
 * <b>Ausnahme: </b><br/>
 * 
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der
 * Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in
 * Methode 51 mit den gleichen Ergebnissen und Testkontonummern.<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA8 extends AbstractChecksumValidator {

	// Weights from left to right
	private static int[] WEIGHTS_ALTERNATIVE1 = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };
	private static int[] WEIGHTS_ALTERNATIVE2 = { 0, 0, 0, 1, 2, 1, 2, 1, 2 };
	private int alternative = 0;
	private boolean exception = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (accountNumber[2] == 9) {
			setException(true);
			Checksum51 checksum = new Checksum51();
			boolean result = checksum.validate(accountNumber);
			setAlternative(checksum.getAlternative());
			return result;
		} else {
			if (new Checksum06(WEIGHTS_ALTERNATIVE1).validate(accountNumber)) {
				return true;
			} else {
				setAlternative(1);
				return new Checksum00(WEIGHTS_ALTERNATIVE2)
						.validate(accountNumber);
			}
		}
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}

	/**
	 * @return the exception
	 */
	public boolean isException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	protected void setException(boolean exception) {
		this.exception = exception;
	}

}
