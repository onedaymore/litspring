package org.lispring.beans.factory;

import org.lispring.beans.BeanDefinition;
import org.lispring.service.PetStoService;

public interface BeanFactory {

	BeanDefinition getBeanDefinition(String string);

	Object getBean(String beanId);

}
