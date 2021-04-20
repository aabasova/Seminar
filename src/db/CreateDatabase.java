package db;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase extends Database {

	public void createTables() {
		createTableFrage();
		createTableAntwort();
		createTableSpieler();
	}

	private void dropTables() {
		String sql = "";
		try {
			Statement statement = connection.createStatement();
			for (String s : new String[] { "Frage", "Antwort", "Spieler" }) {
				sql = "DROP TABLE IF EXISTS " + s + ";";
				statement.executeUpdate(sql);
			}
		} catch (Exception e) {
			System.out.println("Problem with statement " + sql);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Deleted tables. ");
	}

	public void createTableFrage() {

		// TODO
	}

	public void createTableAntwort() {

		// TODO
	}

	public void createTableSpieler() {

		// TODO
	}

	public static void main(String[] args) {
		CreateDatabase cdb = new CreateDatabase();
		cdb.dropTables();
		cdb.createTables();
		try {
			cdb.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cdb.disconnect();
	}
}
