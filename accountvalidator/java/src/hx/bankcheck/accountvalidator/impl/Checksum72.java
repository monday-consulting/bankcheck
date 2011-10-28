/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;


/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Die Kontonummer ist 10-stellig. Die Stellen 4 bis 9 der Kundennummer (K)
 * werden von rechts nach links mit den Gewichten 2, 1, 2, 1, 2, 1
 * multipliziert. Die Berechnung und Ergebnisse entsprechen dem Verfahren 00.
 * Die 10. Stelle der Kontonummer ist die Prüfziffer. Es ist jedoch zu beachten,
 * dass die zweistellige Unterkontonummer (U = Stellen 1 und 2) und die
 * Artziffer (A = Stelle 3) nicht in das Prüfzifferverfahren mit einbezogen
 * werden.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: U U A K K K K K K P <br/>
 * Gewichtung: 1 2 1 2 1 2 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum72 extends Checksum00 {

	private static final int[] WEIGHTS = { 0, 0, 0, 1, 2, 1, 2, 1, 2 };

	public Checksum72() {
		super(WEIGHTS);
	}

}
