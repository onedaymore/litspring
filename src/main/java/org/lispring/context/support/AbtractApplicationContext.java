package org.lispring.context.support;

import org.lispring.beans.factory.ConfigureBeanFactory;
import org.lispring.beans.factory.annotation.AutoWiredAnnotaitionProcesser;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.context.ApplicationContext;
import org.lispring.core.io.Resource;
import org.lispring.util.ClassUtils;

public abstract class AbtractApplicationContext implements ApplicationContext {


	private DefaultBeanFactory factory;
	
	private ClassLoader cl;

	public AbtractApplicationContext(String path) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = getResourceFromPath(path);
		reader.loadDefinitions(resource);
		factory.setClassLoader(getClassLoader());
		
		// zhuru
		registerBeanPostProcessors(factory);
	}

	@Override
	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}
	
	protected abstract Resource getResourceFromPath(String path);

	
	public void setClassLoader(ClassLoader cl) {
		this.cl = cl;
	}

	
	public ClassLoader getClassLoader() {
		return cl != null ? cl : ClassUtils.getDefaultClassLoader();
	}
	
	
	protected void registerBeanPostProcessors(ConfigureBeanFactory beanFactory) {
		AutoWiredAnnotaitionProcesser postProcessor = new AutoWiredAnnotaitionProcesser();
		postProcessor.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(postProcessor);
	}
}
