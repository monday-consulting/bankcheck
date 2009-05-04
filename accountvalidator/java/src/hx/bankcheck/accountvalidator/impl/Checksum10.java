package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10 (modifiziert)
 * Die Berechnung erfolgt wie bei Verfahren 06.
 * Testkontonummern: 12345008, 87654008
 * @author tma
 *
 */
public class Checksum10 extends Checksum06 {

	private final static int[] WEIGHTS = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	public Checksum10() {
		super(WEIGHTS);
	}
}
