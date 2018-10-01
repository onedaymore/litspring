package org.lispring.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;




public class ReflectionUtils {
	



	/**
	 * Make the given field accessible, explicitly setting it accessible if
	 * necessary. The {@code setAccessible(true)} method is only called
	 * when actually necessary, to avoid unnecessary conflicts with a JVM
	 * SecurityManager (if active).
	 * @param field the field to make accessible
	 * @see java.lang.reflect.Field#setAccessible
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) ||
				!Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
				Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}
	
	public static Method findMethod(Class<?> clazz, String name,  Class<?>... paramTypes) {
		Assert.notNULL(clazz, "Class must not be null");
		Assert.notNULL(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : getDeclaredMethods(searchType));
			for (Method method : methods) {
				if (name.equals(method.getName()) &&
						(paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	
	private static Method[] getDeclaredMethods(Class<?> clazz) {
		Assert.notNULL(clazz, "Class must not be null");
		Method[] result = null;
		if (result == null) {
			try {
				Method[] declaredMethods = clazz.getDeclaredMethods();
				List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
				if (defaultMethods != null) {
					result = new Method[declaredMethods.length + defaultMethods.size()];
					System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
					int index = declaredMethods.length;
					for (Method defaultMethod : defaultMethods) {
						result[index] = defaultMethod;
						index++;
					}
				}
				else {
					result = declaredMethods;
				}
				
			}
			catch (Throwable ex) {
				throw new IllegalStateException("Failed to introspect Class [" + clazz.getName() +
						"] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
			}
		}
		return result;
	}
	
	private static List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
		List<Method> result = null;
		for (Class<?> ifc : clazz.getInterfaces()) {
			for (Method ifcMethod : ifc.getMethods()) {
				if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
					if (result == null) {
						result = new LinkedList<>();
					}
					result.add(ifcMethod);
				}
			}
		}
		return result;
	}
}
