package org.lispring.context.annotation;

import org.lispring.beans.factory.annotation.AnnotationBeanDefinition;
import org.lispring.beans.factory.support.GenericBeanDefinition;
import org.lispring.core.type.AnnotationMetadata;

public class ScannerGenericBeanDefinition extends GenericBeanDefinition implements AnnotationBeanDefinition {
	
	private final AnnotationMetadata metadata;

	public ScannerGenericBeanDefinition(AnnotationMetadata metadata) {
		super();
		this.metadata =metadata;
		
		setBeanClassName(metadata.getClassName());
	}

	public AnnotationMetadata getMetadata() {
		return metadata;
	}

}
