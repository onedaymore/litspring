package org.lispring.beans.factory;

import org.lispring.beans.BeanDefinition;
import org.lispring.service.PetStoService;

public interface BeanFactory {

	Object getBean(String beanId);

}
