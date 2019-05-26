package ssm.handbook;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListTest {
	public static final Double D = 3.1415; 
	public static void main(String [] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		for (String item : list) {
			if ("2".equals(item)) {
				list.remove(item);
			}
		}
		
	}
}
