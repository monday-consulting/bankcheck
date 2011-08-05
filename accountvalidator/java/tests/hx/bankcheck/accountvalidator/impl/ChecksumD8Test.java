package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 *         $Id$
 */
public class ChecksumD8Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws Throwable {
		ChecksumValidator cs = new ChecksumD8();

		long[] validNumbers = { 1403414848, 6800000439L, 6899999954L, 30000000L };

		long[] invalidNumbers = { 3012084101L, 1062813622, 260986 };

		for (long n : validNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = cs.validate(an, null);
			assertTrue(n + " is invalid!", valid);
		}

		for (long n : invalidNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = cs.validate(an, null);
			assertFalse(n + " is valid!", valid);
		}
	}

}