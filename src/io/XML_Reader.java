package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import data.Antwort;
import data.Frage;
import data.Spieler;
import general.Parameter;

public class XML_Reader {
	private File file;
	private List<Frage> fragen;
	private List<Antwort> antworten;
	private List<Spieler> spieler;

	public XML_Reader(String filepath) {
		file = new File(filepath);
		fragen = new ArrayList<Frage>();
		antworten = new ArrayList<Antwort>();
		spieler = new ArrayList<Spieler>();
	}

	public void parseFile() {
		try {
			// Create XMLReader
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			
			FileReader reader = new FileReader(file);
			InputSource inputSource = new InputSource(reader);

			// Set the DTD-File as an identifier
			inputSource.setSystemId(Parameter.resourcesPath + "Quiz.dtd");

			// Create ContentHandler
			QuizContentHandler handler = new QuizContentHandler();
			xmlReader.setContentHandler(handler);

			// Parse the xml-document
			xmlReader.parse(inputSource);

			fragen = handler.getFragen();
			antworten = handler.getAntworten();
			spieler = handler.getSpieler();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
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

}
