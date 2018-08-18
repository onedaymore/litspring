package org.lispring.test.v1;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.core.io.support.FileSystemResource;

public class ResourceTest {

	@Test
	public void testClassPathResource() {
		Resource r = new ClassPathResource("beans.xml");
		InputStream in = null;
		try{
			in = r.getInputStream();
			Assert.assertNotNull(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testFileSystemResource() {
		Resource r = new FileSystemResource("F:\\work\\leave\\litSpring\\src\\test\\resources\\beans.xml");
		InputStream in = null;
		try{
			in = r.getInputStream();
			Assert.assertNotNull(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
