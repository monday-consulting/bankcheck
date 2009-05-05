package hx.bankcheck.accountvalidator.exceptions;

public class IllegalAccountNumber extends ValidationException {
	private static final long serialVersionUID = 4201977648958334207L;

	public IllegalAccountNumber() {
		super();
	}

	public IllegalAccountNumber(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAccountNumber(String message) {
		super(message);
	}

	public IllegalAccountNumber(Throwable cause) {
		super(cause);
	}

}
