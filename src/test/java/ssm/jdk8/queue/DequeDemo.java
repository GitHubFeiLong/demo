/**
 * 
 */
package ssm.jdk8.queue;

import java.util.Deque;
import java.util.LinkedList;
import static java.lang.System.out;

/**   
 * @ClassName:  DequeDemo   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年6月19日 下午1:10:49     
 */
public class DequeDemo {
	public static void main(String [] args) {
		Deque deque = new LinkedList();
		deque.offer(1);
		System.out.println(deque);
		deque.offerFirst(2);
		out.println(deque);
		deque.offer(3);
		out.println(deque);
		deque.offerLast(4);
		out.println(deque);
		
		Object o = deque.poll();
		out.println(o);
		out.println(deque);
	}

}
