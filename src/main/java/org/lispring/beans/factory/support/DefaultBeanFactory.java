package org.lispring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.BeanCreateException;
import org.lispring.beans.factory.BeanDefinitionException;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.service.PetStoService;
import org.lispring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {
	
	private final Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();
	
	public DefaultBeanFactory() {
		
	}

	@Override
	public void registryBeanDefinition(String beanId, BeanDefinition bd) {
		this.bdMap.put(beanId, bd);
		
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		return this.bdMap.get(beanId);
	}

	@Override
	public Object getBean(String beanId) {
		BeanDefinition bd = getBeanDefinition(beanId);
		if (bd == null) {
			throw new BeanCreateException("create ex");
		}
		
		String className = bd.getBeanClassName();
		try {
			Class clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) { 
			throw new BeanCreateException("create ex");
		}
	}


	
}
