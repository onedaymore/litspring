package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.service.PetStoService;

/**
 * 目标：给定一个配置，获取bean的定义
 * @author Administrator
 *
 */
public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		BeanFactory facorty = new DefaultBeanFactory("beans.xml");
		
		BeanDefinition db = facorty.getBeanDefinition("petSto");
		
		Assert.assertEquals("org.lispring.service.PetStoService", db.getBeanClassName());
		
		PetStoService pss = (PetStoService) facorty.getBean("petSto");
		
		Assert.assertNotNull(pss);
	}
}
