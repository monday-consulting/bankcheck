package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum10;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum10Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {
		ChecksumValidator cs = new Checksum10();
		
		int[][] correctNumbers = {
				{0,0,1,2,3,4,5,0,0,8},
				{0,0,8,7,6,5,4,0,0,8}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
	}
	
}