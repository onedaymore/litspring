package org.lispring.tx;

public class TransactionManager {

	public void start() {
		System.out.println("start");
	}
	
	public void commit() {
		System.out.println("commit");
	}
	
	public void rollback() {
		System.out.println("rollback");
	}
}
