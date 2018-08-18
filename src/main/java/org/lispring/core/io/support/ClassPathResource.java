package org.lispring.core.io.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.lispring.core.io.Resource;
import org.lispring.util.ClassUtils;

public class ClassPathResource implements Resource {
	
	String path;
	ClassLoader loader;

	public ClassPathResource(String path) {
		this(path, null);
	}
	
	public ClassPathResource(String path, ClassLoader loader) {
		this.path = path;
		this.loader = loader != null ? loader : ClassUtils.getDefaultClassLoader();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is = loader.getResourceAsStream(path);
		
		if (is ==null) {
			throw new FileNotFoundException(path + "cannot be opened");
		}
		
		return is;
	}

	@Override
	public String getDes() {
		// TODO Auto-generated method stub
		return null;
	}

}
