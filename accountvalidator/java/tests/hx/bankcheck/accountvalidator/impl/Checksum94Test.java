package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum94;

/**
 * Testclass for testing algorithm 95.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum94Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {

		Checksum94 checksum = new Checksum94();

		// Valid account numbers for testing alternative 1
		int[] validAccountNumber = { 6, 7, 8, 2, 5, 3, 3, 0, 0, 3 };

		// Should be valid
		assertTrue(checksum.validate(validAccountNumber));

	}

}
