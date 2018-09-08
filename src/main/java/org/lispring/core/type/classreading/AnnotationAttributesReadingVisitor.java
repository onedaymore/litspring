package org.lispring.core.type.classreading;

import java.util.Map;

import javax.naming.StringRefAddr;

import org.lispring.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
	
	private final String annotationType;
	
	private final Map<String, AnnotationAttributes> attributsMap;
	
	AnnotationAttributes attributes = new  AnnotationAttributes();

	public AnnotationAttributesReadingVisitor(String annotationType, 
			Map<String, AnnotationAttributes> attributsMap) {
		super(SpringAsmInfo.ASM_VERSION);
		this.annotationType = annotationType;
		this.attributsMap = attributsMap;
		
	}
	
	@Override
	public void visit(String attributeName, Object attributeValue) {
		attributes.put(attributeName, attributeValue);
		
	}

	@Override
	public void visitEnd() {
		attributsMap.put(annotationType, attributes);
	}

}


