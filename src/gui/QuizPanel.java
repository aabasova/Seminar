package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;

import data.Antwort;
import data.Frage;
import data.Spieler;
import db.QuizDB;
import general.Parameter;

public class QuizPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Spieler spieler;
	QuizDB db;
	String category;

	int questionNumber;
	JLabel question;
	JLabel prefixLabel;
	ImageIcon icon;
	JProgressBar progressBar;
	ButtonGroup bt;
	JToggleButton[] answerButtons;
	List<Frage> questions;
	JButton next;

	public QuizPanel(QuizDB db, Spieler spieler, String category, int difficulty) {
		this.db = db;
		this.spieler = spieler;
		this.category = category;
		initializeQuestions(difficulty);
		initializeImage();

		JPanel q = new JPanel();
		q.setSize(Parameter.P_WIDTH, 100);
		questionNumber = 0;
		prefixLabel = new JLabel(icon);
		prefixLabel.setFont(new Font("Arial", Font.ITALIC, 16));
		prefixLabel.setHorizontalAlignment(JLabel.LEFT);
		prefixLabel.setHorizontalTextPosition(JLabel.RIGHT);
		question = new JLabel();
		question.setAlignmentX(Component.CENTER_ALIGNMENT);
		questionNumber = 0;
		progressBar = new JProgressBar();
		progressBar.setMaximum(questions.size());
		progressBar.setMinimum(0);
		progressBar.setStringPainted(true);
		progressBar.setMaximumSize(new Dimension(150, 150));

		q.add(Box.createVerticalStrut(20));
		q.add(progressBar);
		q.add(Box.createHorizontalStrut(Parameter.P_WIDTH));
		q.add(prefixLabel);
		q.add(question);

		JPanel answers = new JPanel();
		answers.setLayout(new GridLayout(2, 2));
		JToggleButton tb1 = new JToggleButton();
		JToggleButton tb2 = new JToggleButton();
		JToggleButton tb3 = new JToggleButton();
		JToggleButton tb4 = new JToggleButton();
		bt = new ButtonGroup();
		bt.add(tb1);
		bt.add(tb2);
		bt.add(tb3);
		bt.add(tb4);
		answers.add(tb1);
		answers.add(tb2);
		answers.add(tb3);
		answers.add(tb4);
		answerButtons = new JToggleButton[] { tb1, tb2, tb3, tb4 };

		next = new JButton("Weiter");
		next.setAlignmentX(Component.CENTER_ALIGNMENT);
		createNextListener();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(Parameter.P_WIDTH, 350));
		this.add(q);
		this.add(Box.createVerticalStrut(50));
		this.add(answers);
		this.add(Box.createVerticalStrut(30));
		this.add(next);
		this.add(Box.createVerticalStrut(30));
		updatePanel();
	}

	/**
	 * Initializes the list with the right questions from the database and saves
	 * only the random 10 questions.
	 * 
	 * @param difficulty level of difficulty
	 */
	public void initializeQuestions(int difficulty) {
		questions = db.getFragen(category, difficulty);
		Collections.shuffle(questions);
		if (questions.size() > 10) {
			questions = questions.subList(0, 10);
		}
	}

	/**
	 * Initializes the imageIcon variable with the correct image, in the correct
	 * size.
	 */
	public void initializeImage() {
		switch (this.category) {
		case "Musik":
			icon = new ImageIcon(Parameter.musicImage);
			break;
		case "Geografie":
			icon = new ImageIcon(Parameter.geographyImage);
			break;
		case "Filme":
			icon = new ImageIcon(Parameter.movieImage);
		}
		Image logoScaled = getScaledImage(icon.getImage(), 50, 50);
		icon = new ImageIcon(logoScaled);
	}

	/**
	 * Scales the given image to the specified width and height.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return scaled image
	 */
	public Image getScaledImage(Image image, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
		return resizedImg;
	}

	/**
	 * Updates the entire panel
	 */
	public void updatePanel() {
		updateQuestion();
		updateAnswers();
		updateProgressBar();
	}

	public void createNextListener() {
		this.next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				evaluate();
				removeQuestionFromList();
				if (!questions.isEmpty()) {
					updatePanel();
				}
				if (questions.size() == 1) {
					next.setText("Evaluieren");
				}
			}
		});
	}

	/**
	 * Checks whether the selected answer equals to the correct answer. If yes, then
	 * the players score increases by one.
	 */
	public void evaluate() {
		for (JToggleButton button : answerButtons) {
			if (button.isSelected()) {
				String selectedAnswer = button.getText();
				updateScore(selectedAnswer);
				break;
			}
		}
	}

	/**
	 * Checks whether the correct answer is equal to the selected answer. If yes,
	 * then the score of the player increases by one point.
	 * 
	 * @param selectedAnswer answer selected from the togglebutton
	 */
	public void updateScore(String selectedAnswer) {
		if (selectedAnswer.equals(db.getKorrekteAntwort(questions.get(0).getFrageNr()))) {
			spieler.increaseScore();
		}
	}

	/**
	 * Increases the percentage of completed questions and adjusts the bar
	 * accordingly.
	 */
	public void updateProgressBar() {
		int max = this.progressBar.getMaximum();
		int finishedQuestions = max - questions.size();
		this.progressBar.setValue(finishedQuestions);
	}

	/**
	 * Updates the label containing the question. The label contains a text
	 * constisting of the question-number and the question itself.
	 */
	public void updateQuestion() {
		questionNumber++;
		Frage currentQuestion = questions.get(0);
		String prefix = "Frage " + questionNumber + ": ";
		prefixLabel.setText(prefix);
		question.setText(currentQuestion.getFrage());
		this.revalidate();
		this.repaint();
	}

	/**
	 * Updates each Togglebutton with a new possible solution for the current
	 * question.
	 */
	public void updateAnswers() {
		Frage currentQuestion = questions.get(0);
		ArrayList<Antwort> answers = db.getAntworten(currentQuestion.getFrageNr());

		bt.clearSelection();
		for (JToggleButton toggleButton : answerButtons) {
			toggleButton.setText(answers.get(0).getAntwort());
			answers.remove(0);
		}
	}

	/**
	 * Removes the top question from the list
	 */
	public void removeQuestionFromList() {
		if (!questions.isEmpty()) {
			questions.remove(0);
		}
	}

	public Spieler getSpieler() {
		return spieler;
	}

}
