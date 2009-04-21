package hx.bankcheck.accountvalidator;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 3 (modifiziert)
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.
 * 
 * @author tma
 *
 */
public class Checksum20 extends Checksum06 {

	// Weights from left to right
	private final static int[] WEIGHTS = { 3, 9, 8, 7, 6, 5, 4, 3, 2 };

	public Checksum20() {
		super(WEIGHTS);
	}
}
