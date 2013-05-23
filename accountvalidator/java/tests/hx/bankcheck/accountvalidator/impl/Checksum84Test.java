/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm 84.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @author Dirk Schrödter (ds@monday-consulting.com) - Monday Consulting GmbH
 * 
 * @version 1.1
 * 
 */
public class Checksum84Test extends AbstractChecksumTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.AbstractChecksumTest#testValidate()
	 */
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new Checksum84();
		
		// Valid account numbers for alternative A
		int[] validAccountNumberAlternativeA_1 = { 0, 0, 0, 0, 2, 4, 0, 6, 9, 9 };
		int[] validAccountNumberAlternativeA_2 = { 0, 0, 0, 0, 3, 5, 0, 9, 8, 2 }; 
		int[] validAccountNumberAlternativeA_3 = { 0, 0, 0, 0, 4, 6, 1, 0, 5, 9 };
		
		// Valid account numbers for alternative B
		int[] validAccountNumberAlternativeB_1 = { 0, 0, 0, 0, 2, 4, 0, 6, 9, 2 }; 
		int[] validAccountNumberAlternativeB_2 = { 0, 0, 0, 0, 3, 5, 0, 9, 8, 5 }; 
		int[] validAccountNumberAlternativeB_3 = { 0, 0, 0, 0, 4, 6, 1, 0, 5, 2 };
		
		// Valid account numbers for alternative C
		int[] validAccountNumberAlternativeC_1 = { 0, 0, 0, 0, 2, 4, 0, 6, 9, 1 }; 
		int[] validAccountNumberAlternativeC_2 = { 0, 0, 0, 0, 3, 5, 0, 9, 8, 4 }; 
		int[] validAccountNumberAlternativeC_3 = { 0, 0, 0, 0, 4, 6, 1, 0, 5, 4 };
		
		// Invalid account numbers
		int[] invalidAccountNumber_1 = { 0, 0, 0, 0, 2, 4, 0, 9, 6, 5 };   
		int[] invalidAccountNumber_2 = { 0, 0, 0, 0, 3, 5, 0, 9, 8, 0 }; 
		int[] invalidAccountNumber_3 = { 0, 0, 0, 0, 4, 6, 1, 0, 5, 3 };		
		
		// Should be valid for alternative A
		assertTrue(validator.validate(validAccountNumberAlternativeA_1, null) && (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternativeA_2, null) && (validator.getAlternative() == 0));
		assertTrue(validator.validate(validAccountNumberAlternativeA_3, null) && (validator.getAlternative() == 0));
				
		// Should be valid for alternative B
		assertTrue(validator.validate(validAccountNumberAlternativeB_1, null) && (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternativeB_2, null) && (validator.getAlternative() == 1));
		assertTrue(validator.validate(validAccountNumberAlternativeB_3, null) && (validator.getAlternative() == 1));
		
		// Should be valid for alternative C
		assertTrue(validator.validate(validAccountNumberAlternativeC_1, null) && (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternativeC_2, null) && (validator.getAlternative() == 2));
		assertTrue(validator.validate(validAccountNumberAlternativeC_3, null) && (validator.getAlternative() == 2));
		
		// Should be invalid 
		assertFalse(validator.validate(invalidAccountNumber_1, null));
		assertFalse(validator.validate(invalidAccountNumber_2, null));	
		assertFalse(validator.validate(invalidAccountNumber_3, null));	
	}
}
