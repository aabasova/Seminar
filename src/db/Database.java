package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import general.Parameter;

public class Database {
	private Properties properties;
	private String dbFilename;
	protected Connection connection;

	private void init() {
		properties = new Properties();
		properties.setProperty("PRAGMA foreign_keys", "ON");
	}

	public Database() {
		this.dbFilename = Parameter.databasePath;
		System.out.println("Database File: " + this.dbFilename);
		init();
		this.connection();
	}

	public void connection() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilename);
			this.connection.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println("Problem with database file " + dbFilename);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	protected void disconnect() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
