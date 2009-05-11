package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum17;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum17Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator cs = new Checksum17();

		int[][] correctNumbers = { { 0, 4, 4, 6, 7, 8, 6, 0, 4, 0 } };

		assertTrue(cs.validate(correctNumbers[0], null));
	}

}