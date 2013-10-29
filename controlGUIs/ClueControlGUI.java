package controlGUIs;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ClueControlGUI
{
	JFrame frame;
	JLabel guessLabel, responseLabel, playerLabel, dieRollLabel;
	JTextField guessTextField, responseTextField, playerTextField, dieRollTextField;
	JButton nextPlayerButton, accuseButton;
	JMenuBar menuBar;
	Box b1, b2, b3, b4, clueButtonBox, guessBox, guessBox2;
	public static void main(String [] args)
	{if (JOptionPane.showConfirmDialog(null, "Want to play Clue?", "Choose one", JOptionPane.YES_NO_OPTION) == 0)
	{ClueControlGUI amain = new ClueControlGUI();
	amain.run();}
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

	public void run(){
		frame = new JFrame("Clue Game Control GUI");
		frame.setVisible(true);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(createFileMenu());

		////////////////////////////////////////////////////////////////////////////////////////		
		guessBox = new Box(BoxLayout.X_AXIS);
		guessBox.add(Box.createRigidArea(new Dimension(0,15)));
		guessBox.setBorder(new TitledBorder (new EtchedBorder(), "Guess Display"));

		b1 = new Box(BoxLayout.Y_AXIS);
		b2 = new Box(BoxLayout.Y_AXIS);

		guessLabel = new JLabel("Guess: ");
		b1.add(guessLabel);
		guessTextField = new JTextField("");
		guessTextField.setPreferredSize(new Dimension (150,18));
		guessTextField.setMaximumSize(new Dimension (300,18));
		b2.add(guessTextField);

		responseLabel = new JLabel("Response:  ");
		b1.add(responseLabel);

		responseTextField = new JTextField("");
		responseTextField.setPreferredSize(new Dimension (150,18));
		responseTextField.setMaximumSize(new Dimension (300,18));
		b2.add(responseTextField);

		guessBox.add(b1);
		guessBox.add(b2);
		frame.add(BorderLayout.CENTER, guessBox);

		////////////////////////////////////////////////////////
		JPanel buttonPanel = new JPanel();
		clueButtonBox = new Box(BoxLayout.X_AXIS);
		nextPlayerButton = new JButton("Next Player");
		accuseButton = new JButton("Make Accusation");
		nextPlayerButton.addActionListener(new nextPlayerListener());
		accuseButton.addActionListener(new accusationListener());
		buttonPanel.add(nextPlayerButton);
		buttonPanel.add(accuseButton);
		frame.add(BorderLayout.SOUTH, buttonPanel);
		///////////////////////////////////////////////////////


		guessBox2 = new Box(BoxLayout.X_AXIS);
		guessBox2.add(Box.createRigidArea(new Dimension(0,15)));
		guessBox2.setBorder(new TitledBorder (new EtchedBorder(), "Player Display"));

		b3 = new Box(BoxLayout.Y_AXIS);
		b4 = new Box(BoxLayout.Y_AXIS);

		playerLabel = new JLabel("Player's Turn: ");
		b3.add(playerLabel);

		playerTextField = new JTextField("");
		playerTextField.setPreferredSize(new Dimension (150,18));
		playerTextField.setMaximumSize(new Dimension (300,18));
		b4.add(playerTextField);
		dieRollLabel = new JLabel("Die Roll:  ");
		b3.add(dieRollLabel);

		dieRollTextField = new JTextField("");
		dieRollTextField.setPreferredSize(new Dimension (50,18));
		dieRollTextField.setMaximumSize(new Dimension (50,18));
		b4.add(dieRollTextField);

		guessBox2.add(b3);
		guessBox2.add(b4);
		frame.add(BorderLayout.WEST, guessBox2);

		/////////////////////////////////////////////////
		//windows stuff
		frame.setResizable(false);
		frame.setSize(565, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}

	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File"); 
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}

	public void nextPlayer(){
		try{
			System.out.println("I like sysouts");
		}catch(Exception e) {System.out.println("Bummer"); e.printStackTrace();}
	}

	public void accuse(){
		try{
			System.out.println("Do you like sysouts?");
		}catch(Exception e) {System.out.println("Bummer"); e.printStackTrace();}
	}

}

