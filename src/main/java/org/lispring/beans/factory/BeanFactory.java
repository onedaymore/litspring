package org.lispring.beans.factory;

public interface BeanFactory {

	Object getBean(String beanId);

	Class<?> getType(String targetBeanName);

}
