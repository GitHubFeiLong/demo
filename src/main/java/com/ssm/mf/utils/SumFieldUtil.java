/**
 * 
 */
package com.ssm.mf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 使用该工具类条件：类的成员变量不能使用基本变量，要使用包装类，并且拥有无参构造器，
 * 通过集合，和泛型全称，返回集合中相同字段的求和，返回一个Number[],Number[]的索引就是集合泛型对象字段的索引。
 * @ClassName: SumField
 * @Description:TODO	
 * @author: cfl
 * @date: 2019年6月5日 下午1:13:30
 */
public class SumFieldUtil<E> {
	/**
	 * 饿汉式
	 */
	private static SumFieldUtil sfu = new SumFieldUtil<>();
	
	private SumFieldUtil () {
	}
	
	public static SumFieldUtil getInstance(){
		return sfu;
	}
	/**
	 * 声明一个集合元素类型的对象，存储nums数组的数据到对象的成员变量中
	 * @param:    list: 需要求和的集合；
	 * @Param:    obj :list中元素类型
	 * @return:   o: 合计后的对象
	 * @throws
	 */
	public  <T> E getObject(List<T> list, Object obj) {
		try {
			// 获取list元素对象所有字段的合计值。
			// nums[] 的下标对应 fields[] 的下标
			Number [] nums = sum( list, obj);
			// 创建一个对象，用来返回客户端。
			Class<?> clazz = obj.getClass();
			Object o = clazz.newInstance();
			// 获取所有的成员变量。
			Field [] fields = clazz.getDeclaredFields();
			
			// 循环给 o 对象中的每个字段设置值。
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
			// 返回结果
			return (E)o;
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
	 * 更改Number[] nums 的值（循环累加阶段）
	 * @Description: TODO()
	 * @param:   list:需要计算的集合   
	 * @param:	 obj :list中元素类型
	 * @return:    nums： Number[]。
	 */
	private static <T> Number[] sum(List<T> list, Object object) throws Exception {
		// 1. 根据对象.getClass(); 获取object的Class对象
		Class<?> clazz = object.getClass();
		// 2. 获取Class对象的所有字段，返回一个Field[]
		Field[] clazzFileds = clazz.getDeclaredFields();
		// 3. 声明一个Number[]，和 clazzFileds 一一对应
		Number [] nums = new Number[clazzFileds.length];
		// 4. 初始Number[]元素设置位0（默认Null）
		for (int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
		// 5. 循环集合，让 nums 对应字段的值累加
		for (T t : list) {
			/// 上面已经获取了集合元素类型的field[], 这里不需要？
///			clazzFileds = clazz.getDeclaredFields();
			// 6. 对象的属性类型
			for (int i = 0; i < clazzFileds.length; i++) {
				// System.out.println(clazzFileds[i].getName());
				// 调用filed.setAccessible(true)，就能访问 private修饰的成员变量
				clazzFileds[i].setAccessible(true);
				
				// 判断成员变量类型，如果不是Number类型的字段，将nums[] 对应的元素设置null 并跳过此次循环。
				if (!Number.class.isAssignableFrom(clazzFileds[i].getType())) {
					nums[i] = null;
					continue;
				} 
				// t对象的字段对应的getter()的值
				Number n =  (Number)clazzFileds[i].get(t);
				// 将nums 的值加上 n
				if (n != null) {
					// 相当于 nums[i] += n;
					nums[i] = SumFieldUtil.getValue(n, nums[i]);
				}
			}
		}
		
		return nums;
	}
	
	

	/**
	 * 将Number类型转换为基本数据类型
	 * @param:      n : Number类型的变量，这里是集合元素某个字段的值
	 * Type @throws
	 */
//	private static Type getType(Number n) {
//		Class<?> clazz = n.getClass();
//		// 判定 Double.class 对象所表示的类或接口与指定的 clazz 参数所表示的类或接口
//		// 是否相同，或是否是其超类或超接口。如果是则返回 true；否则返回 false.
//		if (true) return n.getClass();
//		if (Double.class.isAssignableFrom(clazz)) {
//			return Double.class;
//		} else if (Float.class.isAssignableFrom(clazz)) {
//			return Float.class;
//		} else if (Long.class.isAssignableFrom(clazz)) {
//			return Long.class;
//		} else if (Integer.class.isAssignableFrom(clazz)) {
//			return Integer.class;
//		} else if (Short.class.isAssignableFrom(clazz)) {
//			return Short.class;
//		} else if (Byte.class.isAssignableFrom(clazz)) {
//			return Byte.class;
//		} else {
//			throw new RuntimeException("com.mf.invo.utils.SumField.getType(Number)的Number无效");
//		}
//	}

	/**
	 * 获取 n 和 n2 的和，将结果转换成Number类型
	 * TODO()
	 * @Param   n:某一个对象的某一个字段的值
	 * @Param   n2:某一个字段对应的Number[] 中的的值
	 * @Return  返回一个Number对象
	 */
	private static Number getValue(Number n, Number n2) throws ClassNotFoundException {
		Class<?> clazz = n.getClass();
		String n2Name = n2.getClass().getTypeName();
//		String n2Name = getType(n2).getTypeName();

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

	/////////////////重载方法num() 根据参数类型不同选择不同的方法进行处理/////////////////////
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
