/**
 * 
 */
package ssm.jdk8;

import static java.lang.System.*;

import java.util.Scanner;

/**   
 * @ClassName:  StringTest   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年5月29日 下午1:15:18     
 */
public class StringTest {
	public static void main(String[] args) {
		String name = "19";
		System.out.println("Integer:" + Integer.parseInt(name));
		StringTest.sumNumber();
	}
	
	private static void  sumNumber() {
		System.out.println("请输入数字");
		Scanner scan = new Scanner(in);
		long input = 0L;
		long sum = 0L;
		do {
			System.out.print("输入数字：");
			input = Long.parseLong(scan.nextLine());
			sum += input;
		} while (input != 0);
		System.out.println("输入数字总和是：" + sum);
		
	}

}
