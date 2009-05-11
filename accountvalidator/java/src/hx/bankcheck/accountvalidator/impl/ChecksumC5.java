/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Die Kontonummern sind einschließlich der Prüfziffer 6- oder 8- bis
 * 10-stellig, ggf. ist die Kontonummer für die Prüfziffer-berechnung durch
 * linksbündige Auffüllung mit Nullen 10-stellig darzustellen. Die Berechnung
 * der Prüfziffer und die möglichen Ergebnisse richten sich nach dem jeweils bei
 * der entsprechenden Variante angegebenen Kontonummernkreis. <br/>
 * 
 * Entspricht eine Kontonummer keinem der vorgegebenen Kontonummern-kreise oder
 * führt die Berechnung der Prüfziffer nach der vorgegebenen Variante zu einem
 * Prüfzifferfehler, so ist die Kontonummer ungültig. <br/>
 * 
 * S = Ziffer der Kontonummer, die in die Prüfzifferberechnung einbezogen wird <br/>
 * X = Weitere Ziffern der Kontonummer, die jedoch nicht in die
 * Prüfzifferberechnung mit einbezogen werden <br/>
 * P = Prüfziffer <br/>
 * 
 * <b>Variante 1: </b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 75.<br/>
 * 
 * 6-stellige Kontonummern; 5. Stelle = 1-8 Kontonummernkreis 0000100000 bis
 * 0000899999<br/>
 * Testkontonummern (richtig): 0000301168, 0000302554 <br/>
 * Testkontonummern (falsch): 0000302589, 0000507336<br/>
 * 
 * 9-stellige Kontonummern; 2. Stelle = 1-8 Kontonummernkreis 0100000000 bis
 * 0899999999<br/>
 * * Testkontonummern (richtig): 0300020050, 0300566000<br/>
 * Testkontonummern (falsch): 0302555000, 0302589000<br/>
 * 
 * 
 * <b>Variante 2:</b><br/>
 * 
 * Modulus 10, iterierte Transformation Die Berechnung und mögliche Ergebnisse
 * entsprechen der Methode 29.<br/>
 * 
 * 10-stellige Kontonummern, 1. Stelle = 1, 4, 5, 6 oder 9 Kontonummernkreis
 * 1000000000 bis 1999999999 Kontonummernkreis 4000000000 bis 6999999999
 * Kontonummernkreis 9000000000 bis 9999999999<br/>
 * 
 * Testkontonummern (richtig): 1000061378, 1000061412, 4450164064, 4863476104,
 * 5000000028, 5000000391, 6450008149, 6800001016, 9000100012, 9000210017<br/>
 * Testkontonummern (falsch): 1000061457, 1000061498 4864446015, 4865038012,
 * 5000001028, 5000001075, 6450008150, 6542812818, 9000110012, 9000300310<br/>
 * 
 * <b>Variante 3:</b><br/>
 * 
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 </br>
 * 
 * Die Berechnung und mögliche Ergebnisse entsprechen der Methode 00.<br/>
 * 
 * 10-stellige Kontonummern, 1. Stelle = 3 Kontonummernkreis 3000000000 bis
 * 3999999999<br/>
 * 
 * Testkontonummern (richtig): 3060188103, 3070402023 <br/>
 * Testkontonummern (falsch): 3081000783, 3081308871 <br/>
 * 
 * <b>Variante 4: </b><br/>
 * 
 * Für die folgenden Kontonummernkreise gilt die Methode 09 (keine
 * Prüfzifferberechnung).<br>
 * 
 * 8-stellige Kontonummern; 3. Stelle = 3, 4 oder 5 Kontonummernkreis 0030000000
 * bis 0059999999 <br/>
 * 
 * 10-stellige Kontonummern; 1.+ 2. Stelle = 70 oder 85 Kontonummernkreis
 * 7000000000 bis 7099999999 Kontonummernkreis 8500000000 bis 8599999999
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC5 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS_ALTERNATIVE_1A = { 0, 0, 0, 0, 2, 1, 2,
			1, 2 };
	private static final int[] WEIGHTS_ALTERNATIVE_1B = { 0, 2, 1, 2, 1, 2, 0,
			0, 0 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int leadingNeutralDigits = ChecksumUtils
				.countNeutralLeadingDigits(accountNumber);
		long accountNumberAsLong = ChecksumUtils.parseLong(accountNumber);
		if ((leadingNeutralDigits != 4) && (leadingNeutralDigits != 2)
				&& (leadingNeutralDigits != 1) && (leadingNeutralDigits != 0)) {
			return false;
		} else {
			if (((accountNumberAsLong >= 100000l) && (accountNumberAsLong <= 899999l))
					|| ((accountNumberAsLong >= 100000000l) && (accountNumberAsLong <= 899999999l))) {
				setAlternative(0);
				if ((accountNumberAsLong >= 100000l)
						&& (accountNumberAsLong <= 899999l)) {
					return new Checksum75().validate(accountNumber, 9,
							WEIGHTS_ALTERNATIVE_1A);
				} else {
					return new Checksum75().validate(accountNumber, 6,
							WEIGHTS_ALTERNATIVE_1B);
				}
			} else {
				if (((accountNumberAsLong >= 1000000000l) && (accountNumberAsLong <= 1999999999l))
						|| ((accountNumberAsLong >= 4000000000l) && (accountNumberAsLong <= 6999999999l))
						|| ((accountNumberAsLong >= 9000000000l) && (accountNumberAsLong <= 9999999999l))) {
					setAlternative(1);
					return new Checksum29().validate(accountNumber);
				} else {
					if ((accountNumberAsLong >= 3000000000l)
							&& (accountNumberAsLong <= 3999999999l)) {
						setAlternative(2);
						return new Checksum00().validate(accountNumber);
					} else {
						if (((accountNumberAsLong >= 30000000l) && (accountNumberAsLong <= 59999999l))
								|| ((accountNumberAsLong >= 7000000000l) && (accountNumberAsLong <= 7099999999l))
								|| ((accountNumberAsLong >= 8500000000l) && (accountNumberAsLong <= 8599999999l))) {
							setAlternative(3);
							return new Checksum09().validate(accountNumber);
						} else {
							return false;
						}
					}
				}
			}
		}
	}

}
