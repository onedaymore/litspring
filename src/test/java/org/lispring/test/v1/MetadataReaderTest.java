package org.lispring.test.v1;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.core.annotation.AnnotationAttributes;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.core.type.AnnotationMetadata;
import org.lispring.core.type.ClassMetadata;
import org.lispring.core.type.MetadataReader;
import org.lispring.core.type.classreading.SimpleMetadataReader;
import org.lispring.stereotype.Component;

public class MetadataReaderTest {

	@Test
	public void testGetAnnotation() throws IOException {
		ClassPathResource reource = new ClassPathResource("org/lispring/service/PetStoService.class");
		
		MetadataReader reader = new SimpleMetadataReader(reource);
		
		ClassMetadata cm = reader.getClassMetadata();
		AnnotationMetadata amd = reader.getAnnotationMetadata();
		
		String anntotation = Component.class.getName();
		
		Assert.assertTrue(amd.hasAnnotation(anntotation));
		
		AnnotationAttributes attributes = amd.getAnnotationAttributes(anntotation);
		Assert.assertEquals("petSto", attributes.get("value"));
		
		Assert.assertFalse(amd.isAbstract());
		Assert.assertFalse(amd.isInterface());
		Assert.assertFalse(amd.isFinal());
		Assert.assertEquals("org.lispring.service.PetStoService", amd.getClassName());
	
	}
}
