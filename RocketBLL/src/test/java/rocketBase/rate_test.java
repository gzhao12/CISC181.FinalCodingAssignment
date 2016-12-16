package rocketBase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.RateException;
import rocketBase.RateBLL;

public class rate_test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	} 
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetRate() throws RateException {
		assertTrue(RateBLL.getRate(700) == 4);
		assertTrue(RateBLL.getRate(750) == 3.75);
	}
	
	@Test(expected = RateException.class)
	public void testException() throws RateException {
		assertTrue(RateBLL.getRate(100) != 0);
	}
	
	@Test
	public void testPayment() {
		assertEquals(RateBLL.getPayment(.04/12, 360, 300000, 0, false), -1432.25, .005);
	}

}
