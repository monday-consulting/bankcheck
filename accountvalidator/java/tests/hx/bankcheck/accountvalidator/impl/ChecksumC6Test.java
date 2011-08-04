/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.impl.ChecksumC6;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Testclass for testing algorithm C6.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumC6Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumC6();

		// Valid account numbers
		long[] validNumbers = { 65516L, 203178249L, 1031405209L, 1082012201L,
				2003455189L, 2004001016L, 3110150986L, 3068459207L,
				5035105948L, 5286102149L, 6028426119L, 6861001755L,
				7008199027L, 7002000023L, 9000430223L, 9000781153L };
		
		// Invalid account numbers
		long[] invalidNumbers = { 525111212L, 91423614L, 1082311275L,
				1000118821L, 2004306518L, 2016001206L, 3462816371L,
				3622548632L, 4232300145L, 4000456126L, 5002684526L,
				5564123850L, 6295473774L, 6640806317L, 7000062022L,
				7006003027L, 8348300005L, 8654216984L, 9000641509L, 9000260986L
		};

		// Should be valid
		for (long n : validNumbers) {
			int[] accountNumber = ChecksumUtils.parseAccountNumber(n);
			boolean valid = validator.validate(accountNumber, null);
			assertTrue("Invalid: "+n, valid);
		}

		// Should be invalid
		for (long n : invalidNumbers) {
			int[] accountNumber = ChecksumUtils.parseAccountNumber(n);
			boolean valid = validator.validate(accountNumber, null);
			assertFalse(valid);
		}

	}
}
