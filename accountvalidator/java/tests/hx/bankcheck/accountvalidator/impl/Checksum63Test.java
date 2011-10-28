/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for algorithm 63.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum63Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator=new Checksum63();
		
		// Valid account numbers for alternative 1
		int[] validAccountNumber1 = { 0, 1, 2, 3, 4, 5, 6, 6, 0, 0 };
		
		//Should be valid
		assertTrue(validator.validate(validAccountNumber1, null));

	}

}
