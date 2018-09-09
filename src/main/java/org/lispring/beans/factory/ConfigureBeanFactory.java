package org.lispring.beans.factory;

import org.lispring.beans.factory.config.AutowireCapableBeanFactory;

public interface ConfigureBeanFactory extends AutowireCapableBeanFactory {

	public void setClassLoader(ClassLoader cl);
	public ClassLoader getClassLoader();
}
