package org.lispring.test.v1;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.core.type.classreading.AnnotaitionMetadataReadingVistor;
import org.lispring.core.type.classreading.ClassMetadataReadingVistor;
import org.springframework.asm.ClassReader;

public class ClassReaderTest {

	@Test 
	public void testGetClassMetaData() throws IOException {
		ClassPathResource reource = new ClassPathResource("org/lispring/service/PetStoService.class");
		ClassReader reader = new ClassReader(reource.getInputStream());
		
		ClassMetadataReadingVistor vistor = new ClassMetadataReadingVistor();
		reader.accept(vistor, ClassReader.SKIP_DEBUG);
		
		Assert.assertFalse(vistor.isAbstract());
		Assert.assertFalse(vistor.isInterface());
		Assert.assertFalse(vistor.isAbstract());
		Assert.assertEquals("org.lispring.service.PetStoService", vistor.getClassName());
		Assert.assertEquals("java.lang.Object", vistor.getSuperClassName());
		Assert.assertEquals(0, vistor.getInterfaces().length);
	}
	
	
	@Test 
	public void testGetAnnotation() throws IOException {
		ClassPathResource reource = new ClassPathResource("org/lispring/service/PetStoService.class");
		ClassReader reader = new ClassReader(reource.getInputStream());
		
		AnnotaitionMetadataReadingVistor vistor = new AnnotaitionMetadataReadingVistor();
		reader.accept(vistor, ClassReader.SKIP_DEBUG);
		
		String anntotation = "org.lispring.stereotype.Component";
		
		AnnotationAttributes attributes = vistor.getAnnotationAttributes(anntotation);
		
		Assert.assertEquals("petSto", attributes.get("value"));
	}
}
