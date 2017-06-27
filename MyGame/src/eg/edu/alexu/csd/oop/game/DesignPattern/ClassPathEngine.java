package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.circutOfPlates.object.ShapeIF;



public class ClassPathEngine {

	private static final Logger logger = LoggerFactory.getLogger(ClassPathEngine.class);
	private List<Class<? extends ShapeIF>> list = new LinkedList<Class<? extends ShapeIF>>();

	public ClassPathEngine() {
		try {
			String classPath = System.getProperty("java.class.path");
			String[] paths = classPath.split(System.getProperty("path.separator"));
			for (String path : paths) {
				if (path.endsWith(".jar")) {
					JarInputStream j = new JarInputStream(new FileInputStream(new File(path)));
					JarEntry dummy = j.getNextJarEntry();
					while (dummy != null) {
						String name = dummy.getName();
						check(name);
						dummy = j.getNextJarEntry();
					}
					j.close();
				} else if (path.endsWith(".class")) {
					check(path);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error in ClassPathEngine()");
		}
	}

	public List<Class<? extends ShapeIF>> getList() {
		try {
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Faild in getList()");
			
		}
	}

	@SuppressWarnings("unchecked")
	private void check(String name) {
		try {
			if (name.endsWith(".class") && !name.contains("log4j")) {
				String className = name.substring(0, name.length() - 6);
				className = className.replace('/', '.');
				Class<?> c = Class.forName(className);
				if (ShapeIF.class.isAssignableFrom(c) && !c.isInterface()) {
					list.add((Class<? extends ShapeIF>) c);
					logger.debug("class name :"+name);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("class name throw exp. :"+name);
			throw new RuntimeException("Failed in check()");
		}
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public List<Class<? extends ShapeIF>> loadPlugins(String path) {
		List<Class<? extends ShapeIF>> list = new LinkedList<Class<? extends ShapeIF>>();
		JarFile jarFile;
		try {
			jarFile = new JarFile(path);
			Enumeration<?> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + path + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (e.hasMoreElements()) {
				JarEntry je = (JarEntry) e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				// -6 because of .class
				String className = je.getName().substring(0,je.getName().length() - 6);
				className = className.replace('/', '.');
				Class<?> c = cl.loadClass(className);
				if (ShapeIF.class.isAssignableFrom(c) && !c.isInterface()) {
					logger.info("class name :"+c.getName());
					list.add((Class<? extends ShapeIF>) c);
				}
			}
			return list;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("RuntimeException Failed in loadPlugins");
			throw new RuntimeException("Failed in loadPlugins()");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("RuntimeException Failed in loadPlugins");
			throw new RuntimeException("Failed in loadPlugins()");
		}
	}
}
