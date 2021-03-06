package org.lispring.beans;

import java.util.List;


public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_DEFAULT = ""; 

	String getBeanClassName();
	
	boolean isSingleton();
	
	boolean isPrototype();
	
	String getScope();
	
	void setScope(String scope);

	List<PropertyValue> getPropertyValues();

	ConstructorArgument getConstructorArgument();

	public String getID();

	Class<?> getBeanClass();

	boolean hasBeanClass();

	 Class<?> reolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
}
