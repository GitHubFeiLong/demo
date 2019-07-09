package ssm.jdk8.collectionMap.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName DequeDemo
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/5 12:39
 * @Version 1.0
 */
public class DequeDemo {
	public static void main (String[] args) {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		deque.addFirst(1);
		deque.addFirst(2);
		deque.add(3);
		deque.offer(4);
		deque.offerFirst(5);
		deque.addFirst(6);
		System.out.println(deque.removeFirst());
		Integer i = deque.peek();
		System.out.println("i = " + i);
		deque.pop();
		deque.poll();
		deque.pollLast();
		System.out.println("deque = " + deque);
	}
}
