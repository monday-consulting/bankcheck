/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2 <br/>
 * 
 * Die beiden ersten und die beiden letzten Stellen sind nicht zu
 * berücksichtigen. Die Stellen drei bis sieben sind von rechts nach links mit
 * den Ziffern 2, 1, 2, 1, 2 zu multiplizieren. Aus zweistelligen
 * Einzelergebnissen ist eine Quersumme zu bilden. Alle Ergebnisse sind dann zu
 * addieren. Die Differenz zum nächsten Zehner ergibt die Prüfziffer auf Stelle
 * acht. Ist die Differenz 10, ist die Prüfziffer 0.<br/>
 * 
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10) <br/>
 * Kontonr.: 5 0 2 9 0 7 6 P 0 1<br/>
 * Gewichtung: 2 1 2 1 2 4 + 9 + 0 + 7 + 3 = 23 (Q) (Q = Quersumme) <br/>
 * Die Einerstelle wird vom Wert 10 subtrahiert 10 - 3 = 7. <br/>
 * 
 * Die Prüfziffer ist in diesem Fall die 7 und die vollständige Kontonummer
 * lautet: 5 0 2 9 0 7 6 7 0 1<br/>
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum62 extends AbstractChecksumValidator {

	private static final int[] WEIGHTS = { 0, 0, 2, 1, 2, 1, 2, 0, 0, 0 };

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hx.bankcheck.accountvalidator.AbstractChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return accountNumber[7]==calcChecksum(accountNumber);
	}

	private int calcChecksum(int[] accountNumber){
		int sum=0;
		for (int i = 0; i < WEIGHTS.length; i++) {
			sum+=ChecksumUtils.qs(accountNumber[i]*WEIGHTS[i]);
		}
		return (sum%10==0)?0:(10-sum%10);
	}
}
