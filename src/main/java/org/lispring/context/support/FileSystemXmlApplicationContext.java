package org.lispring.context.support;

import org.lispring.core.io.Resource;
import org.lispring.core.io.support.FileSystemResource;

public class FileSystemXmlApplicationContext extends AbtractApplicationContext {

	public FileSystemXmlApplicationContext(String path) {
		super(path);
	}

	@Override
	protected Resource getResourceFromPath(String path) {
		return new FileSystemResource(path);
	}



}
