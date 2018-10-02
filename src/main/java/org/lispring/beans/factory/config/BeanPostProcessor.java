package org.lispring.beans.factory.config;

import org.lispring.beans.BeanException;

public interface BeanPostProcessor {

	Object beforeInitialization(Object bean, String beanName) throws BeanException;
	
	Object afterInitialization(Object bean, String beanName) throws BeanException;
}
