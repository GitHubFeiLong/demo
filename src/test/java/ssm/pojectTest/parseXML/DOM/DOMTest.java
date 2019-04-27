package ssm.pojectTest.parseXML.DOM;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 使用DOM解析XML
 * @author msi
 * @date 2019年4月27日
 */
public class DOMTest {
	public static void main(String [] args) {
		// 1.创建DocumentBuilderFactory对象
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
		try {
			// 2.创建DocumentBuilder对象
			DocumentBuilder builder = a.newDocumentBuilder();
			// 3.通过Document对象的parse方法返回一个Document对象
			Document document = builder.parse("src/test/java/ssm/pojectTest/parseXML/book.xml");
			// 4.通过Document对象的getElementsByTagName()返根节点的一个list集合
			NodeList bookList = document.getElementsByTagName("book");
			// book节点的个数（这里是3）
			System.out.println("bookList.getLength():" + bookList.getLength());
			
			for (int i = 0; i < bookList.getLength(); i++) {
				// 循环遍历获取每一个book 
				Node book = bookList.item(i);
				// 获取节点的所有属性(在这里就是book元素的所有属性)
				NamedNodeMap map = book.getAttributes();
				
				for (int j = 0; j < map.getLength(); j ++) {
					Node node = map.item(j);
					//通过Node对象的getNodeName()和getNodeValue()方法获取属性名和属性值
					System.out.println(node.getNodeName() + ":" + node.getNodeValue() + "--" + node.getNodeType());
				}
				// 解析book节点的子节点(在这里就是获取当前循环中的book节点的子元素)
				NodeList childList = book.getChildNodes();
				//System.out.println("childList.getLength():" + childList.getLength());
				for (int t = 0; t < childList.getLength(); t++) {
					 // 区分出text类型的node以及element类型的node 
					if (childList.item(t).getNodeType() == Node.ELEMENT_NODE) {
						// getNodeNmae()用于得到子节点名，getTextContext()用于得到子节点的文本内容
						System.out.println(childList.item(t).getNodeName()+"===" + childList.item(t).getTextContent());
					}
				}
				
				
			}
			
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
