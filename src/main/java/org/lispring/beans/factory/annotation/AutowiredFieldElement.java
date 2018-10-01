package org.lispring.beans.factory.annotation;

import java.lang.reflect.Field;

import org.lispring.beans.factory.config.AutowireCapableBeanFactory;
import org.lispring.beans.factory.config.DependencyDescriptor;
import org.lispring.util.ReflectionUtils;

public class AutowiredFieldElement extends InjectionElement {
	
	boolean required;


	public AutowiredFieldElement(Field f, boolean required, AutowireCapableBeanFactory factory) {
		super(f, factory);
		this.required = required;
	}
	
	public Field getField() {
		return (Field) member;
	}

	@Override
	public void inject(Object target) {
		
		Field field = this.getField();
		try{
			DependencyDescriptor descriptor = new DependencyDescriptor(field, required);
			Object value = factory.resolveDependency(descriptor);
			if (value != null) {
				ReflectionUtils.makeAccessible(field);
				field.set(target, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
