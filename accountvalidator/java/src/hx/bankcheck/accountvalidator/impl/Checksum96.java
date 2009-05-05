package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * <b>Variante 1</b><br/>
 * 
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 1<br/>
 * 
 * Die Prüfziffernrechnung ist nach Kennzeichen 19 durchzuführen. <br/>
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, so ist die
 * Berechnung nach Variante 2 vorzunehmen.<br/>
 * 
 * Gültige Kontonummern (Darstellung 10-stellig, einschl. Prüfziffer):<br/>
 * 
 * 0000254100, 9421000009<br/>
 * 
 * <b>Variante 2</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Prüfziffernrechnung ist nach Kennzeichen 00 durchzuführen.<br/>
 * 
 * Gültige Kontonummern (Darstellung 10-stellig, einschl. Prüfziffer): <br/>
 * 
 * 0000000208, 0101115152, 0301204301
 * 
 * <b>Variante 3 </b><br/>
 * 
 * Führen die Berechnungen nach Variante 1 und 2 zu Prüfzifferfehlern, so ist zu
 * prüfen, ob die Kontonummer zwischen 0001300000 und 0099399999 liegt. <br/>
 * Trifft dies zu, so gilt die Prüfziffer als richtig; trifft dies nicht zu, so
 * ist die Prüfziffer falsch.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum96 implements ChecksumValidator {

	private final static int[] WEIGHTS_ALTERNATIVE_1 = { 1, 9, 8, 7, 6, 5, 4,
			3, 2 };
	private final static int[] WEIGHTS_ALTERNATIVE_2 = { 2, 1, 2, 1, 2, 1, 2,
			1, 2 };

	private int alternative = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AccountChecksum#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		setAlternative(0);
		if (accountNumber[9] == calcChecksum(accountNumber)) {
			return true;
		} else {
			setAlternative(1);
			if (accountNumber[9] == calcChecksum(accountNumber)) {
				return true;
			} else {
				setAlternative(2);
				long tmpAccountNumber = ChecksumUtils.parseLong(accountNumber);
				return (tmpAccountNumber >= Long.parseLong("0001300000"))
						&& (tmpAccountNumber <= Long.parseLong("0099399999"));
			}
		}
	}

	protected int calcChecksum(int[] accountNumber) {
		switch (getAlternative()) {
		case 0:
			return calcChecksumAlternative1(accountNumber);
		case 1:
			return calcChecksumAlternative2(accountNumber);
		default:
			return -1;
		}
	}

	private int calcChecksumAlternative1(int[] accountNumber) {
		Checksum06 checksum06 = new Checksum06(WEIGHTS_ALTERNATIVE_1);
		return checksum06.calcChecksum(accountNumber);
	}

	private int calcChecksumAlternative2(int[] accountNumber) {
		Checksum00 checksum00 = new Checksum00(WEIGHTS_ALTERNATIVE_2);
		return checksum00.calcChecksum(accountNumber);
	}

	/**
	 * @return the methodFlag
	 */
	public int getAlternative() {
		return alternative;
	}

	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

}
