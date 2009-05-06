package hx.bankcheck.accountvalidator.exceptions;

public class IllegalAccountNumberException extends ValidationException {
	private static final long serialVersionUID = 4201977648958334207L;

	public IllegalAccountNumberException() {
		super();
	}

	public IllegalAccountNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAccountNumberException(String message) {
		super(message);
	}

	public IllegalAccountNumberException(Throwable cause) {
		super(cause);
	}

}
