package hx.bankcheck.accountvalidator;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2
 * Die Berechnung erfolgt wie bei Verfahren 01.
 * 
 * @author tma
 */
public class Checksum03 extends Checksum01 implements IAccountChecksum {
	private final static int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
	
	public Checksum03() {
		super(WEIGHTS);
	}
	
}
