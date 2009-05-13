package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 *
 */
public class Checksum49Test extends AbstractChecksumTest{
	
	@Override
	public void testValidate() throws Throwable {
		ChecksumValidator cs = new Checksum49();

		int[][] correctNumbers = new int[][] {
				{0,0,0,9,2,9,0,7,0,1},
				{0,5,3,9,2,9,0,8,5,8},
				{0,0,0,1,5,0,1,8,2,4},
				{0,0,0,1,5,0,1,8,3,2}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
		assertTrue(cs.validate(correctNumbers[2],null));
		assertTrue(cs.validate(correctNumbers[3],null));
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,5,6,7,8,9,0}
		};
		assertFalse(cs.validate(wrongNumbers[0],null));
	}
	
}