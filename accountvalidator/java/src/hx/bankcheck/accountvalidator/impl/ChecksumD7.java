package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import java.util.logging.Logger;

/**
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2<br/>
 * <p>
 * Die Kontonummer ist einschließlich der Prüfziffer 10-stellig, ggf. ist die
 * Kontonummer für die Prüfzifferberechnung durch linksbündige Auffüllung mit
 * Nullen 10-stellig darzustellen.
 * </p><p>
 * Die Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 1,
 * 2, 1, 2, 1, 2, 1, 2 zu multiplizieren. Die jeweiligen Produkte werden
 * addiert, nachdem jeweils aus den zweistelligen Produkten die Quersumme
 * gebildet wurde (z. B. Produkt 18 = Quersumme 9). Nach der Addition bleiben
 * außer der Einerstelle alle anderen Stellen unberücksichtigt; diese
 * Einerstelle ist die Prüfziffer (Ergebnis = 27 / Prüfziffer = 7).
 * </p>
 * <p>
 * Testkontonummern richtig: 0500018205, 0230103715, 0301000434, 0330035104,
 * 0420001202, 0134637709, 0201005939, 0602006999
 * </p><p>
 * Testkontonummern falsch: 0501006102, 0231307867, 0301005331, 0330034104,
 * 0420001302, 0135638809, 0202005939, 0601006977
 * </p>
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 */
public class ChecksumD7 extends AbstractChecksumValidator {
	private final static Logger LOG = Logger.getLogger(ChecksumD7.class
			.getName());

	protected final static int[] WEIGHTS = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };

	public ChecksumD7() {
		this(WEIGHTS);
	}

	public ChecksumD7(int[] weights) {
		setWeights(weights);
	}

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return calcChecksum(accountNumber) == accountNumber[9];
	}

	protected int calcChecksum(int[] accountNumber) {
		int sum = 0;
		for (int i = getWeights().length - 1; i >= 0; i--) {
			sum += ChecksumUtils.qs(accountNumber[i] * getWeights()[i]);
		}
		int checksum = (sum % 10);

		LOG.finer("Calculated Checksum is: " + checksum);

		return checksum;
	}

}
