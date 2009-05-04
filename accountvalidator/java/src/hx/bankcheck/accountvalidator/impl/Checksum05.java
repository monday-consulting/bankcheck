package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 10, Gewichtung 7, 3, 1, 7, 3, 1, 7, 3, 1
 * Die Berechnung erfolgt wie bei Verfahren 01.
 * 
 * @author tma
 */
public class Checksum05 extends Checksum01 {

	// Weights from left to right
	private final static int[] WEIGHTS = { 1, 3, 7, 1, 3, 7, 1, 3, 7 };
	
	public Checksum05() {
		super(WEIGHTS);
	}
}
