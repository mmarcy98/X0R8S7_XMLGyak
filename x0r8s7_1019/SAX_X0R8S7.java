import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.*;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

public class SaxSVOXGH {
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		indent++;
		indent();
		System.out.println(qName + formatAttributes(attributes) + "start");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		indent();
		indent--;
		System.out.println(qName + "end");
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch,start,length);
		if(!chars.isEmpty()) {
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			SaxHandler handler = new SaxHandler();
			
			saxParser.parse(new File("szemelyek.xml"), handler);
			
		}catch(ParserConfigurationException | SAXExeption | IOExeption e) {
			e.printStackTrace();
		}
	}

}
