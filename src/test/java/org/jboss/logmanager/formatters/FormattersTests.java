package org.jboss.logmanager.formatters;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.testng.annotations.Test;

@Test
public class FormattersTests {
	
	public void getJarName_jar() throws MalformedURLException {
		String classResourceName = "org/alib/foo/Bar.class";
		URL resource = new URL(null, "jar:file:/D:/Java/SomeDir/alib-3.14.jar!/" + classResourceName, new DummyURLStreamHandler());
		
		assertEquals(Formatters.getJarName(resource, classResourceName), "alib-3.14.jar");
	}

	public void getJarName_module() throws MalformedURLException {
		String classResourceName = "org/alib/foo/Bar.class";
		URL resource = new URL(null, "module:blib-3.14.jar", new DummyURLStreamHandler());
		
		assertEquals(Formatters.getJarName(resource, classResourceName), "blib-3.14.jar");
	}

	public void getJarName_vfs() throws MalformedURLException {
		String classResourceName = "org/alib/foo/Bar.class";
		URL resource = new URL(null, "vfs:/D:/Java/jboss-as-7.1.1.Final/standalone/deployments/myapp.war/WEB-INF/lib/clib-3.1.4.jar", new DummyURLStreamHandler());
		
		assertEquals(Formatters.getJarName(resource, classResourceName), "clib-3.1.4.jar");
	}

	public void getJarName_other() throws MalformedURLException {
		String classResourceName = "org/alib/foo/Bar.class";
		URL resource = new URL(null, "foo://bar/" + classResourceName, new DummyURLStreamHandler());
		
		assertEquals(Formatters.getJarName(resource, classResourceName), "Bar.class");
	}
	
}

class DummyURLStreamHandler extends URLStreamHandler {

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		throw new UnsupportedOperationException();
	}
}