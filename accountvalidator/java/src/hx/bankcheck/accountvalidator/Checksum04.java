package hx.bankcheck.accountvalidator;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4
 * Die Berechnung erfolgt wie bei Verfahren 02.
 * 
 * @author tma
 */
public class Checksum04 extends Checksum02 {
	
	private final static int[] WEIGHTS = { 2, 3, 4, 5, 6, 7, 2, 3, 4 }; 
	
	public Checksum04() {
		super(WEIGHTS);
	}
	
}
