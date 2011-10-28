/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * Testclass for testing algorithm D1.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * @version 1.1
 * 
 */
public class ChecksumD1Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumD1();

		long validAccountNumbers[] = { 82012203L, 1452683581L, 2129642505L,
				3002000027L, 4230001407L, 5000065514L, 6001526215L, 9000430223L };

		for (long n : validAccountNumbers) {
			int[] accountNumber = ChecksumUtils.parseAccountNumber(n);
			assertTrue(validator.validate(accountNumber, null));
		}

		long invalidAccountNumbers[] = { 260986L, 1062813622L, 2256412314L,
				3012084101L, 4006003027L, 5814500990L, 6128462594L, 7000062025L,
				8003306026L, 9000641509L };

		for (long n : invalidAccountNumbers) {
			int[] accountNumber = ChecksumUtils.parseAccountNumber(n);
			assertFalse(validator.validate(accountNumber, null));
		}

	}
}
