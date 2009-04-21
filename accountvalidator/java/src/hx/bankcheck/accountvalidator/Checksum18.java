package hx.bankcheck.accountvalidator;

/**
 * Modulus 10, Gewichtung 3, 9, 7, 1, 3, 9, 7, 1, 3
 * Die Berechnung erfolgt wie bei Verfahren 01.
 * 
 * @author tma
 */
public class Checksum18 extends Checksum01 {

	// Weights from left to right
	private final static int[] WEIGHTS = { 3, 1, 7, 9, 3, 1, 7, 9, 3 };
	
	public Checksum18() {
		super(WEIGHTS);
	}
	
	
}
