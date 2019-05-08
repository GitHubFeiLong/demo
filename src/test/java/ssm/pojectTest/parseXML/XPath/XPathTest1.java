package ssm.pojectTest.parseXML.XPath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XPathTest1 {
	public static void main(String [] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		builder.setErrorHandler(new ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {
				System.out.println("warning:" + exception.getMessage());
				
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				System.out.println("error:" + exception.getMessage());
				
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				System.out.println("fatalError:" + exception.getMessage());
				
			}
			
		});
		Document document = builder.parse("src/test/java/ssm/pojectTest/parseXML/book.xml");
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		XPathExpression expression = xpath.compile("//book[author='Neal Stephenson']/title/text()");
		
		Object result = expression.evaluate(document, XPathConstants.NODESET);
		
		NodeList list = (NodeList) result;
		
		for(int i = 0; i < list.getLength(); i++) {
			System.out.println(list.item(i).getNodeName()+"//" + list.item(i).getNodeValue()+"//" + list.item(i).getNodeType());
		}
	}

}
