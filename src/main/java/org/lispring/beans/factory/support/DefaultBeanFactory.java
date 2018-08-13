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

public class DefaultBeanFactory implements BeanFactory {
	
	private final Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();

	public DefaultBeanFactory(String config) {
		// 1,ÐèÒª½âÎöxml
		loadDefinition(config);
		
	}
	
	private void loadDefinition(String config) {
		InputStream is = null;
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		is = cl.getResourceAsStream(config);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();//<beans>
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext()) {
				Element ele = it.next();
				String beanId = ele.attributeValue("id");
				String className = ele.attributeValue("class");
				BeanDefinition db = new GenericBeanDefinition(beanId, className);
				bdMap.put(beanId, db);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionException("BeanDefinitionException");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		return bdMap.get(beanId);
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
