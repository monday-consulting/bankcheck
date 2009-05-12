package hx.bankcheck.accountvalidator.impl;


/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6<br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 5 bis 9 der Kontonummer werden
 * von rechts nach links mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle
 * 10 der Kontonummer ist per Definition die Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 6 5 4 3 2<br/>
 * 
 * Testkontonummern: 48658, 84956<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum33 extends Checksum06 {

	// Weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 0, 0, 6, 5, 4, 3, 2 };

	public Checksum33() {
		super(WEIGHTS);
	}

	public Checksum33(int[] weights) {
		super(weights);
	}

}
