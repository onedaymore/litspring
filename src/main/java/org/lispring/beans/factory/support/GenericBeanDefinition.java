package org.lispring.beans.factory.support;

import org.lispring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
	
	private String beanId;
	private String className;

	public GenericBeanDefinition(String beanId, String className) {
		super();
		this.beanId = beanId;
		this.className = className;
	}

	@Override
	public String getBeanClassName() {
		return className;
	}
	
	

}
