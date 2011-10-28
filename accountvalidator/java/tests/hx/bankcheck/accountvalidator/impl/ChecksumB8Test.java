package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.ChecksumB8;

/**
 * Testclass for testing algorithm B8.
 * 
 * @author Sascha D�mer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB8Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws ValidationException {

		ChecksumValidator validator = new ChecksumB8();

		// Valid account numbers for alternative 1
		int[][] validAccountNumberAlternative1 = {
				{ 0, 7, 3, 4, 1, 9, 2, 6, 5, 7 },
				{ 6, 9, 3, 2, 8, 7, 5, 2, 7, 4 }
		};
		// Invalid account numbers for alternative 1
		int[][] invalidAccountNumberAlternative1 = {
				{ 3, 1, 4, 5, 8, 6, 3, 0, 2, 9 },
				{ 3, 1, 4, 5, 8, 6, 3, 0, 2, 9 },
				{ 2, 9, 3, 8, 6, 9, 2, 5, 2, 3 },
				{ 0, 1, 3, 2, 5, 7, 2, 9, 7, 5 },
				{ 5, 4, 3, 2, 1, 9, 8, 7, 6, 0 },
				{ 9, 0, 7, 0, 8, 7, 3, 3, 3, 3 },
				{ 5, 0, 1, 1, 6, 5, 4, 3, 6, 6 },
				{ 9, 0, 0, 0, 4, 1, 2, 3, 4, 0 },
				{ 9, 3, 1, 0, 3, 0, 5, 0, 1, 1 }
		};

		// Valid account numbers for alternative 2
		int[][] validAccountNumberAlternative2 = {
				{ 3, 1, 4, 5, 8, 6, 3, 0, 2, 9 },
				{ 2, 9, 3, 8, 6, 9, 2, 5, 2, 3 }
		};
		// Invalid account numbers for alternative 2
		int[][] invalidAccountNumberAlternative2 = {
				{ 0, 1, 3, 2, 5, 7, 2, 9, 7, 5 },
				{ 5, 4, 3, 2, 1, 9, 8, 7, 6, 0 },
				{ 9, 0, 7, 0, 8, 7, 3, 3, 3, 3 },
				{ 9, 0, 0, 0, 4, 1, 2, 3, 4, 0 },
				{ 9, 3, 1, 0, 3, 0, 5, 0, 1, 1 }
		};
		
		// Valid account numbers for alternative 3
		int[][] validAccountNumberAlternative3 = {
				{ 5, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 5, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
				{ 9, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 9, 1, 0, 9, 9, 9, 9, 9, 9, 9 }
		};
		int[][] invalidAccountNumberAlternative3 = {
				{ 9, 1, 1, 0, 0, 0, 0, 0, 0, 1 }
		};
		

		// Should be valid using alternative 1
		for (int[] a : validAccountNumberAlternative1) {
			assertTrue((validator.validate(a, null))
					&& (validator.getAlternative() == 0));
		}

		// Should be invalid using alternative 1
		for(int[] invalidAccountNumber : invalidAccountNumberAlternative1) {
			assertFalse((validator.validate(invalidAccountNumber,
					null))
					&& (validator.getAlternative() == 0));
		}

		
		// Should be valid using alternative 2
		for (int[] a : validAccountNumberAlternative2) {
			assertTrue((validator.validate(a, null))
					&& (validator.getAlternative() == 1));
		}

		// Should be invalid using alternative 2
		for(int[] invalidAccountNumber : invalidAccountNumberAlternative2) {
			assertFalse((validator.validate(invalidAccountNumber,
					null))
					&& (validator.getAlternative() == 1));
		}

		
		
		
		// Should be valid using alternative 3
		for (int[] a : validAccountNumberAlternative3) {
			assertTrue(validator.validate(a, null));
			assertTrue(validator.getAlternative() == 2);
		}

		// Should be invalid using alternative 3
		for(int[] invalidAccountNumber : invalidAccountNumberAlternative3) {
			boolean valid = validator.validate(invalidAccountNumber, null);
			assertFalse(valid);
		}
	}
}
