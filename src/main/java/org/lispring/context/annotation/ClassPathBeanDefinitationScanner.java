package org.lispring.context.annotation;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.lispring.beans.BeanDefinition;
import org.lispring.beans.factory.support.BeanDefinitionRegistry;
import org.lispring.beans.factory.support.BeanNameGenerator;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.PackageResourceLoader;
import org.lispring.core.type.MetadataReader;
import org.lispring.core.type.classreading.SimpleMetadataReader;
import org.lispring.stereotype.Component;
import org.lispring.util.StringUtils;

public class ClassPathBeanDefinitationScanner {

	private final BeanDefinitionRegistry registry;
	
	private PackageResourceLoader resourceLoader = new PackageResourceLoader();
	
	
	private BeanNameGenerator beanNameGenerator = new AnnotaotionBeanNameGenerator();
	
	public ClassPathBeanDefinitationScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	public Set<BeanDefinition> doScan(String basePackage)  {
		String [] basePackages = StringUtils.tokenizeToStringArray(basePackage, ",");
		
		Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
		for (String pkage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(pkage);
			for (BeanDefinition candidate : candidates) {
				candidates.add(candidate);
				registry.registryBeanDefinition(candidate.getID(), candidate);
			}
		}
		
		return beanDefinitions;
		
	}

	private Set<BeanDefinition> findCandidateComponents(String pkage) {
		Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
		
		Resource [] resources = resourceLoader.getResources(pkage);
		
		for (Resource resource : resources) {
			try {
				MetadataReader metadataReader = new SimpleMetadataReader(resource);
				
				if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
					ScannerGenericBeanDefinition sbd = new ScannerGenericBeanDefinition(metadataReader.getAnnotationMetadata());
					String beanName = beanNameGenerator.generatorBeanName(sbd, registry);
					sbd.setID(beanName);
					beanDefinitions.add(sbd);
				}
				
				
			} catch (Throwable e) {

			} 
		}
		
		return beanDefinitions;
	}

}
