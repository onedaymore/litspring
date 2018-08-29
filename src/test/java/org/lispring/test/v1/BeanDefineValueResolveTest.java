package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.config.RuntimeBeanRefrence;
import org.lispring.beans.factory.config.TypeStringValue;
import org.lispring.beans.factory.support.BeanDefineValueResolver;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.dao.AccountDao;

public class BeanDefineValueResolveTest {
	
	@Test
	public void testBeanDefineValueResolve() {
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beans.xml");
		
		BeanDefineValueResolver resolver = new BeanDefineValueResolver(facorty);
		RuntimeBeanRefrence refrence = new RuntimeBeanRefrence("accountDao");
		Object obj = resolver.resolveValueIfNecessary(refrence);
		Assert.assertNotNull(obj);
		Assert.assertTrue(obj instanceof AccountDao);
	}
	
	@Test
	public void testResolveTypeValue() {
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beans.xml");
		
		BeanDefineValueResolver resolver = new BeanDefineValueResolver(facorty);
		TypeStringValue stringValue  = new TypeStringValue("test");
		Object obj = resolver.resolveValueIfNecessary(stringValue);
		Assert.assertNotNull(obj);
		Assert.assertEquals("test", obj);
	}
}
