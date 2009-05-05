package hx.bankcheck.accountvalidator.exceptions;

public class ValidatorUnknownException extends Exception {
	private static final long serialVersionUID = -8569678427613099656L;

	public ValidatorUnknownException() {
		super();
	}

	public ValidatorUnknownException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorUnknownException(String message) {
		super(message);
	}

	public ValidatorUnknownException(Throwable cause) {
		super(cause);
	}

}
