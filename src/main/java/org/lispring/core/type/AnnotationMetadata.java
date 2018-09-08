package org.lispring.core.type;

import java.util.Set;

import org.lispring.core.annotation.AnnotationAttributes;

public interface AnnotationMetadata extends ClassMetadata {

	boolean hasAnnotation(String annotationType);
	
	AnnotationAttributes getAnnotationAttributes(String annotationType);

	Set<String> getAnnotationType();
}
