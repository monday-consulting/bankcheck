package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum16;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum16Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator cs = new Checksum16();

		int[][] correctNumbers = { { 0, 0, 9, 4, 0, 1, 2, 3, 4, 1 },
				{ 5, 0, 7, 3, 3, 2, 1, 0, 1, 0 },
				{ 0, 0, 9, 4, 0, 1, 2, 3, 4, 4 } };

		assertTrue(cs.validate(correctNumbers[0], null));
		assertTrue(cs.validate(correctNumbers[1], null));
		assertTrue(cs.validate(correctNumbers[2], null));
	}

}