package hx.bankcheck.accountvalidator.exceptions;

public class IllegalAccountNumber extends Exception {
	private static final long serialVersionUID = 6549031052608627491L;

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
