package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.context.ApplicationContext;
import org.lispring.context.support.ClassPathXmlApplicationContext;
import org.lispring.context.support.FileSystemXmlApplicationContext;
import org.lispring.service.PetStoService;

/**
 * 
 * @author Administrator
 *
 */
public class ApplicationContextTestV2 {

	@Test
	public void testGetBean() {
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		PetStoService ps = (PetStoService) app.getBean("petSto");
		
		Assert.assertNotNull(ps.getAccountDao());
		Assert.assertNotNull(ps.getItemDao());
	}
	
}
