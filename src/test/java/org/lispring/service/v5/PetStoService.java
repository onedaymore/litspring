package org.lispring.service.v5;

import org.lispring.dao.v5.AccountDao;
import org.lispring.dao.v5.ItemDao;
import org.lispring.stereotype.AutoWired;
import org.lispring.stereotype.Component;

@Component(value = "petSto")
public class PetStoService {
	
	@AutoWired
	private AccountDao accountDao;
	@AutoWired
	private ItemDao itemDao;
	
	public PetStoService(AccountDao accountDao, ItemDao itemDao) {
		super();
		this.accountDao = accountDao;
		this.itemDao = itemDao;
	}

	public PetStoService() {
		// TODO Auto-generated constructor stub
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
	
	public void placeOrder() {
		System.out.println("placeOrder");
	}
	

}
