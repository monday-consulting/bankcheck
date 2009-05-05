package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidatorUnknownException;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class ChecksumValidatorFactory {
	private final static String CLASS_BASE_NAME = ChecksumValidatorFactory.class.getPackage().getName() + ".impl.Checksum";

	/**
	 * Creates the corresponding ChecksumValidator
	 * @param code Code, usually consists of two letters
	 * @return
	 * @throws ValidatorUnknownException Thrown when code is unknown
	 */
	public ChecksumValidator createValidatorFor(String code) throws ValidatorUnknownException {
		String className = CLASS_BASE_NAME + code;

		try {
			@SuppressWarnings("unchecked")
			Class<ChecksumValidator> clazz = (Class<ChecksumValidator>) Class.forName(className);
			
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

}
