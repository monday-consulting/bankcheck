package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10
 * Die Berechnung erfolgt wie bei Verfahren 02.
 * 
 * @author tma
 */
public class Checksum07 extends Checksum02 {
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 10, 9, 8, 7, 6, 5, 4, 3, 2 }; 
	
	public Checksum07() {
		super(WEIGHTS);
	}
	
}
