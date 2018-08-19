package org.lispring.context.support;

import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;

public class ClassPathXmlApplicationContext extends AbtractApplicationContext {

	public ClassPathXmlApplicationContext(String path) {
		super(path);
	}

	@Override
	protected Resource getResourceFromPath(String path) {
		return new ClassPathResource(path);
	}

}
