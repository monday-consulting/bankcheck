package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.ChecksumValidatorFactory;
import hx.bankcheck.accountvalidator.exceptions.ValidatorUnknownException;
import hx.bankcheck.accountvalidator.impl.Checksum00;
import hx.bankcheck.accountvalidator.impl.Checksum99;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class ChecksumValidatorFactoryTest {

	private ChecksumValidatorFactory factory;
	
	@Before
	public void init() {
		factory = new ChecksumValidatorFactory();
	}
	
	@Test(expected=ValidatorUnknownException.class)
	public void testCreateValidatorFor() throws ValidatorUnknownException {
		ChecksumValidator validator = factory.createValidatorFor("00");
		assertTrue(validator.getClass() == Checksum00.class);
		
		validator = factory.createValidatorFor("99");
		assertTrue(validator.getClass() == Checksum99.class);
		
		validator = factory.createValidatorFor("unknownClass");
	}
	
}
