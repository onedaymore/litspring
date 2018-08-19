package org.lispring.beans.factory;

public interface ConfigureBeanFactory extends BeanFactory {

	public void setClassLoader(ClassLoader cl);
	public ClassLoader getClassLoader();
}
