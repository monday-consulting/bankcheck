package hx.bankcheck.accountvalidator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testclass for testing algorithm 90.
 *  
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 *
 */
public class Checksum90Test {

	@Test
	public void testValidate() throws Throwable{
		
		Checksum90 checksum90 =new Checksum90();
		
		//Valid account numbers for testing method A
		int[] validAccountNumberA1={1,9,7,5,6,4,1};
		int[] validAccountNumberA2={1,9,8,8,6,5,4};
		int[] validAccountNumberNotUsingA={6,5,4,3,2,1};
		
		//Valid account numbers for testing method B
		int[] validAccountNumberB1={8,6,3,5,3,0};
		int[] validAccountNumberB2={7,8,4,4,5,1};
		int[] validAccountNumberNotUsingB1 ={9,9,7,6,6,4};
		int[] validAccountNumberNotUsingB2 ={8,6,3,5,3,6};

		//Valid account numbers for testing method C
		int[] validAccountNumberC1={6,5,4,3,2,1};
		int[] validAccountNumberC2={8,2,4,4,9,1};
		int[] validAccountNumberNotUsingC1 ={8,2,0,4,8,4};
		int[] validAccountNumberNotUsingC2 ={6,5,4,3,2,8};
		
		//Valid account numbers for testing method D
		int[] validAccountNumberD1={6,7,7,7,4,7};
		int[] validAccountNumberD2={8,4,0,5,0,7};
		int[] validAccountNumberNotUsingD1={6,7,7,7,4,2};
		int[] validAccountNumberNotUsingD2={7,2,6,3,9,1};

		//Valid account numbers for testing method E
		int[] validAccountNumberE1={9,9,6,6,6,3};
		int[] validAccountNumberE2={6,6,6,0,3,4};
		
		//Valid account numbers for testing method F
		int[] validAccountNumberF1={9,9,1,0,0,0,0,2};
		int[] validAccountNumberF2={9,9,1,0,0,0,0,2};
		
		//Invalid AccoutNumbers for all Methods
		int[] invalidAccountNumber1={1,9,2,4,5,9,2};
		int[] invalidAccountNumber2={9,0,1,5,6,8};
		int[] invalidAccountNumber3={8,2,0,4,8,7};
		int[] invalidAccountNumber4={7,2,6,3,9,3};
		int[] invalidAccountNumber5={9,2,4,5,9,1};
		
		
		//Should be valid using method A
		assertTrue(checksum90.validate(validAccountNumberA1)&&(checksum90.getMethodFlag()<=0));
		assertTrue(checksum90.validate(validAccountNumberA2)&&(checksum90.getMethodFlag()<=0));
		//Should be valid but not using method A
		assertTrue(checksum90.validate(validAccountNumberNotUsingA)&&(checksum90.getMethodFlag()==2));
		
		//Should be valid using method B
		assertTrue(checksum90.validate(validAccountNumberB1)&&(checksum90.getMethodFlag()<=1));
		assertTrue(checksum90.validate(validAccountNumberB2)&&(checksum90.getMethodFlag()<=1));
		//Should be valid but not using method B
		assertTrue(checksum90.validate(validAccountNumberNotUsingB1)&&(checksum90.getMethodFlag()==2));
		assertTrue(checksum90.validate(validAccountNumberNotUsingB2)&&(checksum90.getMethodFlag()==3));

		//Should be valid using method C
		assertTrue(checksum90.validate(validAccountNumberC1)&&(checksum90.getMethodFlag()<=2));
		assertTrue(checksum90.validate(validAccountNumberC2)&&(checksum90.getMethodFlag()<=2));
		//Should be valid but not using method C
		assertTrue(checksum90.validate(validAccountNumberNotUsingC1)&&(checksum90.getMethodFlag()==3));
		assertTrue(checksum90.validate(validAccountNumberNotUsingC2)&&(checksum90.getMethodFlag()==4));

		//Should be valid using method D
		assertTrue(checksum90.validate(validAccountNumberD1)&&(checksum90.getMethodFlag()<=3));
		assertTrue(checksum90.validate(validAccountNumberD2)&&(checksum90.getMethodFlag()<=3));
		//Should be valid but not using method D
		assertTrue(checksum90.validate(validAccountNumberNotUsingD1)&&(checksum90.getMethodFlag()==4));
		assertTrue(checksum90.validate(validAccountNumberNotUsingD2)&&(checksum90.getMethodFlag()==4));

		//Should be valid using method E
		assertTrue(checksum90.validate(validAccountNumberE1)&&(checksum90.getMethodFlag()<=4));
		assertTrue(checksum90.validate(validAccountNumberE2)&&(checksum90.getMethodFlag()<=4));

		//Should be valid using method F
		assertTrue(checksum90.validate(validAccountNumberF1)&&(checksum90.getMethodFlag()==5));
		assertTrue(checksum90.validate(validAccountNumberF2)&&(checksum90.getMethodFlag()==5));
		
		//Should be invalid in any case
		assertFalse(checksum90.validate(invalidAccountNumber1));
		assertFalse(checksum90.validate(invalidAccountNumber2));
		assertFalse(checksum90.validate(invalidAccountNumber3));
		assertFalse(checksum90.validate(invalidAccountNumber4));
		assertFalse(checksum90.validate(invalidAccountNumber5));
	}

}
