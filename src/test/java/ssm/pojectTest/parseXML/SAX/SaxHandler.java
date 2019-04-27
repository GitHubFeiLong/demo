package ssm.pojectTest.parseXML.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author msi
 * @date 2019年4月27日
 */
public class SaxHandler extends DefaultHandler{

	@Override
	public void startDocument() throws SAXException {
		System.out.println("…………开始解析文档…………\n");
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("\n…………结束解析文档…………");
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		System.out.print("<" + qName);
		
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i ++) {
				System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
			}
		}
		
		System.out.print( ">");
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</" + qName + ">");
		super.endElement(uri, localName, qName);
	}

	/* 
	*  此方法有三个参数
    * ch是传回来的字符数组，其包含元素内容
    * start和length分别是数组的开始位置和结束位置 
    */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.print(content);
		super.characters(ch, start, length);
	}

}
