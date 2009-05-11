package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum19;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum19Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator cs = new Checksum19();

		int[][] correctNumbers = { { 0, 2, 4, 0, 3, 3, 4, 0, 0, 0 },
				{ 0, 2, 0, 0, 5, 2, 0, 0, 1, 6 } };

		assertTrue(cs.validate(correctNumbers[0], null));
		assertTrue(cs.validate(correctNumbers[1], null));
	}

}