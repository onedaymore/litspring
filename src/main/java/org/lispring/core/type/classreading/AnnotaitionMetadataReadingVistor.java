package org.lispring.core.type.classreading;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.type.AnnotationMetadata;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

public class AnnotaitionMetadataReadingVistor extends ClassMetadataReadingVistor implements AnnotationMetadata {
	
	private final Set<String> anntationSet = new LinkedHashSet<>();
	private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>();
	
	public AnnotaitionMetadataReadingVistor() {
		
	}

	@Override
	public AnnotationVisitor visitAnnotation(final String desc, boolean visable) {
		String className = Type.getType(desc).getClassName();
		anntationSet.add(className);
		return new AnnotationAttributesReadingVisitor(className, attributesMap);
	}

	public AnnotationAttributes getAnnotationAttributes(String anntotation) {
		return attributesMap.get(anntotation);
	}

	public Set<String> getAnntationTypes() {
		return anntationSet;
	}
	
	
	public boolean hasAnntation(String anntationType) {
		return anntationSet.contains(anntationType);
	}

	@Override
	public boolean hasAnnotation(String annotationType) {
		return  anntationSet.contains(annotationType);
	}

	@Override
	public Set<String> getAnnotationType() {
		return anntationSet;
	}

}
