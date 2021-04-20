package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Spieler;
import db.QuizDB;
import general.Parameter;

public class MainGui extends JFrame {
	private static final long serialVersionUID = 1L;
	Spieler spieler;

	Container c;
	JButton start;
	JComboBox<String> categories;
	JComboBox<String> difficultyBox;
	QuizDB db = new QuizDB();
	JTextField nameTextField;
	QuizPanel quizpanel;

	public MainGui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.createComponents();
		this.pack();
		this.setResizable(false);
	}

	private void createComponents() {
		// initialize components
		JLabel nameLabel = new JLabel("Spieler: ", JLabel.CENTER);
		JLabel category = new JLabel("Kategorie:", JLabel.CENTER);
		JLabel difficulty = new JLabel("Schwierigkeitsgrad:", JLabel.CENTER);
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(100, 30));
		start = new JButton("Starten");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		createCategoryBox(db.getKategorien());
		createDifficultyBox(db.getSchwierigkeitsgrad());

		// add components to a panel
		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 0, 50));
		inputPanel.setMaximumSize(new Dimension(Parameter.P_WIDTH, 250));
		inputPanel.add(nameLabel);
		inputPanel.add(nameTextField);
		inputPanel.add(category);
		inputPanel.add(categories);
		inputPanel.add(difficulty);
		inputPanel.add(difficultyBox);

		createMenu();
		this.setTitle("Quiz");
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.setPreferredSize(new Dimension(Parameter.P_WIDTH, 350));
		c.add(Box.createVerticalStrut(20));
		c.add(inputPanel);
		c.add(Box.createVerticalStrut(20));
		c.add(start);
		createStartListener();
	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Quiz");
		JMenuItem statistic = new JMenuItem("Statistik zeigen");
		statistic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAll();
				showStatistics(null);
			}
		});
		JMenuItem terminate = new JMenuItem("Schließen");
		terminate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem newQuiz = new JMenuItem("Neues Quiz starten");
		newQuiz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				spieler = null;
				removeAll();
				createComponents();
			}
		});
		menu.add(newQuiz);
		menu.add(statistic);
		menu.add(terminate);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	public void showStatistics(Spieler spieler) {
		StatisticsPanel statistics = new StatisticsPanel(spieler, db);
		c.add(statistics);
	}

	public void openQuiz(String category, int difficulty) {
		quizpanel = new QuizPanel(db, spieler, category, difficulty);
		quizpanel.next.addActionListener(new ActionListener() {

			/**
			 * Checks whether all questions have been answered. If yes, then the result of
			 * the last question is evaluated and the statistic is shown.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (quizpanel.next.getText().equals("Evaluieren")) {
					quizpanel.evaluate();
					removeAll();
					showStatistics(quizpanel.getSpieler());
				}
			}
		});
		c.add(quizpanel);
	}

	public void createCategoryBox(ArrayList<String> categoriesList) {
		categories = new JComboBox<String>();
		for (String category : categoriesList) {
			categories.addItem(category);
		}
	}

	public void createDifficultyBox(ArrayList<String> levels) {
		difficultyBox = new JComboBox<String>();
		for (String level : levels) {
			difficultyBox.addItem(level);
		}
	}

	public int getDifficultyLevel(String difficulty) {
		if (difficulty.equals("leicht")) {
			return 1;
		}
		if (difficulty.equals("mittel")) {
			return 2;
		}
		if (difficulty.equals("schwer")) {
			return 3;
		}
		return 0;
	}

	public void createStartListener() {
		this.start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nameTextField.getText().isEmpty()) {
					String category = (String) categories.getSelectedItem();
					int difficulty = getDifficultyLevel((String) difficultyBox.getSelectedItem());

					spieler = new Spieler(nameTextField.getText().trim());
					removeAll();
					openQuiz(category, difficulty);
				} else {
					JOptionPane.showMessageDialog(c, "Bitte tragen Sie Ihren Namen ein!");
				}
			}
		});
	}

	public void removeAll() {
		c.removeAll();
		c.revalidate();
		c.repaint();
	}

	public static void main(String args[]) {
		MainGui gui = new MainGui();
		gui.setVisible(true);
	}

}
