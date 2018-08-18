package org.lispring.context.support;

import org.lispring.beans.factory.support.BeanDefinitionRegistry;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.context.ApplicationContext;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;

public class ClassPathXmlApplicationContext implements ApplicationContext {
	
	private DefaultBeanFactory factory;

	public ClassPathXmlApplicationContext(String config) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource(config);
		reader.loadDefinitions(resource);
	}

	@Override
	public Object getBean(String beanId) {
		// TODO Auto-generated method stub
		return factory.getBean(beanId);
	}

}
