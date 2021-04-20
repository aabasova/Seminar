package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Spieler;
import db.QuizDB;
import general.Parameter;

public class StatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTable playersTable;
	Spieler spieler;
	QuizDB db;

	public StatisticsPanel(Spieler spieler, QuizDB db) {
		this.db = db;
		this.spieler = spieler;
		// create components for the table
		String[] columns = { "Rang", "Spieler", "Punkte" };
		TableModel model = new DefaultTableModel(columns, 0);
		playersTable = new JTable(model);
		playersTable.setFillsViewportHeight(true);
		playersTable.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(playersTable);
		scrollPane.setPreferredSize(new Dimension(500, 280));

		// create components for the textarea
		JTextArea area = new JTextArea();
		area.setFont(new Font("TimesRoman", Font.BOLD, 30));
		if (spieler != null) {
			db.addSpieler(spieler);
			String punkt = (spieler.getPunkte() == 1) ? "Punkt" : "Punkte";
			area.setText("Sie haben " + spieler.getPunkte() + " " + punkt + " erreicht.");
		} else {
			area.setText("Statistik");
		}
		fillTable();

		this.add(area);
		this.add(Box.createRigidArea(new Dimension(Parameter.P_WIDTH, 10)));
		this.add(scrollPane);
	}

	/**
	 * Fills the JTable with data from the database.
	 */
	public void fillTable() {
		DefaultTableModel model = (DefaultTableModel) playersTable.getModel();
		model.setRowCount(0);
		ArrayList<Spieler> spieler = db.getStatistik();
		Object[] row = new Object[3];
		int rank = 0;
		for (Spieler sp : spieler) {
			row[0] = ++rank;
			row[1] = sp.getName();
			row[2] = sp.getPunkte();
			model.addRow(row);
		}
	}

}
