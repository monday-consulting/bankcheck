package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Testclass for testing algorithm 51.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @author Dirk Schrödter (ds@monday-consulting.com) - Monday Consulting GmbH
 * 
 * @version 1.1
 * 
 */
public class Checksum51Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new Checksum51();

		// Valid account numbers for alternative A
		int[] validAccountNumberAlternativeA_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };
		int[] validAccountNumberAlternativeA_2 = { 0, 0, 0, 1, 1, 5, 6, 1, 3, 6 };
		
		// Inalid account numbers for alternative A
		int[] invalidAccountNumberAlternativeA_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 8 };
		int[] invalidAccountNumberAlternativeA_2 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 9 };
		
		// Valid account numbers for alternative B
		int[] validAccountNumberAlternativeB_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 8 }; 
		int[] validAccountNumberAlternativeB_2 = { 0, 0, 0, 1, 2, 3, 4, 5, 6, 7 };
		
		// Invalid account numbers for alternative B
		int[] invalidAccountNumberAlternativeB_1 = { 0, 0, 0, 1, 2, 3, 4, 5, 6, 6 }; 
		int[] invalidAccountNumberAlternativeB_2 = { 0, 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		
		// Valid account numbers for alternative C
		int[] validAccountNumberAlternativeC_1 = { 0, 0, 0, 0, 3, 4, 0, 9, 6, 8 }; 
		int[] validAccountNumberAlternativeC_2 = { 0, 0, 0, 0, 2, 0, 1, 1, 7, 8 };
		
		// Invalid account numbers for alternative C
		int[] invalidAccountNumberAlternativeC_1 = { 0, 0, 2, 3, 4, 5, 6, 7, 8, 3 }; 
		int[] invalidAccountNumberAlternativeC_2 = { 0, 0, 7, 6, 7, 4, 3, 2, 1, 1 };		
		
		// Valid account numbers for alternative D
		int[] validAccountNumberAlternativeD_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 1 }; 
		int[] validAccountNumberAlternativeD_2 = { 0, 1, 0, 1, 3, 5, 6, 0, 7, 3 };		
		
		// Invalid account numbers for alternative D
		int[] invalidAccountNumberAlternativeD_1 = { 0, 1, 2, 3, 4, 1, 2, 3, 4, 5 }; 
		int[] invalidAccountNumberAlternativeD_2 = { 0, 0, 6, 7, 4, 9, 3, 6, 4, 7 };			
		
		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0, 0, 0, 0, 2 }; 
		int[] validAccountNumberExceptionAlternative1_2 = { 0, 0, 9, 9, 1, 0, 0, 0, 1, 0 };
		int[] validAccountNumberExceptionAlternative1_3 = { 2, 5, 9, 9, 1, 0, 0, 0, 0, 2 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0, 0, 0, 0, 4 };
		int[] invalidAccountNumberExceptionAlternative1_2 = { 2, 5, 9, 9, 1, 0,	0, 0, 0, 3 };
		int[] invalidAccountNumberExceptionAlternative1_3 = { 0, 0, 9, 9, 3, 4,	5, 6, 7, 8 };

		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative2_1 = { 0, 1, 9, 9, 1, 0, 0, 0, 0, 4 }; 
		int[] validAccountNumberExceptionAlternative2_2 = { 2, 5, 9, 9, 1, 0, 0, 0, 0, 3 };
		int[] validAccountNumberExceptionAlternative2_3 = { 3, 1, 9, 9, 2, 0, 4, 0, 9, 0 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative2_1 = { 0, 0, 9, 9, 3, 4,	5, 6, 7, 8 };
		int[] invalidAccountNumberExceptionAlternative2_2 = { 0, 0, 9, 9, 1, 0,	0, 1, 1, 0 };
		int[] invalidAccountNumberExceptionAlternative2_3 = { 0, 1, 9, 9, 1, 0,	0, 0, 4, 0 };

		// Should be valid for alternative A
		assertTrue(validator.validate(validAccountNumberAlternativeA_1, null) && (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternativeA_2, null) && (validator.getAlternative() == 0));
		
		// Should be invalid for alternative A
		assertFalse(validator.validate(invalidAccountNumberAlternativeA_1, null) && (validator.getAlternative() == 0));
		assertFalse(validator.validate(invalidAccountNumberAlternativeA_2, null) && (validator.getAlternative() == 0));		

		// Should be valid for alternative B (also valid for alternative A)
		assertTrue(validator.validate(validAccountNumberAlternativeB_1, null) && (validator.getAlternative() <= 1));
		assertTrue(validator.validate(validAccountNumberAlternativeB_2, null) && (validator.getAlternative() <= 1));	
		
		// Should be invalid for alternative B
		assertFalse(validator.validate(invalidAccountNumberAlternativeB_1, null) && (validator.getAlternative() == 1));
		assertFalse(validator.validate(invalidAccountNumberAlternativeB_2, null) && (validator.getAlternative() == 1));			

		// Should be valid for alternative C
		assertTrue(validator.validate(validAccountNumberAlternativeC_1, null) && (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternativeC_2, null) && (validator.getAlternative() == 2));
		
		// Should be invalid for alternative C
		assertFalse(validator.validate(invalidAccountNumberAlternativeC_1, null) && (validator.getAlternative() == 2));
		assertFalse(validator.validate(invalidAccountNumberAlternativeC_2, null) && (validator.getAlternative() == 2));			
		
		// Should be valid for alternative D
		assertTrue(validator.validate(validAccountNumberAlternativeD_1, null) && (validator.getAlternative() == 3));
		assertTrue(validator.validate(validAccountNumberAlternativeD_2, null) && (validator.getAlternative() == 3));	
		
		// Should be invalid for alternative D
		assertFalse(validator.validate(invalidAccountNumberAlternativeD_1, null) && (validator.getAlternative() == 3));
		assertFalse(validator.validate(invalidAccountNumberAlternativeD_2, null) && (validator.getAlternative() == 3));			

		// Should be valid for alternative 1
		assertTrue(validator.validate(validAccountNumberExceptionAlternative1_1, null) && (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberExceptionAlternative1_2, null) && (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberExceptionAlternative1_3, null) && (validator.getAlternative() == 0));

		// Should be invalid for alternative 1
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative1_1, null) && (validator.getAlternative() == 0));
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative1_2, null) && (validator.getAlternative() == 0));
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative1_3, null) && (validator.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(validator.validate(validAccountNumberExceptionAlternative2_1, null) && (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberExceptionAlternative2_2, null) && (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberExceptionAlternative2_3, null) && (validator.getAlternative() == 1));

		// Should be invalid for alternative 2
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative2_1, null) && (validator.getAlternative() == 1));
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative2_2, null) && (validator.getAlternative() == 1));
		assertFalse(validator.validate(invalidAccountNumberExceptionAlternative2_3, null) && (validator.getAlternative() == 1));
	}
}
