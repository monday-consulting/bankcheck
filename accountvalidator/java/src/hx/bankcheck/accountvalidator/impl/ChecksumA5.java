package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Die Kontonummer ist einschlie�lich der Pr�fziffer 10-stellig, ggf. ist die
 * Kontonummer f�r die Pr�fzifferberechnung durch linksb�ndige Auff�llung mit
 * Nullen 10-stellig darzustellen. <br/>
 * 
 * <b>Variante 1:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * 
 * Gewichtung und Berechnung erfolgen nach der Methode 00. F�hrt die Berechnung
 * nach Variante 1 zu einem Pr�fzifferfehler, so sind 10-stellige Konten mit
 * einer 9 an Stelle 1 falsch, alle anderen Konten sind nach Variante 2 zu
 * pr�fen. <br/>
 * 
 * Testkontonummern (richtig): 9941510001, 9961230019 9380027210, 9932290910 <br/>
 * Testkontonummern (falsch): 9941510002, 9961230020 <br/>
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10<br/>
 * Gewichtung und Berechnung erfolgen nach der Methode 10.<br/>
 * 
 * Testkontonummern (richtig): 0000251437, 0007948344 0000159590, 0000051640<br/>
 * Testkontonummern (falsch): 0000251438, 0007948345<br/>
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumA5 extends AbstractChecksumValidator {

	private int alternative = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (new Checksum00().validate(accountNumber)) {
			return true;
		} else {
			setAlternative(1);
			if (accountNumber[0] == 9) {
				return false;
			} else {
				return new Checksum10().validate(accountNumber);
			}
		}
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}

}
