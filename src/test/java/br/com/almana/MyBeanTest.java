package br.com.almana;

import static org.testng.AssertJUnit.*;

import javax.naming.NamingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyBeanTest extends EJBContextAware {

	private static final String JAVA_GLOBAL_EJB3_1_POC_MY_BEAN = "java:global/ejb3.1-tomee-poc/MyBean";
	private static MyBean adder; 
	
	@BeforeMethod
	public void setup() throws NamingException {
		adder = lookup(MyBean.class, JAVA_GLOBAL_EJB3_1_POC_MY_BEAN);
	}
	
	@Test
	public void addTest() {
		int expected = 4;
		int sum = adder.add(1, 3);
		assertEquals(String.format("%d plus %d is %d", 1, 3, 4), expected, sum);
	}
	
}
