package data;

public class Antwort {
	private int nr;
	private String antwort;
	private int frage;

	public Antwort(int nr, String antwort, int fragenr) {
		this.nr = nr;
		this.antwort = antwort;
		this.frage = fragenr;
	}

	public Antwort(int nr, String antwort) {
		this.nr = nr;
		this.antwort = antwort;
	}

	public int getNr() {
		return nr;
	}

	public String getAntwort() {
		return antwort;
	}

	public int getFrage() {
		return frage;
	}

}
