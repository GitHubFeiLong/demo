package com.ssm.mf.utils;

/**
 * 
 */

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通过集合，和泛型全称，返回集合中相同字段的求和，返回一个Number[],Number[]的索引就是集合泛型对象字段的索引。
 * @ClassName: SumField
 * @Description:TODO	
 * @author: cfl
 * @date: 2019年6月5日 下午1:13:30
 */
public class SumFieldUtil {
	/**
	 * 将结果计算并赋值给对象
	 * @Title: getObject   
	 * @Description: TODO()   
	 * @param:      
	 * @return: Object      
	 * @throws
	 */
	public static <T> Object getObject(List<T> list, Object obj) {
		try {
			Number [] nums = sum( list, obj); 
			Class<?> clazz = obj.getClass();
			Object o = clazz.newInstance();
			Field [] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// 如果不是Number类型的字段，跳过
				if (!Number.class.isAssignableFrom(fields[i].getType())) {
					continue;
				}
				// 序列化id跳过加和
				if (fields[i].getName().equalsIgnoreCase("serialVersionUID")){
					continue;
				}
				fields[i].setAccessible(true);
				// 默认nums中存放的Integer类型。
				if (Double.class.isAssignableFrom(fields[i].getType())) {
					fields[i].set(o, Double.parseDouble(nums[i].toString()));
					continue;
				}
				if (Long.class.isAssignableFrom(fields[i].getType())) {
					fields[i].set(o, Long.parseLong(nums[i].toString()));
					continue;
				}
				fields[i].set(o, nums[i]);
			}
			return o;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @Title: sum   
	 * @Description: TODO()   
	 * @param:   list:需要计算的集合   
	 * @param:	 clazzName：list的泛型
	 * @return: Number[] list的泛型类中由Number类型或子类型修饰的字段求和。
	  * 字段类型为Number的计算值，不是Number类型的字段，该字段在泛型类的Files[]的索引为index，那么
	 *对应位置的Number[index] = null;    
	 * @throws
	 */
	private static <T> Number[] sum(List<T> list, Object object) throws Exception {
		Class<?> clazz = object.getClass();
		// 根据类名，实例化一个对象，用于存储数据总和
		Object obj = object;
		
		Field[] clazzFileds = clazz.getDeclaredFields();
		Number [] nums = new Number[clazzFileds.length];
		// 初始每个元素为0
		for (int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
		// 循环集合，让nums数组对应字段的值累加
		for (T t : list) {
			clazzFileds = clazz.getDeclaredFields();
			// 对象的属性类型
			for (int i = 0; i < clazzFileds.length; i++) {
				// System.out.println(clazzFileds[i].getName());
				clazzFileds[i].setAccessible(true);
				
				// 如果不是Number类型的字段，跳过
				if (!Number.class.isAssignableFrom(clazzFileds[i].getType())) {
					nums[i] = null;
					continue;
				} 
				// 字段对应的getter()的值
				Number n =  (Number)clazzFileds[i].get(t);
				if (n != null) {
					// 相当于 nums[i] += n;
					nums[i] = SumFieldUtil.getValue(n, nums[i], getType(n));
				}
				
			}	
		}
		
		return nums;
	}
	
	

	/**
	 * 将Number类型转换为基本数据类型 @Title: getType @Description: TODO() @param: @return:
	 * Type @throws
	 */
	private static Type getType(Number n) {
		Class<?> clazz = n.getClass();
		if (Double.class.isAssignableFrom(clazz)) {
			return n.getClass();
		} else if (Float.class.isAssignableFrom(clazz)) {
			return Float.class;
		} else if (Long.class.isAssignableFrom(clazz)) {
			return Long.class;
		} else if (Integer.class.isAssignableFrom(clazz)) {
			return Integer.class;
		} else if (Short.class.isAssignableFrom(clazz)) {
			return Short.class;
		} else if (Byte.class.isAssignableFrom(clazz)) {
			return Byte.class;
		} else {
			throw new RuntimeException("com.mf.invo.utils.SumField.getType(Number)的Number无效");
		}
	}

	/**
	 * 将Number类型的对象判断为基本类型，然后再做加法 @Title: getValue @Description:
	 * TODO() @param: @return: Object @throws
	 */
	private static Number getValue(Number n, Number n2, Type type) throws ClassNotFoundException {
		Class<?> clazz = Class.forName(type.getTypeName());
		String n2Name = getType(n2).getTypeName();

		if (Double.class.isAssignableFrom(clazz)) {
			double n1 = n.doubleValue();
			return sum(n1, n2, n2Name);
		} else if (Float.class.isAssignableFrom(clazz)) {
			float n1 = n.floatValue();
			return sum(n1, n2, n2Name);
		} else if (Long.class.isAssignableFrom(clazz)) {
			long n1 = n.longValue();
			return sum(n1, n2, n2Name);
		} else if (Integer.class.isAssignableFrom(clazz)) {
			int n1 = n.intValue();
			return sum(n1, n2, n2Name);
		} else if (Short.class.isAssignableFrom(clazz)) {
			short n1 = n.shortValue();
			return sum(n1, n2, n2Name);
		} else if (Byte.class.isAssignableFrom(clazz)) {
			byte n1 = n.byteValue();
			return sum(n1, n2, n2Name);
		}
		throw new RuntimeException("com.mf.invo.utils.SumField.getValue(Number, Number, Type)错误");
	}

	// double
	private static Number sum(double n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

	// float
	private static Number sum(float n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

	// long
	private static Number sum(long n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

	// int
	private static Number sum(int n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

	// short
	private static Number sum(short n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

	// byte
	private static Number sum(byte n1, Number n2, String n2Name) {
		switch (n2Name) {
		case "java.lang.Double":
			return n1 + (Double) (n2);
		case "java.lang.Float":
			return n1 + (Float) (n2);
		case "java.lang.Long":
			return n1 + (Long) (n2);
		case "java.lang.Integer":
			return n1 + (Integer) (n2);
		case "java.lang.Short":
			return n1 + (Short) (n2);
		case "java.lang.Byte":
			return n1 + (Byte) (n2);
		}
		return null;
	}

}
