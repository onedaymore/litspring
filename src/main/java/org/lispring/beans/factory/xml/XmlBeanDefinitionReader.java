package org.lispring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.ConstructorArgument;
import org.lispring.beans.PropertyValue;
import org.lispring.beans.factory.BeanDefinitionException;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.config.RuntimeBeanRefrence;
import org.lispring.beans.factory.config.TypeStringValue;
import org.lispring.beans.factory.support.BeanDefinitionRegistry;
import org.lispring.beans.factory.support.GenericBeanDefinition;
import org.lispring.context.annotation.ClassPathBeanDefinitationScanner;
import org.lispring.core.io.Resource;
import org.lispring.util.Assert;
import org.lispring.util.ClassUtils;
import org.lispring.util.StringUtils;

/**
 * ��ȡxml������xml
 * @author Administrator
 *
 */
public class XmlBeanDefinitionReader {
	
	private static final String ID_ATTR = "id";
	
	private static final String CLASS_ATTR = "class";
	
	private static final String SCOPE_ATTR = "scope";
	
	private static final String PROPERTY_ELE = "property";
	
	private static final String REF_ATTR = "ref";
	
	private static final String VALUE_ATTR = "value";
	
	private static final String NAME_ATTR = "name";
	
	private static final String CONSTRUCTOR_ARG_ELE = "constructor-arg";
	
	private static final String TYPE_ATTR = "type";
	
	private static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
	
	private static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";
	
	private static final String BASE_PACKAGE_ATTR = "base-package";
	
	
	BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadDefinition(String config) {
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
				String beanId = ele.attributeValue(ID_ATTR);
				String className = ele.attributeValue(CLASS_ATTR);
				BeanDefinition bd = new GenericBeanDefinition(beanId, className);
				if (ele.attributeValue(SCOPE_ATTR) != null) {
					bd.setScope(ele.attributeValue(SCOPE_ATTR));
				}
				parseConstructorArgEles(ele, bd);
				parsePropertyElement(ele, bd);
				this.registry.registryBeanDefinition(beanId, bd);
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
	
	public void loadDefinitions(Resource resource) {
		InputStream is = null;
		SAXReader reader = new SAXReader();
		try {
			is = resource.getInputStream();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();//<beans>
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext()) {
				Element ele = it.next();
				String nameSpaceUrl = ele.getNamespaceURI();
						
				if (isDefaultNamespace(nameSpaceUrl)) {
					parseDefaultElement(ele);
				} else if (isContextNamespace(nameSpaceUrl)) {
					parseContextElement(ele);
				}
				

			}
		} catch (DocumentException e) {
			throw new BeanDefinitionException("BeanDefinitionException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	private boolean isContextNamespace(String nameSpaceUrl) {
		return CONTEXT_NAMESPACE_URI.equals(nameSpaceUrl);
	}

	private boolean isDefaultNamespace(String nameSpaceUrl) {
		//return !StringUtils.isEmpty(nameSpaceUrl) || BEANS_NAMESPACE_URI.equals(nameSpaceUrl);
		
		return BEANS_NAMESPACE_URI.equals(nameSpaceUrl);
	}

	private void parseContextElement(Element ele) {
		String basePackages = ele.attributeValue(BASE_PACKAGE_ATTR);
		ClassPathBeanDefinitationScanner scanner = new ClassPathBeanDefinitationScanner(registry);
		scanner.doScan(basePackages);
		
	}

	private void parseDefaultElement(Element ele) {
		String beanId = ele.attributeValue(ID_ATTR);
		String className = ele.attributeValue(CLASS_ATTR);
		BeanDefinition bd = new GenericBeanDefinition(beanId, className);
		if (ele.attributeValue(SCOPE_ATTR) != null) {
			bd.setScope(ele.attributeValue(SCOPE_ATTR));
		}
		parseConstructorArgEles(ele, bd);
		parsePropertyElement(ele, bd);
		this.registry.registryBeanDefinition(beanId, bd);
		
	}

	public void parsePropertyElement(Element ele, BeanDefinition bd) {
		Iterator it = ele.elementIterator(PROPERTY_ELE);
		while (it.hasNext()) {
			Element proEle = (Element) it.next();
			String propertyName = proEle.attributeValue(NAME_ATTR);
			if (StringUtils.isEmpty(propertyName)) {
				return;
			}
			
			Object value = parsePropertyValue(proEle, bd, propertyName);
			
			PropertyValue pv = new PropertyValue(propertyName, value);
			bd.getPropertyValues().add(pv);
		}
	}

	private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {

		boolean hasRefAttr = ele.attributeValue(REF_ATTR) != null;
		boolean hasValueAttr = ele.attributeValue(VALUE_ATTR) != null;
		
		if (hasRefAttr) {
			String refName = ele.attributeValue(REF_ATTR);
			RuntimeBeanRefrence rbf = new RuntimeBeanRefrence(refName);
			return rbf;
		} else if (hasValueAttr) {
			String valueName = ele.attributeValue(VALUE_ATTR);
			TypeStringValue valueHolder = new TypeStringValue(valueName);
			return valueHolder;
		} else {
			
		}
		return null;
	}
	
	public void parseConstructorArgEles(Element ele, BeanDefinition bd) {
		Iterator iter = ele.elementIterator(CONSTRUCTOR_ARG_ELE);
		while (iter.hasNext()) {
			Element el = (Element) iter.next();
			parseConstructorArgEle(el, bd);
		}
	}
	
	public void parseConstructorArgEle(Element ele, BeanDefinition bd) {
		Object value = parsePropertyValue(ele, bd, null);
		
		ConstructorArgument.ValueHolder vh = new ConstructorArgument.ValueHolder(value);
		bd.getConstructorArgument().addArgumens(vh);
	}
	

}
