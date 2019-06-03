package ssm.annotations;

import java.lang.reflect.Method;

public class NoBugTest {
	public static void main(String [] args) {
		Class<?> clazz = NoBug.class;
		Method [] methods = clazz.getDeclaredMethods();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(TestNoBug.class)) {
				System.out.println(methods[i].getName());
				methods[i].setAccessible(true);
				try {
					methods[i].invoke(clazz.newInstance(), args);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					sb.append(clazz.getSimpleName()+"."+methods[i].getName());
					sb.append(e.getMessage());
					e.printStackTrace();
				} 
			}
		}
		
		System.out.println(sb.toString());
	}

}
