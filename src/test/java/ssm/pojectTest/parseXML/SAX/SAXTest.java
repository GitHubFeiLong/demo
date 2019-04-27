package ssm.pojectTest.parseXML.SAX;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAXTest {
	public static void main(String [] args) {
		// 1.实例化SAXParserFactory对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.创建解析器
		try {
			SAXParser parser = factory.newSAXParser();
			// 3. 获取需要解析的文档
			File f = new File("src/test/java/ssm/pojectTest/parseXML/book.xml");
			SaxHandler handler = new SaxHandler();
			parser.parse(f,handler);
		} catch (ParserConfigurationException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
