package controlGUIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.BadConfigFormatException;

public class DetectiveNotesGUI extends JFrame {
	
	private ArrayList<JCheckBox> suspectsCB, roomsCB, weaponsCB;
	private JComboBox<String> suspects, rooms, weapons;
	
	public DetectiveNotesGUI(String cardConfigFile) {
		setSize(new Dimension(650, 450));
		setTitle("Detective Notes for Clue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		suspectsCB = new ArrayList<JCheckBox>();
		roomsCB = new ArrayList<JCheckBox>();
		weaponsCB = new ArrayList<JCheckBox>();
		
		suspects = new JComboBox<String>();
		weapons = new JComboBox<String>();
		rooms = new JComboBox<String>();
		
		suspects.addItem("Uncertain");
		weapons.addItem("Uncertain");
		rooms.addItem("Uncertain");
		
		try {
			FileReader reader = new FileReader(cardConfigFile);
			Scanner in = new Scanner(reader);
			while(in.hasNext()){
				String[] typeAndName = in.nextLine().split(", ");
				if (typeAndName[0].equals("SUSPECT")) {
					suspectsCB.add(new JCheckBox(typeAndName[1]));
					suspects.addItem(typeAndName[1]);
				} else if (typeAndName[0].equals("WEAPON")) {
					weaponsCB.add(new JCheckBox(typeAndName[1]));
					weapons.addItem(typeAndName[1]);
				} else if (typeAndName[0].equals("ROOM")) {
					roomsCB.add(new JCheckBox(typeAndName[1]));
					rooms.addItem(typeAndName[1]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		class SuspectPanel extends JPanel {
			public SuspectPanel() {
			setLayout(new GridLayout(suspectsCB.size() / 2, 0));
			
			for (int i = 0; i < suspectsCB.size(); i++) {
				add(suspectsCB.get(i));
			}
			setBorder(new TitledBorder (new EtchedBorder(), "Suspects"));
			}
		}
		
		class SuspectGuessPanel extends JPanel {
			public SuspectGuessPanel() {
				setLayout(new GridLayout(1,0));
				add(suspects);
				setBorder(new TitledBorder (new EtchedBorder(), "Suspect Guess"));
			}
		}
		
		class RoomPanel extends JPanel {
			public RoomPanel() {
				setLayout(new GridLayout(roomsCB.size() / 2, 0));
				
				for (int i = 0; i < roomsCB.size(); i++) {
					add(roomsCB.get(i));
				}
				setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
				}
		}
		
		class RoomGuessPanel extends JPanel {
			public RoomGuessPanel() {
				setLayout(new GridLayout(1,0));
				add(rooms);
				setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
			}
		}

		class WeaponPanel extends JPanel {
			public WeaponPanel() {
				setLayout(new GridLayout(weaponsCB.size(), 0));
				
				for (int i = 0; i < weaponsCB.size(); i++) {
					add(weaponsCB.get(i));
				}
				setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
				}
		}
		
		class WeaponGuessPanel extends JPanel {
			public WeaponGuessPanel() {
				setLayout(new GridLayout(1,0));
				add(weapons);
				setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
			}
		}
		
		SuspectPanel suspectPanel = new SuspectPanel();
		RoomPanel roomPanel = new RoomPanel();
		WeaponPanel weaponPanel = new WeaponPanel();
		
		SuspectGuessPanel suspectGuessPanel = new SuspectGuessPanel();
		RoomGuessPanel roomGuessPanel = new RoomGuessPanel();
		WeaponGuessPanel weaponGuessPanel = new WeaponGuessPanel();
		
		setLayout(new GridLayout(3,2));
		
		add(suspectPanel);
		add(suspectGuessPanel);
		add(roomPanel);
		add(roomGuessPanel);
		add(weaponPanel);
		add(weaponGuessPanel);
	}
	
	public static void main(String[] args) {
		DetectiveNotesGUI detectiveNotes = new DetectiveNotesGUI("cardConfig.txt");
		detectiveNotes.setVisible(true);
	}
	
}
