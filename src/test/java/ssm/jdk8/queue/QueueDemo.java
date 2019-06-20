/**
 * 
 */
package ssm.jdk8.queue;

import java.util.LinkedList;
import java.util.Queue;

/**   
 * @ClassName:  QueueDemo   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年6月19日 下午12:58:26     
 */
public class QueueDemo {
	public static void main(String [] args) {
		Queue requests = new LinkedList();
		offerRequestTo(requests);
		process(requests);
	}

	

	/**   
	 * @Title: offerRequestTo   
	 * @Description: TODO()   
	 * @param:      
	 * @return: void      
	 * @throws   
	 */
	private static void offerRequestTo(Queue requests) {
		// TODO Auto-generated method stub
		requests.offer(null);
		// 仿真将请求添加到队列
		for(int i = 0; i < 6; i++) {
			Request request = new Request() {
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					System.out.println("处理数据：" + Math.random());
				}
				
			};
			// 将请求添加到队列
			requests.offer(request);
		}
	}
	
	/**   
	 * @Title: process  处理队列中的请求 
	 * @Description: TODO()   
	 * @param:      
	 * @return: void      
	 * @throws   
	 */
	private static void process(Queue requests) {
		// TODO Auto-generated method stub
		Request r = (Request) requests.poll();
//		r.execute();
		while(requests.peek() != null) {
			Request request = (Request) requests.poll();
			request.execute();
		}
//		System.out.println(requests.size());
//		requests.peek();
//		System.out.println(requests.size());
	}
	

}
