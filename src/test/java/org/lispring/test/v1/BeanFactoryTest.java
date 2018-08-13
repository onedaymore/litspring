package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.BeanFactory;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.service.PetStoService;

/**
 * Ŀ�꣺����һ�����ã���ȡbean�Ķ���
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
