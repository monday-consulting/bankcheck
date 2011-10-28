/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;


/**
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 0, 0, 0, 0 (A = 10) <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 33. <br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10 <br/>
 * Kontonr.: x x x x x x x x x P <br/>
 * Gewichtung: 0 0 0 0 A 5 8 4 2 (A = 10) <br/>
 * 
 * Testkontonummern: 889006, 2618040504 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum44 extends Checksum33 {
	
	private static final int[] WEIGHTS={0,0,0,0,10,5,8,4,2};
	
	public Checksum44() {
		super(WEIGHTS);
	}

}
