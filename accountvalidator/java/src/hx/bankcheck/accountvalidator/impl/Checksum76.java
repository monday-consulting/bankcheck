/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.AccountNumberNotTestableException;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5 ff. <br/>
 * 
 * Die einzelnen Stellen der für die Berechnung der Prüfziffer relevanten 5-, 6-
 * oder 7-stelligen Stammnummer sind von rechts nach links mit den Ziffern 2, 3,
 * 4, 5 ff. zu multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe
 * ist durch 11 zu dividieren. Der verbleibende Rest ist die Prüfziffer. Ist der
 * Rest 10, kann die Kontonummer nicht geprüft werden.<br/>
 * 
 * Zusammensetzung der Kontonummer: <br/>
 * 
 * S = Stammnummer (5-, 6- oder 7-stellig; die letzte Stelle dieser Nummer ist
 * die Prüfziffer, sie wird jedoch nicht in die Prüfzifferberechnung einbezogen) <br/>
 * A = Kontoart (1-stellig)*<br/>
 * P = Prüfziffer Prüfzifferberech<br/>
 * U = Unterkontonummer (2-stellig) <br/>
 * 
 * (Werden nicht in die Prüfziffernberechnung einbezogen)<br/>
 * 
 * Die Kontoart kann den Wert 0, 4, 6, 7, 8 oder 9 haben.<br/>
 * 
 * Darstellung der Kontonummer:<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * 5stell. Stammnr.: A 0 0 S S S S P U U <br/>
 * 6stell. Stammnr.: A 0 S S S S S P U U <br/>
 * 7stell. Stammnr.: A S S S S S S P U U <br/>
 * 
 * Beispiel: <br/>
 * 
 * Prüfzifferberechnung für eine 6-stellige Kontonummer, Kontoart ist "0".<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: 0 0 1 2 3 4 5 6 0 0<br/>
 * Gewichtung: 6 5 4 3 2 6 + 10 + 12 + 12 + 10 = 50 50 : 11 = 4 Rest 6 =
 * Prüfziffer<br/>
 * 
 * Ausnahme: <br/>
 * 
 * Ist die Unterkontonummer "00" kann es vorkommen, dass sie auf den
 * Zahlungsverkehrsbelegen oder in beleglosen Datensätzen nicht angegeben ist,
 * die Kontonummer jedoch um führende Nullen ergänzt wurde. <br/>
 * 
 * Die Prüfziffer ist dann an die 10. Stelle gerückt. Die Berechnung der
 * Prüfziffer ist wie folgt durchzuführen: <br/>
 * 
 * Beispiel (Kontoart = 0):<br/>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)<br/>
 * Kontonr.: 0 0 0 0 1 2 3 4 5 6 <br/>
 * Gewichtung: 6 5 4 3 2 6 + 10 + 12 + 12 + 10 = 50 50 : 11 = 4 Rest 6 =
 * Prüfziffer <br/>
 * 
 * Testkontonummern:<br/>
 * 
 * 5-stellige Kontonr. (Kontoart = 0) 0006543200 <br/>
 * 6-stellige Kontonr. (Kontoart = 9) 9012345600 <br/>
 * 7-stellige Kontonr. (Kontoart = 7) 7876543100 <br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum76 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_5_DIGITS = { 0, 0, 0, 5, 4, 3, 2, 0, 0,
			0 };
	private static final int[] WEIGHTS_6_DIGITS = { 0, 0, 6, 5, 4, 3, 2, 0, 0,
			0 };
	private static final int[] WEIGHTS_7_DIGITS = { 0, 7, 6, 5, 4, 3, 2, 0, 0,
			0 };
	private static final int[] WEIGHTS_EXCEPTION = { 0, 0, 0, 7, 6, 5, 4, 3, 2 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if ((accountNumber[0] != 0) && (accountNumber[0] != 4)
				&& (accountNumber[0] != 6) && (accountNumber[0] != 7)
				&& (accountNumber[0] != 8) && (accountNumber[0] != 9)) {
			return false;
		} else {
			if ((accountNumber[1] == 0) && (accountNumber[2] == 0)) {
				setWeights(WEIGHTS_5_DIGITS);
			} else {
				if ((accountNumber[1] == 0) && (accountNumber[2] != 0)) {
					setWeights(WEIGHTS_6_DIGITS);
				} else {
					setWeights(WEIGHTS_7_DIGITS);
				}
			}
			int checksum = calcChecksum(accountNumber);
			if (checksum == 10) {
				throw new AccountNumberNotTestableException();
			} else {
				if(accountNumber[7] == calcChecksum(accountNumber)){
					return true;
				}else{
					if (ChecksumUtils.countNeutralLeadingDigits(accountNumber) >= 2) {
						setException(true);
						setWeights(WEIGHTS_EXCEPTION);
						checksum=calcChecksum(accountNumber);
						if (checksum == 10) {
							throw new AccountNumberNotTestableException();
						} else {
							return accountNumber[9] == checksum;
						}
					}else {
						return false;
					}
				}
			}
		}
	}

	private int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < getWeights().length; i++) {
			sum += accountNumber[i] * getWeights()[i];
		}
		return (sum % 11);
	}
}
