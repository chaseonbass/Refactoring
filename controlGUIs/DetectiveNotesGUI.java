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
	private ArrayList<JLabel> suspectsL, roomsL, weaponsL;
	private JComboBox<String> suspects, rooms, weapons;
	
	public DetectiveNotesGUI(String cardConfigFile) {
		setSize(new Dimension(400, 500));
		setTitle("Detective Notes for Clue");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		suspectsCB = new ArrayList<JCheckBox>();
		roomsCB = new ArrayList<JCheckBox>();
		weaponsCB = new ArrayList<JCheckBox>();
		
		suspectsL = new ArrayList<JLabel>();
		weaponsL = new ArrayList<JLabel>();
		roomsL = new ArrayList<JLabel>();
		
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
					suspectsL.add(new JLabel(typeAndName[1]));
					suspects.addItem(typeAndName[1]);
				} else if (typeAndName[0].equals("WEAPON")) {
					weaponsCB.add(new JCheckBox(typeAndName[1]));
					weaponsL.add(new JLabel(typeAndName[1]));
					weapons.addItem(typeAndName[1]);
				} else if (typeAndName[0].equals("ROOM")) {
					roomsCB.add(new JCheckBox(typeAndName[1]));
					roomsL.add(new JLabel(typeAndName[1]));
					rooms.addItem(typeAndName[1]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		class SuspectPanel extends JPanel {
			public SuspectPanel() {
			//setLayout(new GridLayout(2, 0));
			
			for (int i = 0; i < suspectsCB.size(); i++) {
				add(suspectsL.get(i));
				add(suspectsCB.get(i));
			}
			setBorder(new TitledBorder (new EtchedBorder(), "Suspects"));
			}
		}
		
		class SuspectGuessPanel extends JPanel {
			public SuspectGuessPanel() {
				add(suspects);
				setBorder(new TitledBorder (new EtchedBorder(), "Suspect Guess"));
			}
		}
		
		class RoomPanel extends JPanel {
			public RoomPanel() {
				//setLayout(new GridLayout(2, 0));
				
				for (int i = 0; i < roomsCB.size(); i++) {
					add(roomsL.get(i));
					add(roomsCB.get(i));
				}
				setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
				}
		}
		
		class RoomGuessPanel extends JPanel {
			public RoomGuessPanel() {
				add(rooms);
				setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
			}
		}

		class WeaponPanel extends JPanel {
			public WeaponPanel() {
				//setLayout(new GridLayout(2, 0));
				
				for (int i = 0; i < weaponsCB.size(); i++) {
					add(weaponsL.get(i));
					add(weaponsCB.get(i));
				}
				setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
				}
		}
		
		class WeaponGuessPanel extends JPanel {
			public WeaponGuessPanel() {
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
		
		JPanel top = new JPanel();
		top.add(BorderLayout.WEST, suspectPanel);
		top.add(BorderLayout.EAST, suspectGuessPanel);
		
		JPanel middle = new JPanel();
		middle.add(BorderLayout.WEST, roomPanel);
		middle.add(BorderLayout.EAST, roomGuessPanel);
		
		JPanel bottom = new JPanel();
		bottom.add(BorderLayout.WEST, weaponPanel);
		bottom.add(BorderLayout.EAST, weaponGuessPanel);
		
		add(BorderLayout.NORTH, top);
		add(BorderLayout.CENTER, middle);
		add(BorderLayout.SOUTH, bottom);
	}
	
	public static void main(String[] args) {
		DetectiveNotesGUI detectiveNotes = new DetectiveNotesGUI("cardConfig.txt");
		detectiveNotes.setVisible(true);
	}
	
}
