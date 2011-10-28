package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4 <br/>
 * 
 * Die Berechnung erfolgt wie bei Verfahren 06. <br/>
 * 
 * <b>Ausnahmen: </b><br/>
 * 
 * Kontonr.: 0000000001 bis 0001999999 <br/>
 * Kontonr.: 0009000000 bis 0025999999 <br/>
 * Kontonr.: 0396000000 bis 0499999999 <br/>
 * Kontonr.: 0700000000 bis 0799999999 <br/>
 * 
 * Für diese Kontonummernkreise ist keine Prüfziffer-berechnung möglich. Sie
 * sind als richtig anzusehen.<br/>
 * 
 * Testkontonummern: 0068007003, 0847321750, 6450060494, 6454000003
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum95 extends Checksum06 {

	// Weights from left to right
	private static int[] WEIGHTS = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };

	public Checksum95() {
		super(WEIGHTS);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		long tmpAccoutNumber = ChecksumUtils.parseLong(accountNumber);
		if ((tmpAccoutNumber >= Long.parseLong("0000000001") && tmpAccoutNumber <= Long
				.parseLong("0001999999"))
				|| (tmpAccoutNumber >= Long.parseLong("0009000000") && tmpAccoutNumber <= Long
						.parseLong("0025999999"))
				|| (tmpAccoutNumber >= Long.parseLong("0396000000") && tmpAccoutNumber <= Long
						.parseLong("0499999999"))
				|| (tmpAccoutNumber >= Long.parseLong("0700000000") && tmpAccoutNumber <= Long
						.parseLong("0799999999"))) {
			setException(true);
			return true;
		} else {
			return super.validate(accountNumber);
		}
	}

}
