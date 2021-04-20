package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import data.Antwort;
import data.Frage;
import data.Spieler;
import general.Parameter;
import io.XML_Reader;

public class FillDatabase extends Database {
	private PreparedStatement pstmFrage, pstmAntwort, pstmSpieler;

	public void prepareStatements() {
		// TODO

	}

	private void clearTable(String name) {
		try {
			Statement statement = connection.createStatement();
			String sql = "DELETE FROM " + name + ';';
			statement.execute(sql);
		} catch (SQLException e) {
			System.out.println("Could not perform deletion in table " + name);
			System.out.println(e);
		}
	}

	public void fillFrage(Collection<Frage> fragen) {
		clearTable("Frage");
		// TODO

	}

	public void fillAntwort(Collection<Antwort> antworten) {
		clearTable("Antwort");
		// TODO

	}

	public void fillSpieler(Collection<Spieler> spieler) {
		clearTable("Spieler");
		// TODO

	}

	private void closeStatements() {
		try {
			if ((pstmAntwort != null) && (!pstmAntwort.isClosed())) {
				pstmAntwort.close();
			}
			if ((pstmSpieler != null) && (!pstmSpieler.isClosed())) {
				pstmSpieler.close();
			}
			if ((pstmFrage != null) && (!pstmFrage.isClosed())) {
				pstmFrage.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Problem beim Schliessen der Prepared Statements aufgetreten.");
		}
	}

	public static void main(String[] args) {
		FillDatabase fdb = new FillDatabase();
		XML_Reader reader = new XML_Reader(Parameter.xmlPath);
		reader.parseFile();
		fdb.prepareStatements();
		fdb.fillFrage(reader.getFragen());
		fdb.fillAntwort(reader.getAntworten());
		fdb.fillSpieler(reader.getSpieler());
		try {
			fdb.connection.commit();
			fdb.closeStatements();
			fdb.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
