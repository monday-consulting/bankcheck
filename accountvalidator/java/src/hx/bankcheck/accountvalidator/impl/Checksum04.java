package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4
 * Die Berechnung erfolgt wie bei Verfahren 02.
 * 
 * @author tma
 */
public class Checksum04 extends Checksum02 {
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 }; 
	
	public Checksum04() {
		super(WEIGHTS);
	}
	
	public Checksum04(int[] weights){
		super(weights);
	}
	
}
