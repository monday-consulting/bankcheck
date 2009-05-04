package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * Die Berechnung erfolgt wie bei Verfahren 01.
 * 
 * @author tma
 */
public class Checksum03 extends Checksum01 implements ChecksumValidator {
	// Weights from left to right
	private final static int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
	
	public Checksum03() {
		super(WEIGHTS);
	}
	
}
