package org.lispring.beans.factory.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.accessibility.Accessible;

import org.lispring.beans.BeanException;
import org.lispring.beans.factory.BeanCreateException;
import org.lispring.beans.factory.ConfigureBeanFactory;
import org.lispring.beans.factory.config.AutowireCapableBeanFactory;
import org.lispring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.core.annotation.AnnotationUtils;
import org.lispring.service.PetStoService;
import org.lispring.stereotype.AutoWired;
import org.lispring.util.ReflectionUtils;

public class AutoWiredAnnotaitionProcesser implements InstantiationAwareBeanPostProcessor {
	
	private AutowireCapableBeanFactory factory;
	private String requiredParameterName = "required";
	private boolean requiredParameterValue = true;
	
	private Set<Class<? extends Annotation>> autowiredAnnotationTypes = 
			new LinkedHashSet<>();
	
	public AutoWiredAnnotaitionProcesser() {
		autowiredAnnotationTypes.add(AutoWired.class);
	}

	public void setBeanFactory(ConfigureBeanFactory beanFactory) {
		factory = beanFactory;
		
	}

	public InjectionMetadata buildAutoWiringMetadata(Class<?> clazz) {
		LinkedList<InjectionElement> eles = new LinkedList<>();
		Class<?> targetClass = clazz;
		
		do {
			LinkedList<InjectionElement> currEles = new LinkedList<>();
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation ann = findAutowiredAnnotation(field);
				if (ann != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						continue;
					}
					boolean required = determineRequiredStatus(ann);
					currEles.add(new AutowiredFieldElement(field, required, factory));
				}
			}
		
			for (Method method : targetClass.getDeclaredMethods()) {
				/// TODO
			}
			
			eles.addAll(currEles);
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass != Object.class);
			
		return new InjectionMetadata(clazz, eles);
	}

	private boolean determineRequiredStatus(Annotation ann) {
		try {
			Method method = ReflectionUtils.findMethod(ann.annotationType(), requiredParameterName);
			if (method == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Annotation findAutowiredAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
			Annotation ann = AnnotationUtils.getAnnotation(ao, type);
			if (ann != null) {
				return ann;
			}
		}
		return null;
	}

	@Override
	public Object beforeInitialization(Object bean, String beanName) throws BeanException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object afterInitialization(Object bean, String beanName) throws BeanException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean afterInstantiation(Object bean, String beanName) throws BeanException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postProcessPropertyValues(Object bean, String beanName) throws BeanException {
		InjectionMetadata metadata = buildAutoWiringMetadata(bean.getClass());
		try {
			metadata.inject(bean);
		} catch (Throwable ex) {
			throw new BeanCreateException(beanName);
		}
		
	}

}
