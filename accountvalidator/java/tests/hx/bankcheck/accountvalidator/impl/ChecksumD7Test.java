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
public class ChecksumD7Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws Throwable {
		ChecksumValidator cs = new ChecksumD7();

		long[] validNumbers = { 500018205, 230103715, 301000434, 330035104,
				420001202, 134637709, 201005939, 602006999
		};

		long[] invalidNumbers = { 501006102, 231307867, 301005331,
				330034104, 420001302, 135638809, 202005939, 601006977
		};
		
		for(long n : validNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = cs.validate(an, null);
			assertTrue(n+" is invalid!", valid);
		}

		for(long n : invalidNumbers) {
			int[] an = ChecksumUtils.parseAccountNumber(n);
			boolean valid = cs.validate(an, null);
			assertFalse(n+" is valid!", valid);
		}
	}

}