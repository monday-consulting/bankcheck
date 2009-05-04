package hx.bankcheck.accountvalidator;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AccountCheckTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for hx.bankcheck.accountvalidator");
		//$JUnit-BEGIN$
		
		// Testing algorithms
		suite.addTestSuite(Checksum90Test.class);
		suite.addTestSuite(Checksum91Test.class);
		suite.addTestSuite(Checksum92Test.class);
		suite.addTestSuite(Checksum93Test.class);
		suite.addTestSuite(Checksum94Test.class);
		suite.addTestSuite(Checksum95Test.class);
		suite.addTestSuite(Checksum96Test.class);
		suite.addTestSuite(Checksum97Test.class);
		suite.addTestSuite(Checksum98Test.class);
		
		// Testing utils
		suite.addTestSuite(ChecksumUtilsTest.class);
		
		//$JUnit-END$
		return suite;
	}

}
