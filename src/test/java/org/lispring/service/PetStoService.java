package org.lispring.service;

import org.lispring.dao.AccountDao;
import org.lispring.dao.ItemDao;

public class PetStoService {
	
	private AccountDao accountDao;
	private ItemDao itemDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

}
