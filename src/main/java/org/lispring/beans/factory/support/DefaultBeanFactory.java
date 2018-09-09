package org.lispring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.PropertyValue;
import org.lispring.beans.factory.BeanCreateException;
import org.lispring.beans.factory.BeanDefinitionException;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.ConfigureBeanFactory;
import org.lispring.beans.factory.config.DependencyDescriptor;
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
		Object obj = instanseBean(bd);
		
		//…Ë÷√ Ù–‘
		populateBean(bd, obj);
		return obj;
		
	}
	
	private void populateBean(BeanDefinition bd, Object obj) {
		List<PropertyValue> pvs = bd.getPropertyValues();
		if (pvs == null || pvs.isEmpty()) {
			return;
		}
		
		BeanDefineValueResolver resolver = new BeanDefineValueResolver(this);
		
		for (PropertyValue pv : pvs) {
			String propertyName = pv.getName();
			Object valueName = pv.getValue();
			Object convertObj  = resolver.resolveValueIfNecessary(valueName);
			
			//ddd
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
				PropertyDescriptor [] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if (pd.getName().equals(propertyName)) {
						Method m = pd.getWriteMethod();
						m.invoke(obj, convertObj);
						break;
					}
				}
				
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	Object instanseBean(BeanDefinition bd) {
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

	@Override
	public Object resolveDependency(DependencyDescriptor descriptor) {
		Class<?> typeToMatch = descriptor.getDependencyType();
		
		for (BeanDefinition bd : bdMap.values()) {
			resolveBeanClass(bd);
			
			Class<?> beanClass = bd.getBeanClass();
			
			if (typeToMatch.isAssignableFrom(beanClass)) {
				return getBean(bd.getID());
			}
		}
		return null;
	}

	private void resolveBeanClass(BeanDefinition bd) {
		if (bd.hasBeanClass()) {
			return;
		} else {
			try {
				bd.reolveBeanClass(getClassLoader());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}




	
}
