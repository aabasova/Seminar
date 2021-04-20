package data;

public class Frage {
	private int frageNr;
	private String frage;
	private int korrekteAntwort;
	private int schwierigkeitsgrad;
	private String kategorie;

	public Frage(int id, String frage, int korrekteAntwort, int schwierigkeitsgrad, String kategorie) {
		this.frageNr = id;
		this.frage = frage;
		this.korrekteAntwort = korrekteAntwort;
		this.schwierigkeitsgrad = schwierigkeitsgrad;
		this.kategorie = kategorie;
	}

	public Frage(int id, String frage, int korrekteAntwort) {
		this.frageNr = id;
		this.frage = frage;
		this.korrekteAntwort = korrekteAntwort;
	}

	public int getFrageNr() {
		return frageNr;
	}

	public String getFrage() {
		return frage;
	}

	public int getkorrekteAntwort() {
		return korrekteAntwort;
	}

	public int getSchwierigkeitsgrad() {
		return schwierigkeitsgrad;
	}

	public String getKategorie() {
		return kategorie;
	}
}
