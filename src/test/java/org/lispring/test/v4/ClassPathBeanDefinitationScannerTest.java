package org.lispring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.context.annotation.ClassPathBeanDefinitationScanner;
import org.lispring.context.annotation.ScannerGenericBeanDefinition;
import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.type.AnnotationMetadata;
import org.lispring.stereotype.Component;

/**
 * 给定一个目录，将目录下的类通过注解成beandefine，注册到beanFactory
 * 
 * @author Administrator
 *
 */
public class ClassPathBeanDefinitationScannerTest {
	
	@Test
	public void testScanBean() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		
		String basePackage = "org.lispring.dao,org.lispring.service";

		ClassPathBeanDefinitationScanner scanner = new ClassPathBeanDefinitationScanner(factory);
		
		scanner.doScan(basePackage);
		
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
