package ssm.pojectTest.parseXML.DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import static java.lang.System.out;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMTest1 {
	public static void main(String [] args) {
		try {
			DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = db.newDocumentBuilder();
			Document document = builder.parse("src/test/java/ssm/pojectTest/parseXML/book.xml");
			NodeList nodeList = document.getElementsByTagName("book");
			// 循环根节点
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				// 获取根节点的属性
				NamedNodeMap map = node.getAttributes();
				// 将根节点的元素遍历
				for (int j = 0; j < map.getLength(); j++) {
					System.out.println(map.item(j).getNodeName() + "||" + map.item(j).getNodeValue());
				}
				
				//解析根节点的子节点
				NodeList childList = node.getChildNodes();
				
				for (int j = 0; j < childList.getLength(); j++) {
					Node childNode = childList.item(j);
					
					map = childNode.getAttributes();
					if (map != null) {
						for (int k = 0; k < map.getLength(); k++) {
							out.println(map.item(k).getNodeName() + "\\" + map.item(k).getNodeValue());
						}
					}
					
					// 是元素节点
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						out.println(childNode.getNodeName() + "=" + childNode.getTextContent());
					}
					
				}
				
				
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
