/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 9 (A = 10) <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 4 bis 9 der Kontonummer werden
 * von rechts nach links mit den Faktoren 2, 4, 8, 5, A, 9 multipliziert. Dabei
 * steht der Buchstabe A für den Wert 10. Die restliche Berechnung und
 * Ergebnisse entsprechen dem Verfahren 06. Die Stelle 10 der Kontonummer ist
 * per Definition die Prüfziffer. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 9 A 5 8 4 2 <br/>
 * 
 * Testkontonummern: 191919, 1100660<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum38 extends Checksum06 {

	private static final int[] WEIGHTS = { 0, 0, 0, 9, 10, 5, 8, 4, 2 };

	public Checksum38() {
		super(WEIGHTS);
	}

}
