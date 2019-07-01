package ssm.jdk8.collectionMap;

import java.util.*;

/**
 * @ClassName Work
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/1 12:54
 * @Version 1.0
 */
public class Work {
	public static void main(String[] args) {
		foreach(new HashSet());
	}
	
	private static void foreach(Collection collection) {
		Iterator elements = collection.iterator();
		while(((Iterator) elements).hasNext()){
			System.out.println(elements.next());
		}
	}
}
