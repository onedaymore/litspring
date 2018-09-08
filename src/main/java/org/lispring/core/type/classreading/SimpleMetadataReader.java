package org.lispring.core.type.classreading;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.core.type.AnnotationMetadata;
import org.lispring.core.type.ClassMetadata;
import org.lispring.core.type.MetadataReader;
import org.springframework.asm.ClassReader;

public class SimpleMetadataReader implements MetadataReader {
	
	private final Resource resource;
	
	private AnnotationMetadata annotationMetadata;
	
	private ClassMetadata classMetadata;

	public SimpleMetadataReader(Resource resource) throws IOException {
		this.resource = resource;
		
		InputStream is = new BufferedInputStream(resource.getInputStream());
		
		ClassReader cl = null;
		
		try {
			cl = new ClassReader(is);
			
		} finally {
			is.close();
		}
		
		AnnotaitionMetadataReadingVistor visitor = new AnnotaitionMetadataReadingVistor();
		cl.accept(visitor, ClassReader.SKIP_DEBUG);
		this.annotationMetadata = visitor;
		this.classMetadata = visitor;
		
	}

	@Override
	public AnnotationMetadata getAnnotationMetadata() {
		return annotationMetadata;
	}

	@Override
	public ClassMetadata getClassMetadata() {

		return classMetadata;
	}

	@Override
	public Resource getResource() {
		return resource;
	}

}
