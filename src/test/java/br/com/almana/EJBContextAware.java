package br.com.almana;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class EJBContextAware {

	private static final String INJECT = "inject";
	private static EJBContainer container;

	@BeforeTest
	public void startup() throws NamingException {
		if (container == null) {
			container = EJBContainer.createEJBContainer();
		}
	}

	@AfterTest
	public void shutdown() {
		container.close();
	}

	@SuppressWarnings("unchecked")
	protected <T> T lookup(Class<T> clazz, String jndi) throws NamingException {
		return (T) context().lookup(jndi);
	}

	protected Context context() {
		return container.getContext();
	}

	private void bind(String name, Object bean) throws NamingException {
		context().bind(name, bean);
	}

	protected void bind() throws NamingException {
		bind(INJECT, this);
	}

}
