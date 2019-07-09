package ssm.utils;

import com.ssm.mf.utils.SumFieldUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SumFieldUtilTest
 * @Description TODO
 * @Author cfl
 * @Date 2019/7/9 12:47
 * @Version 1.0
 */
public class SumFieldUtilTest {
	public static void main (String[] args) {
		List<Demo> list = new ArrayList<Demo>();
		for(int i = 0; i < 15; i ++){
			Demo demo = new Demo(i+10, i*10, i*2.5, "name:" + i);
			list.add(demo);
		}
		SumFieldUtil<Demo> s = SumFieldUtil.getInstance();
		Demo d = new Demo();
		d = s.getObject(list, d);
		System.out.println(d);
	}
}
