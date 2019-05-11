package ssm.pojectTest.reflector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.property.PropertyNamer;

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
					System.out.println("h:" + constructor);
				}
			}
		}
		System.out.println("设置默认构造方法完毕");

		 Map<String, List<Method>> conflictingGetters = new HashMap<>();
		 Method[] methods = r.getClassMethods(type);
		 r.addGetMethods(Demo.class);
		
	}

	/**
	 * 添加get方法
	 * @param cls
	 */
	private void addGetMethods(Class<?> cls) {
		Map<String, List<Method>> conflictingGetters = new HashMap<String, List<Method>>();
		Method [] methods = getClassMethods(cls);
		for (Method method : methods) {
			// 有参数跳过
			if (method.getParameterTypes().length > 0) {
				continue;
			}
			String name = method.getName();
			// 方法名以get开头或is开头，方法名长度分别大于3或2
			if ((name.startsWith("get")&& name.length() > 3) || (name.startsWith("is") && name.length() > 2)) {
				name = PropertyNamer.methodToProperty(name);
				addMethodConflict(conflictingGetters, name, method);
			}
		}
		resolverGetterConflicts(conflictingGetters);
	}
	
	
	private void resolverGetterConflicts(Map<String, List<Method>> conflictingGetters) {
		for (Entry<String, List<Method>> entry : conflictingGetters.entrySet()) {
			Method winner = null;
			String propName = entry.getKey();
			for (Method candidate : entry.getValue()) {
				if (winner == null) {
					winner = candidate;
					continue;
				}
				Class<?> winnerType = winner.getReturnType();
				Class<?> candidateType = candidate.getReturnType();
				if (candidateType.equals(winnerType)) {
					if (!boolean.class.equals(candidateType)) {
						throw new ReflectionException(
				                "Illegal overloaded getter method with ambiguous type for property "
				                    + propName + " in class " + winner.getDeclaringClass()
				                    + ". This breaks the JavaBeans specification and can cause unpredictable results.");
					} else if (candidate.getName().startsWith("is")) {
						winner = candidate;
					}
				} else if(candidateType.isAssignableFrom(winnerType)) {
					
				} else if (winnerType.isAssignableFrom(candidateType)) {
					winner = candidate;
				} else {
					 throw new ReflectionException(
				              "Illegal overloaded getter method with ambiguous type for property "
				                  + propName + " in class " + winner.getDeclaringClass()
				                  + ". This breaks the JavaBeans specification and can cause unpredictable results.");
				       
				}
				
			}
			
		}
	}
	
	/**
	 * 添加set方法
	 * @param cls
	 */
	private void addSetMethods(Class<?> cls) {
		Map<String, List<Method>> conflictingSetters = new HashMap<String, List<Method>>();
		Method [] methods = getClassMethods(cls);
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("set") && name.length() > 3) {
				name = PropertyNamer.methodToProperty(name);
				addMethodConflict(conflictingSetters, name, method);
			}
		}
		//resolverSetterConflicts(conflictingSetters);
	}
	
	private void addMethodConflict(Map<String, List<Method>> conflictingMethods, String name, Method method) {
		List<Method> list = conflictingMethods.get(name);
		if (list == null) {
			list = new ArrayList<Method>();
			conflictingMethods.put(name, list);
		}
		list.add(method);
		
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
	
	/**
	 * 获取类对象的所有方法数组
	 * @param cls
	 * @return
	 */
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
