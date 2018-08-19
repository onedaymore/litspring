package org.lispring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.lispring.beans.factory.xml.SingleBeanRegistry;
import org.lispring.util.Assert;

public class DefaultSingleBeanRegistry implements SingleBeanRegistry {
	
	private final Map<String, Object> singletionObjects = new ConcurrentHashMap<>(64);

	@Override
	public void registerSingleton(String beanId, Object singleton) {
		Assert.notNULL(beanId, "beanId not null");
		
		Object oldObj = singletionObjects.get(beanId);
		
		if (oldObj != null) {
			throw new IllegalStateException(" object already exists");
		}
		singletionObjects.put(beanId, singleton);

	}

	@Override
	public Object getSingleton(String beanId) {
		return singletionObjects.get(beanId);
	}

}
