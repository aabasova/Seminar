package data;

public class Spieler {
	private int spielerNr;
	private String name;
	private int punkte;

	public Spieler(int id, String name, int punkte) {
		this.spielerNr = id;
		this.name = name;
		this.punkte = punkte;
	}

	public Spieler(String name) {
		this.name = name;
		this.punkte = 0;
		generateNumber();
	}

	public int getSpielerNr() {
		return spielerNr;
	}

	public String getName() {
		return name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void increaseScore() {
		++punkte;
	}

	public void generateNumber() {
		int random = (int) (Math.random() * (100)) + 2050;
		this.spielerNr = random;
	}

}
