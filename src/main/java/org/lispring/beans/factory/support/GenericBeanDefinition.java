package org.lispring.beans.factory.support;

import org.lispring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
	
	private String beanId;
	private String className;
	private String scope;
	private boolean singleton = true;
	private boolean prototype = false;

	public GenericBeanDefinition(String beanId, String className) {
		super();
		this.beanId = beanId;
		this.className = className;
	}

	@Override
	public String getBeanClassName() {
		return className;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	@Override
	public boolean isPrototype() {
		return prototype;
	}

	@Override
	public String getScope() {
		return scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}
	
	

}
