package org.lispring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.PackageResourceLoader;

public class PackageResourceLoaderTest {
	
	@Test
	public void testGetResource() {
		PackageResourceLoader loader = new PackageResourceLoader();
		Resource [] resources = loader.getResources("org.lispring.dao");
		Assert.assertEquals(2, resources.length);
	}

}
