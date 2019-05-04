package ssm.pojectTest.parseXML.XPath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * XPath:类似于sql语句
 * @author msi
 * @date 2019年5月4日
 */
public class XPathTest {
	public static void main(String [] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		// 开启验证
//		factory.setValidating(true);
//		factory.setNamespaceAware(false);
//		factory.setIgnoringComments(true);
//		factory.setIgnoringElementContentWhitespace(false);
//		factory.setCoalescing(false);
//		factory.setExpandEntityReferences(true);
		
		try {
			// 创建DocumentBuilder对象
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// 设置异常处理对象
			builder.setErrorHandler(new ErrorHandler() {

				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.out.println("error:" + exception.getMessage());
					
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					System.out.println("error:" + exception.getMessage());
					
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					System.out.println("warn:" + exception.getMessage());
					
				}
				
			});
			
			// 将文档加载到document对象中
			Document document = builder.parse("src/test/java/ssm/pojectTest/parseXML/book.xml");
			
			// 创建XPathFactory
			XPathFactory xPathFactory = XPathFactory.newInstance();
			// 创建XPath对象
			XPath xpath = xPathFactory.newXPath();
			
			// 编译XPath表达式
			XPathExpression expression = xpath.compile("//book[autho='Neal Stephenson']/title/text()");
			// 通过XPath表达式得到结果，第一个参数制定了XPath表达式进行查询的上下文节点，也就是在指定节点下查找符合XPath的节点。
			// 本例中的上下文节点就是整个文档；第二个参数制定了XPath表达式的返回类型
			Object result = expression.evaluate(document, XPathConstants.NODESET);
			
			System.out.println("查询作责为Neal Stephenson的图书标题：");
			// 强制类型转换
			NodeList list = (NodeList) result;
			for (int i = 0; i < list.getLength(); i++) {
				System.out.println(list.item(i).getNodeValue());
			}
			
			NodeList nodeList = document.getElementsByTagName("books");
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
