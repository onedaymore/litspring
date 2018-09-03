package org.lispring.core.io.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.lispring.core.io.Resource;
import org.lispring.util.Assert;

public class FileSystemResource implements Resource {
	
	String path;
	File file;
	
	public FileSystemResource(String path) {
		Assert.notNULL(path, "path must not be null");
		this.path = path;
		file = new File(path);
	}

	public FileSystemResource(File file) {
		this.file = file;
		path = file.getAbsolutePath();
		
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is = new FileInputStream(file);
		return is;
	}

	@Override
	public String getDes() {
		// TODO Auto-generated method stub
		return "file" + file.getAbsolutePath();
	}

}
