package hu.domparse.x0r8s7;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyx0r8s7 {

	public static void main(String[] args) {
		try {
			File inputFile = new File("XMLx0r8s7.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			Node vendeg = doc.getElementsByTagName("vendeg").item(0);

			NamedNodeMap attr = vendeg.getAttributes();			// 10-re módosítjuk a sorszam-t 1-rõl
			Node nodeAttr = attr.getNamedItem("sorszam");
			nodeAttr.setTextContent("10");

			NodeList list = vendeg.getChildNodes();

			for (int temp = 0; temp < list.getLength(); temp++) {       			// a hibaüzenet megváltoztatása
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element hElement = (Element) node;
					if ("sorszam".equals(hElement.getNodeName())) {
						if ("123456BC".equals(hElement.getTextContent()))
							hElement.setTextContent("123456BC");
					}
				}

			}
			NodeList list2 = doc.getElementsByTagName("alapanyag");
			for (int j = 0; j < list2.getLength(); j++) {
				Node alapanyag = list2.item(j);
				if (alapanyag.getNodeType() == Node.ELEMENT_NODE) {
					String id = alapanyag.getAttributes().getNamedItem("azon").getTextContent();
					if ("1".equals(id.trim())) {
						NodeList gyerekNodes = alapanyag.getChildNodes();
						for (int k = 0; k < gyerekNodes.getLength(); k++) {
							Node item = gyerekNodes.item(k);
							if (item.getNodeType() == Node.ELEMENT_NODE) {                   // kitöröljük az vendégbõl a kód mezõt
								if ("telefonszam".equalsIgnoreCase(item.getNodeName())) {
									alapanyag.removeChild(item);
								}
								// módosítjuk az 1-es sorszam-mal rendelkezõ nevét
								if ("nev".equalsIgnoreCase(item.getNodeName())) {
									item.setTextContent("ABC");
								}
							}
						}
					}
				}
			}
			// kiíratás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println("-----------Módosított File-----------");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


//#CreatedByMeszarosMarcell