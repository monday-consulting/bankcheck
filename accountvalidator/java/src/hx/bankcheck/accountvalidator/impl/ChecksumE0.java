package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 <br/>
 * 
 * Die Stellen der Kontonummer sind von rechts nach links mit
 * den Ziffern 2, 1, 2, 1, 2 usw. zu multiplizieren. Die jeweiligen
 * Produkte werden addiert, nachdem jeweils aus den
 * zweistelligen Produkten die Quersumme gebildet wurde
 * (z. B. Produkt 18 = Quersumme 9) plus den Wert 7. Nach
 * der Addition bleiben außer der Einerstelle alle anderen
 * Stellen unberücksichtigt. Die Einerstelle wird von dem Wert
 * 10 subtrahiert. Das Ergebnis ist die Prüfziffer (10. Stelle der
 * Kontonummer). Ergibt sich nach der Subtraktion der
 * Rest 10, ist die Prüfziffer 0. <br/>
 * 
 * Testkontonummern (richtig): 1234568013, 1534568010, 2610015, 8741013011 <br/>
 * Testkontonummern (falsch): 1234769013, 2710014, 9741015011 <br/>
 * 
 * @author Dirk Schrödter (ds@monday-consulting.com) - Monday Consulting GmbH
 * @version 1.0
 * 
 */
public class ChecksumE0 extends AbstractChecksumValidator {

	// Weights from left to right
	private static final int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
	
	public ChecksumE0() {
		this(WEIGHTS);
	}
	
	public ChecksumE0(int[] weights) {
		setWeights(weights);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {		
		return accountNumber[9] == calcChecksum(accountNumber);
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < accountNumber.length - 1; i++) {
			sum += ChecksumUtils.qs(accountNumber[i] * getWeights()[i]);
		}				
		int cs = (10 - ((sum+7) % 10));
		return (cs == 10) ? 0 : cs;
	}
}
