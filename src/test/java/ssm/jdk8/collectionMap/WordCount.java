package ssm.jdk8.collectionMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.in;

/**
 * @ClassName WordCount
 * @Description TODO    set
 * @Author cfl
 * @Date 2019/6/14 17:14
 * @Version 1.0
 */
public class WordCount {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(in);
		System.out.println("请输入英文：");
		Set words = tokenSet(scanner.nextLine());
		System.out.printf("不重复单词有%d个：%s%n", words.size(), words);
	}
	
	private static Set tokenSet(String nextLine) {
		String[] tokens = nextLine.split(" ");  //根据空白切割出字符串
		return new HashSet(Arrays.asList(tokens));
	}
	
	
}
