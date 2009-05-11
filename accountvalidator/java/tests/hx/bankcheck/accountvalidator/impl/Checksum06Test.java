package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.impl.Checksum06;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum06Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws Exception {
		ChecksumValidator cs = new Checksum06();
		
		int[][] correctNumbers = {
				{0,0,9,4,0,1,2,3,4,1},
				{5,0,7,3,3,2,1,0,1,0}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
	}
	
}