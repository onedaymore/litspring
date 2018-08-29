package org.lispring.service;

import org.lispring.dao.AccountDao;
import org.lispring.dao.ItemDao;

public class PetStoService {
	
	private AccountDao accountDao;
	private ItemDao itemDao;
	
	public PetStoService(AccountDao accountDao, ItemDao itemDao) {
		super();
		this.accountDao = accountDao;
		this.itemDao = itemDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	
	

}
