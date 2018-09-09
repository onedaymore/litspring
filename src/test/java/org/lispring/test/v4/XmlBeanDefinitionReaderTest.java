package org.lispring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.context.annotation.ClassPathBeanDefinitationScanner;
import org.lispring.context.annotation.ScannerGenericBeanDefinition;
import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.core.type.AnnotationMetadata;
import org.lispring.stereotype.Component;

public class XmlBeanDefinitionReaderTest {
	
	@Test
	public void testXmlReader() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

		Resource resource = new ClassPathResource("beansV4.xml");
		
		reader.loadDefinitions(resource);
		
		String annotation = Component.class.getName();
		
		{
			BeanDefinition bd = factory.getBeanDefinition("petSto");
			
			Assert.assertTrue(bd instanceof ScannerGenericBeanDefinition);
			
			ScannerGenericBeanDefinition sbd = (ScannerGenericBeanDefinition) bd;
			
			AnnotationMetadata amd = sbd.getMetadata();
			
			Assert.assertTrue(amd.hasAnnotation(annotation));
			
			AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
			Assert.assertEquals("petSto", attributes.get("value"));
		}
		
		{
			BeanDefinition bd = factory.getBeanDefinition("accountDao");
			
			Assert.assertTrue(bd instanceof ScannerGenericBeanDefinition);
			
			ScannerGenericBeanDefinition sbd = (ScannerGenericBeanDefinition) bd;
			
			AnnotationMetadata amd = sbd.getMetadata();
			
			Assert.assertTrue(amd.hasAnnotation(annotation));
//			
//			AnnotationAttributes attributes = amd.getAnnotationAttributes(anntotation);
//			Assert.assertEquals("accountDao", attributes.get("value"));
		}
		
	}
}
