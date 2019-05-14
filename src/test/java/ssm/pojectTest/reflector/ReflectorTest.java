package ssm.pojectTest.reflector;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.ReflectPermission;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.apache.ibatis.reflection.property.PropertyNamer;

/**
 * 测试Reflector这个类中的方法
 * @author msi
 * @date 2019年5月8日
 */
public class ReflectorTest {
	
	private final Class<?> type;
	private final String [] readablePropertyNames;
	private final String [] writeablePropertyNames;
	private final Map<String, Invoker> setMethods = new HashMap<String, Invoker>();
	private final Map<String, Invoker> getMethods = new HashMap<String, Invoker>();
	private final Map<String, Class<?>> setTypes = new HashMap<String, Class<?>>();
	private final Map<String, Class<?>> getTypes = new HashMap<String, Class<?>>();
	private Constructor<?> defaultConstructor;
	
	private Map<String, String> caseInsensitivePropertyMap = new HashMap<String, String>();
	
	public ReflectorTest(Class<?> clazz) {
		type = clazz;
		addDefaultConstructor(clazz);
		addGetMethods(clazz);
		addSetMethods(clazz);
//		addFields(clazz);
		readablePropertyNames = getMethods.keySet().toArray(new String[getMethods.keySet().size()]);
		writeablePropertyNames = setMethods.keySet().toArray(new String [setMethods.keySet().size()] );
		for (String propName : readablePropertyNames) {
			caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
		}
		for (String propName : writeablePropertyNames) {
			caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
		}
	}
	
	private void addDefaultConstructor(Class<?> clazz) {
		Constructor<?>[] consts = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : consts) {
			if (constructor.getParameterTypes().length ==0) {
				try {
					constructor.setAccessible(true);
				} catch (Exception e) {
					
				}
			}
			if (constructor.isAccessible()) {
				this.defaultConstructor = constructor;
			}
		}
	}

	public static void main(String[] args) {
		new ReflectorTest(Demo.class);
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
			addGetMethod(propName, winner);
		}
	}
	
	private void addGetMethod(String name, Method method) {
		if (isValidPropertyName(name)) {
			getMethods.put(name, new MethodInvoker(method));
			Type returnType = TypeParameterResolver.resolveReturnType(method, type);
			getTypes.put(name, typeToClass(returnType));
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
				if (method.getParameterTypes().length == 1) {
					name = PropertyNamer.methodToProperty(name);
					addMethodConflict(conflictingSetters, name, method);
				}
			}
		}
		resolverSetterConflicts(conflictingSetters);
	}
	
	
	private void addMethodConflict(Map<String, List<Method>> conflictingMethods, String name, Method method) {
		List<Method> list = conflictingMethods.get(name);
		if (list == null) {
			list = new ArrayList<Method>();
			conflictingMethods.put(name, list);
		}
		list.add(method);
		
	}
	
	private void resolverSetterConflicts(Map<String, List<Method>> conflictingSetters) {
		for (String propName : conflictingSetters.keySet()) {
			List<Method> setters = conflictingSetters.get(propName);
			Class<?> getterType = getTypes.get(propName);
			Method match = null;
			ReflectionException exception = null;
			for (Method setter : setters) {
				Class<?> paramType = setter.getParameterTypes()[0];
				if (paramType.equals(getterType)) {
					match = setter;
					break;
				}
				if (exception == null) {
					try {
						match = pickBetterSetter(match, setter, propName);
					} catch (ReflectionException e) {
						match = null;
						exception = e;
					}
				}
			}
			if (match == null) {
				throw exception;
			} else {
				addSetMethod(propName, match);
			}
		}
	}

	private Method pickBetterSetter(Method setter1, Method setter2, String property) {
		if (setter1 == null) {
			return setter2;
		}
		Class<?> paramType1 = setter1.getParameterTypes()[0];
		Class<?> paramType2 = setter2.getParameterTypes()[0];
		if (paramType1.isAssignableFrom(paramType2)) {
			return setter2;
			
		} else if (paramType2.isAssignableFrom(paramType1)) {
			return setter1;
		}
		 throw new ReflectionException("Ambiguous setters defined for property '" + property + "' in class '"
			        + setter2.getDeclaringClass() + "' with types '" + paramType1.getName() + "' and '"
			        + paramType2.getName() + "'.");
	}
	
	private void addSetMethod(String name, Method method) {
		if (isValidPropertyName(name)) {
			setMethods.put(name, new MethodInvoker(method));
			Type[] paramTypes = TypeParameterResolver.resolveParamTypes(method, type);
			setTypes.put(name, typeToClass(paramTypes[0]));
		}
	}
	
    private boolean isValidPropertyName(String name) {
		return !(name.startsWith("$") || "serialVersionUID".equals(name) || "class".equals(name));
	}	 

	
	private Class<?> typeToClass(Type src) {
		Class<?> result = null;
		if (src instanceof Class) {
			result = (Class<?>) src;
		} else if (src instanceof ParameterizedType) {
			result = (Class<?>)((ParameterizedType) src).getRawType();
		} else if (src instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) src).getGenericComponentType();
			if (componentType instanceof Class) {
				result = Array.newInstance((Class<?>) componentType, 0).getClass();
			} else {
				Class<?> componentClass = typeToClass(componentType);
				result = Array.newInstance((Class<?>) componentClass, 0).getClass();
			}
		}
		if (result == null) {
			 result = Object.class;
		}
		return result;
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
