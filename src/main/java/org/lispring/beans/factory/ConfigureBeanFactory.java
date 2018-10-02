package org.lispring.beans.factory;

import java.util.List;

import org.lispring.beans.factory.config.AutowireCapableBeanFactory;
import org.lispring.beans.factory.config.BeanPostProcessor;

public interface ConfigureBeanFactory extends AutowireCapableBeanFactory {

	public void setClassLoader(ClassLoader cl);
	public ClassLoader getClassLoader();
	
	void addBeanPostProcessor(BeanPostProcessor postProcessor);
	List<BeanPostProcessor> getBeanPostProcessor();
}
