package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1 <br />
 * 
 * Die Kontonummer ist 10-stellig.
 * 
 * Die Berechnung erfolgt wie bei Verfahren 01.<br />
 * 
 * Es ist jedoch zu beachten, dass nur die Stellen 4 bis 9 in das
 * Prüfzifferberechnungsverfahren einbezogen werden. <br />
 * 
 * Die Stelle 10 der Kontonummer ist die Prüfziffer. <br />
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br />
 * Kontonr.: x x x x x x x x x P<br />
 * Gewichtung: 1 7 3 1 7 3<br />
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum92 extends Checksum01 {

	// Weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 0, 1, 7, 3, 1, 7, 3 };

	public Checksum92() {
		super(WEIGHTS);
	}

}
