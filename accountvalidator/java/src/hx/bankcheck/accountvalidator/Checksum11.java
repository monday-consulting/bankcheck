package hx.bankcheck.accountvalidator;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10 (modifiziert)
 * Die Berechnung erfolgt wie bei Verfahren 06. Beim Rechenergebnis 10
 * wird die Null jedoch durch eine 9 ersetzt.
 * 
 * @author tma
 */
public class Checksum11 extends Checksum06 {

	// Weights from left to right
	private final static int[] WEIGHTS = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	public Checksum11() {
		super(WEIGHTS);
	}

	@Override
	protected int adjustChecksum(int checksum) {
		if (checksum == 10)
			return 9;
		
		return super.adjustChecksum(checksum);
	}
}
