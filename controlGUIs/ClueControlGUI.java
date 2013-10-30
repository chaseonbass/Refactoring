package controlGUIs;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.ClueGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ClueControlGUI extends JPanel {
	
	public ClueControlGUI() {
		//setSize(new Dimension(900,200)); // comment out later?
		
		class WhoseTurnPanel extends JPanel {
			public WhoseTurnPanel() {
				//setLayout(new GridLayout(1, 0));
				
				add(new JLabel("Whose Turn?"));
				add(new JTextField(20));
				//setBorder(new TitledBorder (new EtchedBorder(), "Whose Turn?"));
			}
		}
		
		class DieRoll extends JPanel {
			public DieRoll() {
				//setLayout(new GridLayout(1, 0));
				
				add(new JTextField(5));
				setBorder(new TitledBorder (new EtchedBorder(), "Die Roll"));
			}
		}
		
		class Guess extends JPanel {
			public Guess() {
				//setLayout(new GridLayout(1, 0));
				
				add(new JTextField(25));
				setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
			}
		}
		
		class GuessResult extends JPanel {
			public GuessResult() {
				//setLayout(new GridLayout(1, 0));
				
				add(new JTextField(15));
				setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
			}
		}
	
		// add a Die Roll textbox
		// add a Guess/Response pair of textboxes
		
		// Make the buttons and add listeners
		JButton nextPlayerButton = new JButton("Next Player");
		nextPlayerButton.addActionListener(new nextPlayerListener());
		JButton makeAccusationButton = new JButton("Make Accusation");
		makeAccusationButton.addActionListener(new accusationListener());
		
		WhoseTurnPanel whoseTurnPanel = new WhoseTurnPanel();
		DieRoll dieRoll = new DieRoll();
		Guess guess = new Guess();
		GuessResult guessResult = new GuessResult();
		
		setLayout(new GridLayout(2,3));
		add(whoseTurnPanel);
		add(nextPlayerButton);
		add(makeAccusationButton);
		add(dieRoll);
		add(guess);
		add(guessResult);
	}
	
	public static void main(String [] args){
		ClueControlGUI control = new ClueControlGUI();
		control.setVisible(true);
	}

	class nextPlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			nextPlayer();
		}
	}
	class accusationListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			accuse();
		}
	}

	public void nextPlayer(){
				System.out.println("Next Player pressed");
	}

	public void accuse(){
			System.out.println("Make Accusation pressed");
	}

}

