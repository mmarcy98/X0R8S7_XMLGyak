package hu.domparse.x0r8s7;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DOMReadx0r8s7 {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLx0r8s7.xml"); // fájl beolvasása
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			Read(doc);
			//kivételkezelés
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void Read(Document doc) {
		NodeList nList = doc.getElementsByTagName("etterem1"); //NoteList létrehozása, amiben tároljuk az elemeket
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) { //az azonosito kodok
				String kod = element.getAttribute("kod");
				String azon = element.getAttribute("azon");
				String kk = element.getAttribute("kk");
				ReadEtteremById(doc, kod);
				ReadAlapanyagById(doc, azon);
				ReadKiszolgaloById(doc, kk);
			}
		}
	}

	public static void ReadEtteremById(Document doc, String kod) {
		NodeList nList = doc.getElementsByTagName("etterem1"); //étterem adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("kod").equals(eid)) { //kiirandó adatok kiválasztása
					String telefonszam = element.getElementsByTagName("telefonszam").item(0).getTextContent();
					String etel = element.getElementsByTagName("etel").item(0).getTextContent();
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String cim = element.getElementsByTagName("cim").item(0).getTextContent();
					System.out.println(" Az étterem adatai: \n\tNév:\t" + nev 
							+ "\n\tTelefonszám:\t" + telefonszam + "\n\tÉtelek::\t" + etel + "\n\tCim:\t" + cim); //kiíratás
				}
			}
		}
	}

	public static void ReadAlapanyagById(Document doc, String azon) {
		NodeList nList = doc.getElementsByTagName("alapanyag"); //az alapanyagokat tartalmazo nodelist létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("azon").equals(bid)) { //kiirandó adatok kiválasztása
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String keszlet = element.getElementsByTagName("keszlet").item(0).getTextContent();
					String lejarat = element.getElementsByTagName("lejarat").item(0).getTextContent();
					System.out.println("Alapanyagok: \n\tNév:\t" + nev + "\n\tkeszlet:\t" + keszlet
							+ "\n\tLejárat:\t" + lejarat); //kiíratás
					String ekod = element.getAttribute("ekod");
					ReadTermeloById(doc, ekod);
				}
			}
		}
	}

	public static void ReadEtelById(Document doc, String ekod) {
		NodeList nList = doc.getElementsByTagName("etel"); //a készételek adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("ekod").equals(ekod)) { //kiirandó adatok kiválasztása
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String ar = element.getElementsByTagName("ar").item(0).getTextContent();
					String keszlet = element.getElementsByTagName("keszlet").item(0).getTextContent();
					System.out.println("Készétel: \n\tNév:\t" + nev + "\n\tÁr:\t" + ar
							+ "\n\tKészlet:\t" + keszlet); //kiíratás
				}
			}
		}
	}

	public static void ReadVendegById(Document doc, String sorszam) {
		NodeList nList = doc.getElementsByTagName("vendeg"); //a vendégek adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("sorszam").equals(sorszam)) { //kiirandó adatok kiválasztása
					String nev = element.getElementsByTagName("nev").item(0).getTextContent();
					String telefonszam = element.getElementsByTagName("telefonszam").item(0).getTextContent();
					System.out.println("Személyzet adatok: \n\tNév:\t" + nev + "\n\tTelefonszám:\t" + telefonszam
							+ "\n\tCím:\t" + cim + "\n\tMunkakör:\t" + munkakor)  //kiíratás
					String rsz = element.getAttribute("rsz");
					ReadFoglalasById(doc, rsz);
				}
			}
		}
	}

	public static void ReadRendelesById(Document doc, String rsz) {
		NodeList nList = doc.getElementsByTagName("rendeles"); //a rendelések adatait tartalmazó nodeList létrehozása
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				if (element.getAttribute("rsz").equals(fid)) { //kiirandó adatok kiválasztása
					String idopont = element.getElementsByTagName("idopont").item(0).getTextContent();
					String fizmod = element.getElementsByTagName("fizmod").item(0).getTextContent();
					String tipus = element.getElementsByTagName("tipus").item(0).getTextContent();
					System.out.println("Rendelés adatok: \n\tIdőpont:\t" + idopont + "\n\tFizetési Mód:\t" + fizmod + "\n\tTípus:\t" + tipus); //kiíratás
				}
			}
		}
	}
}

//#CreatedByMeszarosMarcell