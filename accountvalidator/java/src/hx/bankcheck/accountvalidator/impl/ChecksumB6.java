package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * <b>Variante 1: </b><br/>
 * 
 * Modulus 11, Gewichtung 2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,3<br/>
 * 
 * Kontonummern, die an der 1. Stelle der 10-stelligen Kontonummer den Wert 1-9 
 * oder an den Stellen 1–5 die Werte 02691-02699 beinhalten, sind 
 * nach der Methode 20 zu prüfen. Alle anderen Kontonummern sind nach der Variante
 * 2 zu prüfen.
 * 
 * Testkontonummer (richtig): 9110000000, 0269876545
 * Testkontonummer (falsch): 9111000000, 0269456780
 *
 * <b>Variante 2: </b><br/>
 * 
 * Modulus 11, Gewichtung 2, 4,8, 5, 10, 9, 7, 3, 6, 1, 2, 4 <br/>
 * 
 * Die Berechnung erfolgt nach der Methode 53.<br/>
 * 
 * Testkontonummer (richtig) mit BLZ 80053782: 487310018 <br/>
 * Testkontonummer (falsch) mit BLZ 80053762: 467310018 <br/>
 * Testkontonummer (falsch) mit BLZ 80053772: 477310018<br/>
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB6 implements ChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS_ALTERANTIVE1 = { 3, 9, 8, 7, 6, 5, 4, 3,
			2 };
	private static final int[] WEIGHTS_ALTERANTIVE2 = { 4, 2, 1, 6, 3, 7, 9,
			10, 5, 8, 4, 2 };
	private int alternative = 0;

	@Override
	public boolean validate(int[] a, int[] bankNumber)
			throws ValidationException {
		// erste Stelle 1-9 oder 1-5 Stelle Wert 02691 bis 02699
		if (a[0] != 0 || (a[1] == 2 && a[2] == 6 && a[3] == 9 && a[4] != 0)) {
			setAlternative(0);
			return new Checksum20(WEIGHTS_ALTERANTIVE1).validate(a);
		} else {
			setAlternative(1);
			return new Checksum53(WEIGHTS_ALTERANTIVE2).validate(a,
					bankNumber);
		}
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	@Override
	public int getAlternative() {
		return alternative;
	}

	@Override
	public boolean isException() {
		return false;
	}

}
