package org.lispring.beans.factory.support;

import org.lispring.beans.BeanDefinition;

public interface BeanNameGenerator {

	String generatorBeanName(BeanDefinition sbd, BeanDefinitionRegistry registry);

}
