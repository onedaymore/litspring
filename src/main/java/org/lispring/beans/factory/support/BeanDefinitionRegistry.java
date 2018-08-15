package org.lispring.beans.factory.support;

import org.lispring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String beanId);
	void registryBeanDefinition(String beanId, BeanDefinition bd);
}
