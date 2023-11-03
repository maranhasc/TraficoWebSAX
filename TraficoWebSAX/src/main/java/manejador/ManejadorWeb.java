package manejador;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import model.Item;

public class ManejadorWeb extends DefaultHandler {

	private XMLReader xr;
	private String lastContent;
	private InputSource is;
	private ArrayList<Item> items;
	private Item item;
	private boolean isItem;
	

	public ManejadorWeb(String strUrl) {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        SAXParser parser;
		try {
			parser = parserFactory.newSAXParser();
	        xr = parser.getXMLReader();
	        this.is=new InputSource(new URL(strUrl).openStream());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		xr.setContentHandler(this);
		xr.setErrorHandler(this);
	}

	public ArrayList<Item> parsear() {
		// En este caso como el xml esta en Internet debemos crear el stream y
		// con este el source
		try {
			// La llamada a este metodo es el que desencadena todo el proceso
			xr.parse(this.is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public void startDocument() throws SAXException {
		items = new ArrayList<Item>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		lastContent = "";
		if (localName.equals("item")) {
			item = new Item();
			isItem=true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if (localName.equals("item")) {
			items.add(item);
			isItem=false;
		} else if (localName.equals("title") && isItem) {
			item.setTitle(lastContent);
		} else if (localName.equals("link") && isItem) {
			item.setLink(lastContent);
		} else if (localName.equals("pubDate") && isItem) {
			item.setPubDate(lastContent);
		} else if (localName.equals("description") && isItem) {
			String descripcion = lastContent;
			descripcion = descripcion.replace("src='img", "src='https://infocar.dgt.es/etraffic/img");
			item.setDescription(descripcion);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		lastContent += String.valueOf(ch, start, length);
	}

}
