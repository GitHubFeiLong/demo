package ssm.utils;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/9 12:47
 * @Version 1.0
 */
public class Demo {
	Integer age;
	Integer length;
	Double weight;
	String name;
	
	public Demo () {
	}
	
	public Demo (int age, int length, double weight, String name) {
		this.age = age;
		this.length = length;
		this.weight = weight;
		this.name = name;
	}
	
	@Override
	public String toString () {
		return "Demo{" +
				"age=" + age +
				", length=" + length +
				", weight=" + weight +
				", name='" + name + '\'' +
				'}';
	}
}
