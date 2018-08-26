package org.lispring.test.v1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.PropertyValue;
import org.lispring.beans.factory.config.RuntimeBeanRefrence;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.core.io.support.ClassPathResource;

public class BeanDefineTestV2 {
	
	@Test
	public void testPropertys() {
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beans.xml");
		
		BeanDefinition db = facorty.getBeanDefinition("petSto");
		
		List<PropertyValue> list = db.getPropertyValues();
		
		Assert.assertTrue(list.size() == 2);
		{
			PropertyValue pv = this.getPropertyValue("accountDao", list);
			Assert.assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanRefrence);
		}
		
		{
			PropertyValue pv = this.getPropertyValue("itemDao", list);
			Assert.assertNotNull(pv);
			Assert.assertTrue(pv.getValue() instanceof RuntimeBeanRefrence);
		}
		
	}
	
	private PropertyValue getPropertyValue(String name, List<PropertyValue> list) {
		for (PropertyValue pv : list) {
			if (name.equals(pv.getName())) {
				return pv;
			} 
		}

		return null;
	}

}
