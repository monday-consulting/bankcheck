package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.util.logging.Logger;

/**
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 2 Die Stellen der Kontonummer
 * sind von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 8, 9, 2 zu
 * multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe ist durch
 * 11 zu dividieren. Der verbleibende Rest wirdvom Divisor (11) subtrahiert. Das
 * Ergebnis ist die Prüfziffer. Verbleibt nach der Division durch 11 kein Rest,
 * ist die Prüfziffer 0. Ergibt sich als Rest 1, ist die Prüfziffer
 * zweistellig und kann nicht verwendet werden. Die Kontonummer ist dann nicht
 * verwendbar.
 * 
 * @author tma
 */
public class Checksum02 implements ChecksumValidator {
	private final static Logger LOG = Logger.getLogger(Checksum02.class
			.getName());

	// Weights from left to right
	private final static int[] WEIGHTS = { 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private int[] weights;

	public Checksum02() {
		this(WEIGHTS);
	}

	public Checksum02(int[] weights) {
		this.weights = weights;
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		int checksum = calcChecksum(accountNumber);

		return checksum == accountNumber[9];
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = 0; i < weights.length; i++) {
			sum += accountNumber[i] * weights[i];
		}
		int checksum = 11 - (sum % 11);

		if (checksum == 11)
			checksum = 0;

		LOG.finer("Calculated Checksum is: " + checksum);

		return checksum;
	}

}
