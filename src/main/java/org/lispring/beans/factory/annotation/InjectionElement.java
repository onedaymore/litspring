package org.lispring.beans.factory.annotation;

import java.lang.reflect.Member;

import org.lispring.beans.factory.config.AutowireCapableBeanFactory;

public abstract class InjectionElement {
	
	protected Member member;
	protected AutowireCapableBeanFactory factory;
	
	public InjectionElement(Member member, AutowireCapableBeanFactory factory) {
		this.member = member;
		this.factory = factory;
	}



	public abstract void inject(Object target);

}
