package ssm.jdk8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName ExceptionWork
 * @Description TODO    异常检查
 * @Author cfl
 * @Date 2019/6/14 14:54
 * @Version 1.0
 */
public class ExceptionWork {
	public static void main(String[] args) {
		/*Object [] objs = {"as", "7"};
		Integer number = (Integer) objs[1];
		System.out.println(number);*/
		try {
			int number = Integer.parseInt(args[0]);
			System.out.println(number++);
		}catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
			System.out.println("请输入数字");
		}
	
	}
	public static void reaFile() {
		try(FileInputStream fis = new FileInputStream("asd")){
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void doSome() throws Exception {
		reaFile();
	}
}
