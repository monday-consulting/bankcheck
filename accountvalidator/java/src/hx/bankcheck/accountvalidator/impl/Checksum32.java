package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 4 bis 9 der Kontonummer werden
 * vonrechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die
 * Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle 10 der
 * Kontonummer ist per Definition die Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 7 6 5 4 3 2<br/>
 * 
 * Testkontonummern:<br/>
 * 
 * 9141405, 1709107983, 0122116979, 0121114867, 9030101192, 9245500460
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum32 extends Checksum06 {

	// weights from left to right
	private static final int[] WEIGHTS = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };

	public Checksum32() {
		super(WEIGHTS);
	}
	
	public Checksum32(int[] weights){
		super(weights);
	}

}
