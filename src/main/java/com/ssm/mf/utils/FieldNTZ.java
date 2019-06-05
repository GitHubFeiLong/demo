/**
 * 
 */
package com.ssm.mf.utils;

import java.lang.reflect.Field;

/**   
 * @ClassName:  FieldNTZ   
 * @Description:TODO   对象属性值为null，改为0
 * @author: cfl
 * @date:  2019年5月31日 下午12:32:35     
 */
public class FieldNTZ {
	
	public static<T> void change(T t) {
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				// f字段的类型是Number的子类
				if (Number.class.isAssignableFrom(f.getType())) {
					if (f.get(t) == null) {
						f.set(t, 0);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
