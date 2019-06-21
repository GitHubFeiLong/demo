package ssm.jdk8.collectionMap.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Students
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/14 17:29
 * @Version 1.0
 */
public class Students {
	public static void main(String[] args) {
		Set students = new HashSet();
		students.add(new Student("Justin", "B835031"));
		students.add(new Student("Monica", "B835031"));
		students.add(new Student("Justin", "B835031"));
		System.out.println(students);
	}
}
