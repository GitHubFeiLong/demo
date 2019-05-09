package ssm.pojectTest.design.factory.abstractFactory.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	public static Object getBeen() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/test/java/ssm/pojectTest/design/factory/abstractFactory/config.xml"));
		NodeList nl = document.getElementsByTagName("className");
		Node classNode = nl.item(0).getFirstChild();
		String cName = classNode.getNodeValue();
		
		Class c = Class.forName(cName);
		Object obj = c.newInstance();
		return obj;
	}

}
