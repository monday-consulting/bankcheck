/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9 Die Kontonummer ist 10-stellig.
 * Die Stellen 2 bis 9 der Kontonummer werden von rechts nach links mit den
 * Ziffern 2, 3, 4, 5, 6, 7, 8, 9 multipliziert. Die restliche Berechnung und
 * Ergebnisse entsprechen dem Verfahren 06. Die Stelle 10 der Kontonummer ist
 * per Definition die Prüfziffer. Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.: x x x x x x x x x P Gewichtung: 9 8 7 6 5 4 3 2 Testkontonummern:
 * 59498, 59510
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum42 extends Checksum06 {

	private static final int[] WEIGHTS = { 0, 9, 8, 7, 6, 5, 4, 3, 2 };

	public Checksum42() {
		super(WEIGHTS);
	}

}
