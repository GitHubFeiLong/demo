/**
 * 
 */
package ssm.pojectTest;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

/**   
 * @ClassName:  TestLog   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年3月23日 下午4:44:17     
 */
public class TestLog {
	public static Logger logger = Logger.getLogger(TestLog.class);
	public static void main(String [] args) {
		logger.info("你好");
	}
	public static int add(int a, int b) {
		logger.info(a + b);
		return a+b;
	}
	@Test
	public void testAdd() throws Exception {
	    Assert.assertEquals(2, TestLog.add(1,1));
	} 

}
