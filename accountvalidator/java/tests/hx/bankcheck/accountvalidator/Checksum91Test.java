package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum91;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 91.
 *  
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 *
 */
public class Checksum91Test extends TestCase{

	@Test
	public void testValidate() throws Throwable{
		
		Checksum91 checksum91=new Checksum91();
		
		// Valid account numbers for testing method 1
		int[] validAccountNumberMethod1_1 = { 2, 9, 7, 4, 1, 1, 8, 0, 0, 0 };
		int[] validAccountNumberMethod1_2 = { 5, 2, 8, 1, 7, 4, 1, 0, 0, 0 };
		int[] validAccountNumberMethod1_3={9,9,5,2,8,1,0,0,0,0};
		
		//Invalid account numbers for testing method 1
		int[] invalidAccountNumberMethod1_1={8,8,4,0,0,1,7,0,0,0};
		int[] invalidAccountNumberMethod1_2={8,8,4,0,0,2,3,0,0,0};
		int[] invalidAccountNumberMethod1_3={8,8,4,0,0,4,1,0,0,0};
		
		//Valid account numbers for testing method 2
		int[] validAccountNumberMethod2_1={2,9,7,4,1,1,7,0,0,0};
		int[] validAccountNumberMethod2_2={5,2,8,1,7,7,0,0,0,0};
		int[] validAccountNumberMethod2_3={9,9,5,2,8,1,2,0,0,0};
		
		//Invalid account numbers for testing method 2
		int[] invalidAccountNumberMethod2_1={8,8,4,0,0,1,4,0,0,0};
		int[] invalidAccountNumberMethod2_2={8,8,4,0,0,2,6,0,0,0};
		int[] invalidAccountNumberMethod2_3={8,8,4,0,0,4,5,0,0,0};
		
		//Valid account numbers for testing method 2
		int[] validAccountNumberMethod3_1={8,8,4,0,0,1,9,0,0,0};
		int[] validAccountNumberMethod3_2={8,8,4,0,0,5,0,0,0,0};
		int[] validAccountNumberMethod3_3={8,8,4,0,0,8,7,0,0,0};
		int[] validAccountNumberMethod3_4={8,8,4,0,0,4,5,0,0,0};
		
		//Invalid account numbers for testing method 2
		int[] invalidAccountNumberMethod3_1={8,8,4,0,0,1,1,0,0,0};
		int[] invalidAccountNumberMethod3_2={8,8,4,0,0,2,5,0,0,0};
		int[] invalidAccountNumberMethod3_3={8,8,4,0,0,6,2,0,0,0};

		//Valid account numbers for testing method 2
		int[] validAccountNumberMethod4_1={8,8,4,0,0,1,2,0,0,0};
		int[] validAccountNumberMethod4_2={8,8,4,0,0,5,5,0,0,0};
		int[] validAccountNumberMethod4_3={8,8,4,0,0,8,0,0,0,0};
		
		//Invalid account numbers for testing method 2
		int[] invalidAccountNumberMethod4_1={8,8,4,0,0,1,0,0,0,0};
		int[] invalidAccountNumberMethod4_2={8,8,4,0,0,5,7,0,0,0};
		
		//Should be valid using method 1
		assertTrue(checksum91.validate(validAccountNumberMethod1_1)&&(checksum91.getMethodFlag()==0));
		assertTrue(checksum91.validate(validAccountNumberMethod1_2)&&(checksum91.getMethodFlag()==0));
		assertTrue(checksum91.validate(validAccountNumberMethod1_3)&&(checksum91.getMethodFlag()==0));

		//Should be invalid using method 1
		assertFalse(checksum91.validate(invalidAccountNumberMethod1_1)&&(checksum91.getMethodFlag()==0));
		assertFalse(checksum91.validate(invalidAccountNumberMethod1_2)&&(checksum91.getMethodFlag()==0));
		assertFalse(checksum91.validate(invalidAccountNumberMethod1_3)&&(checksum91.getMethodFlag()==0));
		
		//Should be valid using method 2
		assertTrue(checksum91.validate(validAccountNumberMethod2_1)&&(checksum91.getMethodFlag()==1));
		assertTrue(checksum91.validate(validAccountNumberMethod2_2)&&(checksum91.getMethodFlag()==1));
		assertTrue(checksum91.validate(validAccountNumberMethod2_3)&&(checksum91.getMethodFlag()==1));

		//Should be invalid using method 2
		assertFalse(checksum91.validate(invalidAccountNumberMethod2_1)&&(checksum91.getMethodFlag()==1));
		assertFalse(checksum91.validate(invalidAccountNumberMethod2_2)&&(checksum91.getMethodFlag()==1));
		assertFalse(checksum91.validate(invalidAccountNumberMethod2_3)&&(checksum91.getMethodFlag()==1));

		//Should be valid using method 3
		assertTrue(checksum91.validate(validAccountNumberMethod3_1)&&(checksum91.getMethodFlag()==2));
		assertTrue(checksum91.validate(validAccountNumberMethod3_2)&&(checksum91.getMethodFlag()==2));
		assertTrue(checksum91.validate(validAccountNumberMethod3_3)&&(checksum91.getMethodFlag()==2));
		assertTrue(checksum91.validate(validAccountNumberMethod3_4)&&(checksum91.getMethodFlag()==2));

		//Should be invalid using method 3
		assertFalse(checksum91.validate(invalidAccountNumberMethod3_1)&&(checksum91.getMethodFlag()==2));
		assertFalse(checksum91.validate(invalidAccountNumberMethod3_2)&&(checksum91.getMethodFlag()==2));
		assertFalse(checksum91.validate(invalidAccountNumberMethod3_3)&&(checksum91.getMethodFlag()==2));

		//Should be valid using method 4
		assertTrue(checksum91.validate(validAccountNumberMethod4_1)&&(checksum91.getMethodFlag()==3));
		assertTrue(checksum91.validate(validAccountNumberMethod4_2)&&(checksum91.getMethodFlag()==3));
		assertTrue(checksum91.validate(validAccountNumberMethod4_3)&&(checksum91.getMethodFlag()==3));

		//Should be invalid using method 4
		assertFalse(checksum91.validate(invalidAccountNumberMethod4_1)&&(checksum91.getMethodFlag()==3));
		assertFalse(checksum91.validate(invalidAccountNumberMethod4_2)&&(checksum91.getMethodFlag()==3));

	}

}
