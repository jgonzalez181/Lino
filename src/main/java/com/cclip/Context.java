package com.cclip;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {

	private static ApplicationContext applicationContext;

	public static <T> T getBean(String arg0, Class<T> arg1) throws BeansException {

		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext("spring/root_context.xml");

		}

		return applicationContext.getBean(arg0, arg1);
	}
	//

}
