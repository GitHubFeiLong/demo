package ssm.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation1 {
	//最后，还需要注意的一种情况是一个注解没有任何属性。比如
	//那么在应用这个注解的时候，括号都可以省略。
}
