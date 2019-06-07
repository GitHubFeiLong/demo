package ssm.mybatis;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ssm.mf.page.dao.PageMapper;
import com.ssm.mf.uploadfile.dao.FileMapper;

public class SqlSessionTest {
	public static void main(String [] args) throws IOException {
		Resource rs = new ClassPathResource("mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(rs.getInputStream());
		SqlSession session = factory.openSession();
		System.out.println(session.getMapper(FileMapper.class).getClass().getName());
		FileMapper mapper = session.getMapper(FileMapper.class);
		Method [] ms = mapper.getClass().getDeclaredMethods();
		for (Method m : ms) {
			
			System.out.println(m.getName());
			
		}
	}

}
