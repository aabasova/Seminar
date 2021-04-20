package io;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import data.Antwort;
import data.Frage;
import data.Spieler;

public class QuizContentHandler implements ContentHandler {
	List<Frage> fragen = new ArrayList<Frage>();
	List<Antwort> antworten = new ArrayList<Antwort>();
	List<Spieler> spieler = new ArrayList<Spieler>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// TODO

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO

	}

	public List<Frage> getFragen() {
		return fragen;
	}

	public List<Antwort> getAntworten() {
		return antworten;
	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	/*
	 * ****************************** REST WIRD NICHT GEBRAUCHT
	 * **************************
	 */

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

}
