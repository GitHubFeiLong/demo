/**
 * 
 */
package com.ssm.mf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**   
 * @ClassName:  ConfigResolver   解析配置文件config.properties
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年5月17日 下午3:09:02     
 */
public class ConfigResolver {
	
	/**
	  * 根据传参获取配置文件中的对应属性的值
	 * @Title: parseFile   
	 * @Description: TODO()   
	 * @param:      
	 * @return: String      
	 * @throws
	 */
	public static String  parseFile(String field) {
		Resource resource = new ClassPathResource("config.properties");
		InputStream input = null;
		try {
			input = resource.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(field);
	}

}
