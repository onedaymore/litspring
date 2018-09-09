package org.lispring.beans.factory.config;

import org.lispring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

	public Object resolveDependency(DependencyDescriptor descriptor);
}
