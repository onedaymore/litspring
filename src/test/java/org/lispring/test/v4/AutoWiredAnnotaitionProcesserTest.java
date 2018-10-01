package org.lispring.test.v4;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.beans.factory.annotation.AutoWiredAnnotaitionProcesser;
import org.lispring.beans.factory.annotation.InjectionElement;
import org.lispring.beans.factory.annotation.InjectionMetadata;
import org.lispring.beans.factory.config.DependencyDescriptor;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.dao.AccountDao;
import org.lispring.dao.ItemDao;
import org.lispring.service.PetStoService;

public class AutoWiredAnnotaitionProcesserTest {
	
	
	AccountDao accountDao = new  AccountDao();
	ItemDao itemDao = new ItemDao();
	DefaultBeanFactory beanFactory = new DefaultBeanFactory(){
		public Object resolveDependency(DependencyDescriptor desc) {
			if (desc.getDependencyType().equals(AccountDao.class)) {
				return accountDao;
			}
			
			if (desc.getDependencyType().equals(ItemDao.class)) {
				return itemDao;
			}
			
			throw new RuntimeException("cannot cast ...");
		}
	};
	
	@Test
	public void testGetInjectMetadata() {
		AutoWiredAnnotaitionProcesser processer = new AutoWiredAnnotaitionProcesser();
		processer.setBeanFactory(beanFactory);
		InjectionMetadata injectionMetadata = processer.buildAutoWiringMetadata(PetStoService.class);
		List<InjectionElement> injectionElements = injectionMetadata.getInjectionElements();
		
		Assert.assertEquals(2, injectionElements.size());
		
		PetStoService ps = new PetStoService();
		injectionMetadata.inject(ps);
		System.out.println(ps.getAccountDao().getClass().getName());
		
		Assert.assertTrue(ps.getAccountDao() instanceof AccountDao);
		Assert.assertTrue(ps.getItemDao() instanceof ItemDao);
		
	}
}
