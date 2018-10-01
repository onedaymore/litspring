package org.lispring.beans.factory.annotation;

import java.util.List;

public class InjectionMetadata {
	
	private final Class<?> targetClass;
	private List<InjectionElement> injectionElements;
	
	public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElements) {
		super();
		this.targetClass = targetClass;
		this.injectionElements = injectionElements;
	}
	public List<InjectionElement> getInjectionElements() {
		return injectionElements;
	}
	public void setInjectionElements(List<InjectionElement> injectionElements) {
		this.injectionElements = injectionElements;
	}
	
	public void inject(Object target) {
		if (injectionElements == null || injectionElements.isEmpty()) {
			return;
		}
		
		for (InjectionElement injectionElement : injectionElements) {
			injectionElement.inject(target);
		}
	}
	
}
