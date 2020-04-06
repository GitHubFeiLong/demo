package ssm.utils;

import com.ssm.mf.utils.SumFieldUtil;

import java.util.ArrayList;
import java.util.List;

public class SumFieldUtilTest {
    public static void main(String[] args) {
        List<Pojo> list = new ArrayList<Pojo>(10);
        System.out.println("list.size() = " + list.size());
        for (int i = 0; i <10 ; i++) {

            Pojo pojo = new Pojo((byte)i,(char)i,(short)i,i,(long)i, (float)(1.1 * i),1.1 * i);
            System.out.println(pojo);
            list.add(pojo);
        }
        System.out.println("list.size() = " + list.size());
        Pojo p = (Pojo)SumFieldUtil.getObject(list,new Pojo());
        System.out.println(p);
    }
}
