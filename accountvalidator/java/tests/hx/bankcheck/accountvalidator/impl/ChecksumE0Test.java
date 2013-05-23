package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * @author Dirk Schrödter (ds@monday-consulting.com) - Monday Consulting GmbH
 * @version 1.0
 */
public class ChecksumE0Test extends AbstractChecksumTest{
	
	@Override
	public void testValidate() throws Throwable {
		ChecksumValidator cs = new ChecksumE0();

		int[][] correctNumbers = new int[][] {				
				{1,2,3,4,5,6,8,0,1,3}, 
				{1,5,3,4,5,6,8,0,1,0},
				{0,0,0,2,6,1,0,0,1,5}, 
				{8,7,4,1,0,1,3,0,1,1}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
		assertTrue(cs.validate(correctNumbers[2],null));
		assertTrue(cs.validate(correctNumbers[3],null));
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,7,6,9,0,1,3},
				{0,0,0,2,7,1,0,0,1,4},
				{9,7,4,1,0,1,5,0,1,1}				
		};
		
		assertFalse(cs.validate(wrongNumbers[0],null));
		assertFalse(cs.validate(wrongNumbers[1],null));
		assertFalse(cs.validate(wrongNumbers[2],null));
	}
}