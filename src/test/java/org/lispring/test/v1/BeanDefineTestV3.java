package org.lispring.test.v1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.ConstructorArgument;
import org.lispring.beans.ConstructorArgument.ValueHolder;
import org.lispring.beans.PropertyValue;
import org.lispring.beans.factory.config.RuntimeBeanRefrence;
import org.lispring.beans.factory.support.BeanDefineValueResolver;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.core.io.support.ClassPathResource;

public class BeanDefineTestV3 {
	
	@Test
	public void testConstruct() {
		DefaultBeanFactory facorty = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facorty);
		reader.loadDefinition("beansV3.xml");
		
		BeanDefinition bd = facorty.getBeanDefinition("petSto");
		
		ConstructorArgument args = bd.getConstructorArgument();
		List<ValueHolder> holds = args.getValueHolders();
		
		RuntimeBeanRefrence ref1 = (RuntimeBeanRefrence) holds.get(0).getValue();
		Assert.assertEquals("accountDao", ref1.getBeanName());
		RuntimeBeanRefrence ref2 = (RuntimeBeanRefrence) holds.get(1).getValue();
		Assert.assertEquals("itemDao", ref2.getBeanName());
		
	}
	

}
