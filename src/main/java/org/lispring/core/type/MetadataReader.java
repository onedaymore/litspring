package org.lispring.core.type;

import org.lispring.core.io.Resource;

public interface MetadataReader {
	
	Resource getResource();

	AnnotationMetadata getAnnotationMetadata();

	ClassMetadata getClassMetadata();

}
