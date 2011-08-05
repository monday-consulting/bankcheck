package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum13;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 * 
 *         $Id$
 */
public class Checksum13Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator cs = new Checksum13();

		int[][] correctNumbers = { { 0, 0, 0, 6, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 0, 0, 6, 0, 0, 0, 4 } };
		int[][] invalidNumbers = { { 0, 0, 0, 6, 0, 0, 0, 4, 1, 0 },
				{ 1, 0, 0, 0, 0, 6, 0, 0, 0, 4 } };

		assertTrue(cs.validate(correctNumbers[0], null));
		assertTrue(cs.validate(correctNumbers[1], null));
		assertFalse(cs.validate(invalidNumbers[0], null));
		assertFalse(cs.validate(invalidNumbers[1], null));
	}

}