package ssm.jdk8;

import java.util.Scanner;

import static java.lang.System.in;
/**
 * @ClassName AutoCloseableDemo
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/14 14:41
 * @Version 1.0
 */
public class AutoCloseableDemo {
	public static void main(String[] args) {
		String str = null;
		try(Scanner scanner = new Scanner(in)){
			int num = 0;
			while(!"0".equals(str = scanner.nextLine())){
				System.out.println("输入数字：" + str);
				num += Integer.parseInt(str);
				
			}
			System.out.println("结果为：" + num);
		}
	}
}
