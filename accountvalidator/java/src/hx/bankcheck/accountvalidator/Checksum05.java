package hx.bankcheck.accountvalidator;

/**
 * Modulus 10, Gewichtung 7, 3, 1, 7, 3, 1, 7, 3, 1
 * Die Berechnung erfolgt wie bei Verfahren 01.
 * 
 * @author tma
 */
public class Checksum05 extends Checksum01 {

	private final static int[] WEIGHTS = { 7, 3, 1, 7, 3, 1, 7, 3, 1 };
	
	public Checksum05() {
		super(WEIGHTS);
	}
}
