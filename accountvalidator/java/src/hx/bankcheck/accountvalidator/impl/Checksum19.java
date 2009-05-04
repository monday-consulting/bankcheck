package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 1
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.
 * Testkontonummern: 0240334000, 0200520016
 * 
 * @author tma
 */
public class Checksum19 extends Checksum06 {
	
	// Weights from left to right
	private final static int[] WEIGHTS = { 1, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	public Checksum19() {
		super(WEIGHTS);
	}
	
}
