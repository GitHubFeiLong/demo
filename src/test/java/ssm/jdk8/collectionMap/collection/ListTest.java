package ssm.jdk8.collectionMap.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListTest
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/1 13:08
 * @Version 1.0
 */
public class ListTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		for(int i = 0; i < 5; i ++){
			list.add(i);
			list2.add(i*10);
		}
		// 索引为2的地方，改为10，其余元素往后移
		list.add(2,10);
		list.addAll(list2);
		list.addAll(5, list2);
		System.out.println(list);
		System.out.println(list2);
//		list2.remove(new Integer(30));
		list2.remove(2);
		list2.set(3,300);
		System.out.println(list2);
		
		
	}
}
