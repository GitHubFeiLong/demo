package ssm.annotations;

import java.lang.annotation.Annotation;

public class TestClient {
	public static void main(String[] args) {
		Class clazz = TestAnnotation.class;
		boolean boo = TestDemo.class.isAnnotationPresent(clazz);
		System.out.println(boo);
		System.out.println("TestDemo类："+ (boo?"有":"没有") + "使用注解" + clazz.getName());
		if (boo) {
			Annotation cAnno = TestDemo.class.getAnnotation(TestAnnotation.class);
			System.out.println(((TestAnnotation) cAnno).id());
			System.out.println(((TestAnnotation) cAnno).message());
		}
		
		
	}
}
