package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.BeanCreateException;
import org.lispring.beans.factory.BeanDefinitionException;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.service.PetStoService;

/**
 * 目标：给定一个配置，获取bean的定义
 * @author Administrator
 *
 */
public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		
		
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beans.xml");
		
		BeanDefinition db = facorty.getBeanDefinition("petSto");
		
		Assert.assertEquals("org.lispring.service.PetStoService", db.getBeanClassName());
		
		PetStoService pss = (PetStoService) facorty.getBean("petSto");
		
		Assert.assertNotNull(pss);
	}
	
	@Test
	public void testGetBeanScope() {
		
		
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beans.xml");
		
		BeanDefinition db = facorty.getBeanDefinition("petSto");
		
		Assert.assertTrue(db.isSingleton());
		Assert.assertFalse(db.isPrototype());
		
		//Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT, db.getScope());
		
		Assert.assertEquals("org.lispring.service.PetStoService", db.getBeanClassName());
		
		PetStoService pss = (PetStoService) facorty.getBean("petSto");
		
		Assert.assertNotNull(pss);
		PetStoService pss2 = (PetStoService) facorty.getBean("petSto");
		
		Assert.assertTrue(pss2 == pss);
	}
	
	@Test
	public void testInvalidBean() {
		try {
			DefaultBeanFactory facorty = new DefaultBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
			reader.loadDefinition("beans.xml");
			facorty.getBean("InvalidBean");
		} catch (BeanCreateException e) {
			return;
		}
		
		Assert.fail("excption occ");
	}
	
	@Test
	public void testInvalidBean2() {
		
		try {
			DefaultBeanFactory facorty = new DefaultBeanFactory();
			XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
			reader.loadDefinition("beansss.xml");
		} catch (BeanDefinitionException e) {
			return;
		}
		
		Assert.fail("excption occ");
	}
}
