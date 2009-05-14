/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

/**
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 9, 7 (A = 10) <br/>
 * 
 * Die Kontonummer ist 10-stellig. Es wird das Berechnungsverfahren 28 mit
 * modifizierter Gewichtung angewendet. Die Gewichtung lautet 2, 4, 8, 5, A, 9,
 * 7. Dabei steht der Buchstabe A für den Wert 10. <br/>
 * 
 * Testkontonummern: 9913000700, 9914001000<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum34 extends Checksum28 {

	private static final int[] WEIGHTS = { 7, 9, 10, 5, 8, 4, 2, 0, 0, 0 };

	public Checksum34() {
		super(WEIGHTS);
	}

}
