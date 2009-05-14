package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.AccountNumberNotTestableException;
import hx.bankcheck.accountvalidator.exceptions.ValidatorUnknownException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class BAVDataTest extends TestCase {

	private static final String CLASS_BASE_NAME = BAVDataTest.class
			.getPackage().getName().concat(".impl.Checksum%s");

	private Properties validAccountNumbers;
	private Properties invalidAccountNumbers;

	public BAVDataTest() {
		validAccountNumbers = new Properties();
		invalidAccountNumbers =new Properties();
		try {
			validAccountNumbers.load(new FileInputStream(
					"tests/validAccountNumbers.properties"));
			invalidAccountNumbers.load(new FileInputStream(
					"tests/invalidAccountNumbers.properties"));
		} catch (IOException e) {
			fail(String.format("IOException occured: %s", e.getMessage()));
		}
	}

	@Test
	public void testValidAccountNumbers() throws Throwable {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 10; j++) {
				String code = parseCode(i, j);
				if (validAccountNumbers.getProperty(code) != null) {
					String[] accountNumbersAsString = validAccountNumbers
							.getProperty(code).split(",");
					ChecksumValidator validator = createValidatorFor(code);
					for (int k = 0; k < accountNumbersAsString.length; k++) {
						try {
							assertTrue(
									String
											.format(
													"Validating %s for code %s wasn't successfull!",
													accountNumbersAsString[k],
													code),
									validator
											.validate(
													parseAccoutNumber(accountNumbersAsString[k]),
													null));
						} catch (AccountNumberNotTestableException e) {
							System.err.println(String.format(
									"%s is not testable for code %s!",
									accountNumbersAsString[k], code));
						}
					}
				}
			}
		}
	}
	
	@Test
	public void testInvalidAccountNumbers() throws Throwable {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 10; j++) {
				String code = parseCode(i, j);
				if (invalidAccountNumbers.getProperty(code) != null) {
					String[] accountNumbersAsString = invalidAccountNumbers
							.getProperty(code).split(",");
					ChecksumValidator validator = createValidatorFor(code);
					for (int k = 0; k < accountNumbersAsString.length; k++) {
						try {
							assertFalse(
									String
											.format(
													"Validating %s for code %s wasn't successfull!",
													accountNumbersAsString[k],
													code),
									validator
											.validate(
													parseAccoutNumber(accountNumbersAsString[k]),
													null));
						} catch (AccountNumberNotTestableException e) {
							System.err.println(String.format(
									"%s is not testable for code %s!",
									accountNumbersAsString[k], code));
						}
					}
				}
			}
		}
	}

	private String parseCode(int firstDigit, int secondDigit) {
		switch (firstDigit) {
		case 10:
			return new String("A" + secondDigit);
		case 11:
			return new String("B" + secondDigit);
		case 12:
			return new String("C" + secondDigit);
		case 13:
			return new String("D" + secondDigit);
		default:
			return new String(firstDigit + "" + secondDigit);
		}
	}

	private int[] parseAccoutNumber(String accountNumber) {
		try {
			int[] accountNumberAsArray = new int[10];
			char[] tmpCharArray = accountNumber.trim().toCharArray();
			for (int i = tmpCharArray.length - 1; i >= 0; i--) {
				accountNumberAsArray[i + (10 - tmpCharArray.length)] = new Integer(
						String.valueOf(tmpCharArray[i])).intValue();
			}
			return accountNumberAsArray;
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(String
					.format(
							"ArrayIndexOutOfBoundsException occured for accountnumber: %s",
							accountNumber));
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	private ChecksumValidator createValidatorFor(String code)
			throws ValidatorUnknownException {
		String className = String.format(CLASS_BASE_NAME, code);

		try {
			Class<ChecksumValidator> clazz = (Class<ChecksumValidator>) Class
					.forName(className);

			ChecksumValidator validator = clazz.newInstance();

			return validator;
		} catch (ClassNotFoundException e) {
			throw new ValidatorUnknownException("Unknown Code: " + code, e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// private void printArray(int[] array){
	// for (int i = 0; i < array.length; i++) {
	// System.err.print(array[i]);
	// }
	// System.err.print("\n");
	// }

}
