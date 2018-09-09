package org.lispring.context.annotation;

import java.beans.Introspector;
import java.util.Set;

import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.annotation.AnnotationBeanDefinition;
import org.lispring.beans.factory.support.BeanDefinitionRegistry;
import org.lispring.beans.factory.support.BeanNameGenerator;
import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.type.AnnotationMetadata;
import org.lispring.util.ClassUtils;
import org.lispring.util.StringUtils;

public class AnnotaotionBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generatorBeanName(BeanDefinition bd, BeanDefinitionRegistry registry) {
		if (bd instanceof AnnotationBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotationBeanDefinition) bd);
			if (!StringUtils.isEmpty(beanName)) {
				return beanName;
			}
		}
		return buildDefaultBeanName(bd, registry);
	}

	private String buildDefaultBeanName(BeanDefinition bd, BeanDefinitionRegistry registry) {
		
		return buildDefaultBeanName(bd);
	}

	private String buildDefaultBeanName(BeanDefinition bd) {
		String shortClassName = ClassUtils.getShortName(bd.getBeanClassName());
		return Introspector.decapitalize(shortClassName);
	}

	private String determineBeanNameFromAnnotation(AnnotationBeanDefinition bd) {
		
		AnnotationMetadata amd = bd.getMetadata();
		Set<String> types = amd.getAnnotationType();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes aa = amd.getAnnotationAttributes(type);
			if (aa.get("value") != null) {
				Object value = aa.get("value");
				if (value instanceof String) {
					String strValue = (String) value;
					beanName = strValue;
				}
			}
		}
		return beanName;
	}

}
