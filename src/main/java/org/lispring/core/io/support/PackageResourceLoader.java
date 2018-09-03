package org.lispring.core.io.support;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.lispring.core.io.Resource;
import org.lispring.util.Assert;
import org.lispring.util.ClassUtils;

public class PackageResourceLoader {
	
	private final ClassLoader classLoader;
	
	public PackageResourceLoader() {
		classLoader = ClassUtils.getDefaultClassLoader();
	}
	
	public PackageResourceLoader(ClassLoader classLoader) {
		Assert.notNULL(classLoader, "classLoader must not be null");
		this.classLoader = classLoader;
	}
	
	public ClassLoader getClassLoader(){
		return classLoader;
	}

	public Resource[] getResources(String basePackage) {
		
		Assert.notNULL(basePackage, "basePackage must not be null");

		String loacation = ClassUtils.convertClassNameToResourcePath(basePackage);
		ClassLoader cl = getClassLoader();
		
		URL url = cl.getResource(loacation);
		File rootDir = new File(url.getFile());
		
		Set<File> mathingFiles = retriveMathingFiles(rootDir);
		
		Resource [] result = new Resource[mathingFiles.size()];
		int i = 0;
		for (File file : mathingFiles) {
			result[i++] = new FileSystemResource(file);
		}
		
		return result;
	}

	private Set<File> retriveMathingFiles(File rootDir) {
		if (!rootDir.exists()) {
			return Collections.emptySet();
		}
		if (!rootDir.isDirectory()) {
			return Collections.emptySet();
		}
		
		if (!rootDir.canRead()) {
			return Collections.emptySet();
		}
		
		Set<File> result = new LinkedHashSet<File>(8);
		doRetriveMathingFiles(rootDir, result);
		return result;
	}

	private void doRetriveMathingFiles(File rootDir, Set<File> result) {
		File [] dirtContents = rootDir.listFiles();
		if (dirtContents == null) {
			return;
		}
		for (File file : dirtContents) {
			if (file.isDirectory()) {
				if (!file.canRead()) {
					
				} else {
					doRetriveMathingFiles(file, result);
				}
			} else {
				result.add(file);
			}
		}
		
	}

}
