package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 10, Gewichtung 1, 2, 1, 2, 1, 2, 1, 2, 1 <br/>
 * 
 * Die Stellen 1 bis 9 der Kontonummer sind von rechts nach links mit den
 * Gewichten zu multiplizieren.<br/>
 * 
 * Die weitere Berechnung erfolgt wie bei Verfahren 00.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 1 2 1 2 1 2 1 2 1 <br/>
 * 
 * Testkontonummer: 6782533003
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum94 extends Checksum00 {

	private static final int[] WEIGHTS = { 1, 2, 1, 2, 1, 2, 1, 2, 1 };

	public Checksum94() {
		super(WEIGHTS);
	}

}
