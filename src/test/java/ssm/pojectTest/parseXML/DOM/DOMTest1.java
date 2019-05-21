package ssm.pojectTest.parseXML.DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.ChildNode;

public class DOMTest1 {
	public static void main(String [] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			File f = new File("src/test/java/ssm/pojectTest/parseXML/book.xml");
			System.out.println("filename：" + f.getName());
			document = builder.parse(f);
			NodeList parentList = document.getElementsByTagName("book");
			
			for (int i = 0; i < parentList.getLength(); i++) {
				
				// 获取节点地属性
				NamedNodeMap map = parentList.item(i).getAttributes();
				for (int j = 0; j < map.getLength(); j ++) {
					System.out.println(map.item(j).getNodeName()+"="+map.item(j).getNodeValue()+":"+map.item(j).getNodeType());
				}
				
				NodeList childs = parentList.item(i).getChildNodes();
				for (int j = 0; j < childs.getLength(); j ++) {
					if (childs.item(j).getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(childs.item(j).getNodeName()+"=>"+childs.item(j).getTextContent());
					}
				}
				
				
			}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
