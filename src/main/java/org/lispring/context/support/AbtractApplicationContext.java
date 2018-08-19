package org.lispring.context.support;

import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.context.ApplicationContext;
import org.lispring.core.io.Resource;

public abstract class AbtractApplicationContext implements ApplicationContext {


	private DefaultBeanFactory factory;

	public AbtractApplicationContext(String path) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = getResourceFromPath(path);
		reader.loadDefinitions(resource);
	}

	@Override
	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}
	
	protected abstract Resource getResourceFromPath(String path);

}
