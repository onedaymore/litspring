package org.lispring.beans.factory.xml;

public interface SingleBeanRegistry {

	void registerSingleton(String beanId, Object singleton);
	
	Object getSingleton(String beanId);
}
