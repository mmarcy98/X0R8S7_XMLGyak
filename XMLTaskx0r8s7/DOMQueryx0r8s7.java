package hu.domparse.x0r8s7;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class DOMQueryx0r8s7 {

	public static void main(String[] args)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		File xmlFile = new File("XMLx0r8s7.xml"); //beolvassuk a fájlt
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		LoadtermeloQuery(doc);
	}

	public static void LoadtermeloQuery(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("etel"); //etel noteList létrehoz
		String etel;
		Element element = null;
		Node nNode = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			String nev = element.getElementsByTagName("nev").item(0).getTextContent(); //név szerint lekérdezzük
			System.out.println(nev);

		}
		System.out.println("\nÍrja be annak az ételnek a nevét, amelyiknek adatait szeretné látni:");
		Scanner sc = new Scanner(System.in); //consolról beolvas
		termelo = sc.nextLine();
		for (int i = 0; i < nodeList.getLength(); i++) {
			nNode = nodeList.item(i);
			element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (etel.equals("Margaréta")) { 
					LoadEtelQuery(doc, "1");
					break;
				}

				if (etel.equals("Sonkasajt")) {
					LoadEtelQuery(doc, "2");
					break;
				}

				if (etel.equals("Szalámisajt")) {
					LoadEtelQuery(doc, "3");
					break;
				}	
				
				if (etel.equals("Hagymasajt")) {
					LoadEtelQuery(doc, "4");
					break;
				
				}
			}
		}
		sc.close();
	}

	public static void LoadEtelQuery(Document doc, String ekod) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("etel"); //etel noteList létrehozása

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			Element element = (Element) nNode;
			String ekod = element.getAttribute("ekod"); //az ekod alapján keressük az elemet
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				if (ekod.equals(azon)) {
					String Bid = element.getAttribute("ekod"); //ekod alapján kivesszük az elemet
					DOMReadx0r8s7.ReadEtelById(doc, ekod); //meghívjuk az etel függvényt
				}
			}
		}
	}
}

//CreatedByMeszarosMarcell