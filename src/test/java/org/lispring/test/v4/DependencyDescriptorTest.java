package org.lispring.test.v4;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.factory.config.DependencyDescriptor;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.dao.AccountDao;
import org.lispring.service.PetStoService;

public class DependencyDescriptorTest {
	
	@Test
	public void testResolveDependency() throws NoSuchFieldException, SecurityException {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("beansV4.xml");	
		reader.loadDefinitions(resource);
		
		Field f = PetStoService.class.getDeclaredField("accountDao");
		DependencyDescriptor descriptor = new DependencyDescriptor(f, true);
		
		Object o = factory.resolveDependency(descriptor);
		Assert.assertTrue(o instanceof AccountDao);
	}

}
