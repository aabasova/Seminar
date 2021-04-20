package db;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import data.Antwort;
import data.Frage;
import data.Spieler;

public class QuizDB extends Database {
	PreparedStatement pstm1, pstm2, pstm3, pstm4, pstm5, pstm6, pstm7;

	public QuizDB() {
		super();
	}

	public ArrayList<String> getKategorien() {
		// TODO

		return new ArrayList<String>(); // Dummy return
	}

	public ArrayList<String> getSchwierigkeitsgrad() {
		// TODO

		return new ArrayList<String>(); // Dummy return
	}

	public ArrayList<Frage> getFragen(String category, int difficulty) {
		// TODO

		return new ArrayList<Frage>(); // Dummy return
	}

	public ArrayList<Antwort> getAntworten(int frageId) {
		// TODO

		return new ArrayList<Antwort>(); // Dummy return
	}

	public String getKorrekteAntwort(int frageId) {
		// TODO

		return null; // Dummy return
	}

	public ArrayList<Spieler> getStatistik() {
		// TODO

		return new ArrayList<Spieler>(); // Dummy return
	}

	public void addSpieler(Spieler spieler) {
		// TODO

	}

}
