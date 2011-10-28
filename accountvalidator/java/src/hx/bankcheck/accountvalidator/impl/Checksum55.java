/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum55 extends Checksum06 {

	private static final int[] WEIGHTS = { 8, 7, 8, 7, 6, 5, 4, 3, 2 };

	public Checksum55() {
		super(WEIGHTS);
	}

}
