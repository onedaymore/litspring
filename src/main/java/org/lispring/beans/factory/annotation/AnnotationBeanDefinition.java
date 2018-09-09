package org.lispring.beans.factory.annotation;

import org.lispring.beans.BeanDefinition;
import org.lispring.core.type.AnnotationMetadata;

public interface AnnotationBeanDefinition extends BeanDefinition {

	AnnotationMetadata getMetadata();

}
