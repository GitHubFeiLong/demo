package ssm.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations= "classpath:spring-tx.xml")
public class AOPTest {
    @Autowired
    UserDao userDao = new UserDaoImp();

    @Test
    public void aspectJTest(){
        userDao.addUser();
    }
}
