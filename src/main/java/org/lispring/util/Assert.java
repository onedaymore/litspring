package org.lispring.util;

public class Assert {

	public static void notNULL(String path, String mes) {
		if (path == null) {
			throw new IllegalArgumentException(mes);
		}
		
	}

}
