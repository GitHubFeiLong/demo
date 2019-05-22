package com.ssm.mf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将集合转换成页面展示需要的格式
 * @author msi
 * @date 2019年5月22日
 */
public class DataFormat {
	
	public static <T> List<Map<String,Object>> format(List<T> list){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for (T t : list) {
			Map<String, Object> map = DataFormat.getAttrbutes(t);
			result.add(map);
		}
		return result;
	}
	
	private static Map<String, Object> getAttrbutes(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Field field : fields) {
			String varName = field.getName();
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			try {
				Object o = field.get(obj);
				
				map.put(varName, o);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}
