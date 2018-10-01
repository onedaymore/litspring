package org.lispring.test.v4;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.factory.annotation.AutowiredFieldElement;
import org.lispring.beans.factory.annotation.InjectionElement;
import org.lispring.beans.factory.annotation.InjectionMetadata;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.dao.AccountDao;
import org.lispring.dao.ItemDao;
import org.lispring.service.PetStoService;

public class InjectMetaDataTest {

	@Test
	public void testInjectMetaData() throws NoSuchFieldException, SecurityException {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("beansV4.xml");	
		reader.loadDefinitions(resource);
		
		Class<?> clz = PetStoService.class;
		LinkedList<InjectionElement> elements = new LinkedList<>();
		{
			Field f = PetStoService.class.getDeclaredField("accountDao");
			InjectionElement injectEle = new AutowiredFieldElement(f, true, factory);
			elements.add(injectEle);
		}
		
		{
			Field f = PetStoService.class.getDeclaredField("itemDao");
			InjectionElement injectEle = new AutowiredFieldElement(f, true, factory);
			elements.add(injectEle);
		}
		
		InjectionMetadata metadata = new InjectionMetadata(clz, elements);
		PetStoService ps = new PetStoService();
		metadata.inject(ps);
		System.out.println(ps.getAccountDao().getClass().getName());
		
		Assert.assertTrue(ps.getAccountDao() instanceof AccountDao);
		Assert.assertTrue(ps.getItemDao() instanceof ItemDao);
	}
}
