package ssm.jdk8.collectionMap;

/**
 * @ClassName SimpleLinkedList
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/14 16:39
 * @Version 1.0
 */
public class SimpleLinkedList {
	private class Node{
		Node(Object obj){
			this.obj = obj;
		}
		Object obj;
		Node next;
	}
	
	private Node first;     // 第一个节点
	
	public void add(Object elem){
		Node node = new Node(elem);
		if (first == null){
			first = node;
		} else {
			append(node);
		}
	}
	
	private void append(Node node) {
		Node last = first;
		while(last.next != null){
			last = last.next;
		}
		last.next = node;
	}
	
	public int size(){
		int count = 0;
		Node last = first;
		while(last != null){
			last = last.next;
			count++;
		}
		return count;
	}
	
	public Object get(int index){
		checkSize(index);
		return findElemOf(index);
	}
	

	
	private void checkSize(int index) {
		int size = size();
		if (index >= size){
			throw new IndexOutOfBoundsException(String.format("Index:%d,Size:%d", index, size));
			
		}
	}
	private Object findElemOf(int index) {
		int count = 0;
		Node last = first;
		while(count < index){
			last = last.next;
			count ++;
		}
		return last.obj;
	}
	/**
	 * @Author cfl
	 * @Description //TODO  测试
	 * @Date 17:12 2019/6/14
	 * @Param [args]
	 * @Return void
	 */
	public static void main(String[] args) {
		SimpleLinkedList s = new SimpleLinkedList();
		for (int i = 0; i < 10; i++) {
			s.add(i);
		}
		System.out.println(s.get(11));
	}
}
