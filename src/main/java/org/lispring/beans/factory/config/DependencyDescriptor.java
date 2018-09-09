package org.lispring.beans.factory.config;

import java.lang.reflect.Field;

public class DependencyDescriptor {
	
	private Field f;
	private boolean requied;
	
	public DependencyDescriptor(Field f, boolean requied) {
		this.f = f ;
		this.requied = requied;
	}
	
	public Class<?> getDependencyType() {
		if (f != null) {
			return f.getType();
		}
		throw new RuntimeException("f is null");
	}

	public boolean isRequied() {
		return requied;
	}

}
