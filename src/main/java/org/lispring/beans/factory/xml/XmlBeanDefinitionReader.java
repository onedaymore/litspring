package org.lispring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.BeanDefinitionException;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.support.BeanDefinitionRegistry;
import org.lispring.beans.factory.support.GenericBeanDefinition;
import org.lispring.core.io.Resource;
import org.lispring.util.ClassUtils;

/**
 * ∂¡»°xml£¨Ω‚Œˆxml
 * @author Administrator
 *
 */
public class XmlBeanDefinitionReader {
	
	private static final String ID_ATTR = "id";
	
	private static final String CLASS_ATTR = "class";
	
	private static final String SCOPE_ATTR = "scope";
	
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
				String beanId = ele.attributeValue(ID_ATTR);
				String className = ele.attributeValue(CLASS_ATTR);
				BeanDefinition bd = new GenericBeanDefinition(beanId, className);
				this.registry.registryBeanDefinition(beanId, bd);
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

}
