package ssm.jdk8.collectionMap.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName QueueDemo
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/19 8:52
 * @Version 1.0
 */
public class QueueDemo {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(1);
		queue.add(2);
		System.out.println(((LinkedList<Integer>) queue).getFirst());
		System.out.println(((LinkedList<Integer>) queue).getLast());
		System.out.println(queue);
		
	}
}
