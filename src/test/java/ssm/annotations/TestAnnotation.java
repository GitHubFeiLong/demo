package ssm.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
	int id();
	String message();
	// 注解中属性可以有默认值，默认值需要用 default 关键值指定
	String value() default "Hi";
//	还有一种情况。如果一个注解内仅仅只有一个名字为 value 的属性时，
//	应用这个注解时可以直接接属性值填写到括号内。
}
