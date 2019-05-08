package ssm.pojectTest.reflector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试Reflector这个类中的方法
 * @author msi
 * @date 2019年5月8日
 */
public class ReflectorTest {

	public static void main(String[] args) {
		ReflectorTest r = new ReflectorTest();
		Class<Demo> type = Demo.class;
		 Constructor<?>[] constructors = type.getDeclaredConstructors();
		 System.out.println(Arrays.toString(constructors));
		 for (Constructor<?> constructor : constructors) {
			 if (constructor.getParameterTypes().length == 0) {
				 System.out.println("无参构造函数");
				 if (canAccessPrivateMethods()) {
					try {
						constructor.setAccessible(true);
					} catch (Exception e) {
						// Ignored. This is only a final precaution, nothing we can do.
					}
				 }
				 if (constructor.isAccessible()) {
			          System.out.println("h:"+constructor);
			        }
			 }
		 }
		 System.out.println("设置默认构造方法完毕");
		
		 Map<String, List<Method>> conflictingGetters = new HashMap<>();
		 Method[] methods = r.getClassMethods(type);
		
	}

	private static boolean canAccessPrivateMethods() {
	    try {
	      SecurityManager securityManager = System.getSecurityManager();
	      if (null != securityManager) {
	        securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
	      }
	    } catch (SecurityException e) {
	      return false;
	    }
	    return true;
	}
	private Method[] getClassMethods(Class<?> cls) {
		Map<String, Method> uniqueMethods = new HashMap<>();
		Class<?> currentClass = cls;
		while (currentClass != null && currentClass != Object.class) {
			addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());
			
			Class<?>[] interfaces = currentClass.getInterfaces();
			for (Class<?> anInterface : interfaces) {
				addUniqueMethods(uniqueMethods,anInterface.getMethods());
			}
			currentClass = currentClass.getSuperclass();
		}
		
		Collection<Method> methods = uniqueMethods.values();
		
		return methods.toArray(new Method[methods.size()]);
	}
	

	
	private void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] methods) {
		for (Method currentMethod : methods) {
			if (!currentMethod.isBridge()) {
				String signature = getSignature(currentMethod);
				if (!uniqueMethods.containsKey(signature)) {
					if (canAccessPrivateMethods()) {
						try {
							currentMethod.setAccessible(true);
						} catch(Exception e) {
							
						}
					}
					
					uniqueMethods.put(signature, currentMethod);
				}
			}
		}
		
	}
	
	private String getSignature (Method method) {
		StringBuilder sb = new StringBuilder();
		Class<?> returnType = method.getReturnType();
		if (returnType != null) {
			sb.append(returnType.getName()).append('#');
		}
		sb.append(method.getName());
		Class<?>[] parameters = method.getParameterTypes();
		for(int i = 0; i < parameters.length; i++) {
			if (i == 0) {
				sb.append(':');
			} else {
				sb.append(',');
			}
			sb.append(parameters[i].getName());
		}
		return sb.toString();
	}
	
}
