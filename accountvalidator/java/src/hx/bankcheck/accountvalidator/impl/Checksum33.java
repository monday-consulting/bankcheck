package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 5 bis 9 der Kontonummer werden
 * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle
 * 10 der Kontonummer ist per Definition die Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 6 5 4 3 2<br/>
 * 
 * Testkontonummern: 48658, 84956<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum33 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 0, 0, 0, 6, 5, 4, 3, 2 };
	private int[] weights;

	public Checksum33() {
		this.weights = WEIGHTS;
	}

	public Checksum33(int[] weights) {
		this.weights = weights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return new Checksum06(weights).validate(accountNumber);
	}

}
