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
import org.lispring.beans.factory.ConfigureBeanFactory;
import org.lispring.service.PetStoService;
import org.lispring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingleBeanRegistry
implements ConfigureBeanFactory, BeanDefinitionRegistry {
	
	private final Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();
	private ClassLoader cl;
	
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
		
		if (bd.isSingleton()) {
			Object obj = getSingleton(beanId);
			if (obj == null) {
				obj = createBean(bd);
				registerSingleton(beanId, obj);
			}
			return obj;
		}
		
		return  createBean(bd);
	}
	
	Object createBean(BeanDefinition bd) {
		String className = bd.getBeanClassName();
		try {
			Class clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) { 
			throw new BeanCreateException("create ex");
		}
	}

	@Override
	public void setClassLoader(ClassLoader cl) {
		this.cl = cl;
		
	}

	@Override
	public ClassLoader getClassLoader() {
		
		return cl != null ? cl : ClassUtils.getDefaultClassLoader();
	}


	
}
