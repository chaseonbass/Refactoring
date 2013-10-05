package clueGame;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int maxColumns, maxRows;
	private int numRows;
	private int numColumns;
	private String sheetName, legendFile;
	public Board(String sheetName, String legendFile){
		this.sheetName = sheetName;
		this.legendFile = legendFile;
	}
	
	public void loadConfigFiles(){
		try{
		loadBoardConfig();
		loadRoomConfig();
		}
		catch(BadConfigFormatException e){
			System.out.println(e.getMessage());
		}
	}
	
	public int calcIndex(int rowNum, int columnNum){
			int index = rowNum* maxColumns + columnNum;	
			return index;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public RoomCell getRoomCellAt(int row, int column) {
		RoomCell room = new RoomCell(row,column);
		if(room.isRoom() == true){
			return room;
		}
		else
			return null;
	}

	public int getMaxColumns() {
		return maxColumns;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public BoardCell getCellAt(int i) {
		return cells.get(i);
	}

	public void loadRoomConfig() throws BadConfigFormatException{
		rooms = new HashMap<Character, String>();
		try {
			FileReader reader = new FileReader(legendFile);
			Scanner in = new Scanner(reader);
			while(in.hasNext()){
				rooms.put(in.next().charAt(0), in.next());
				System.out.println(rooms.toString());
			}
		
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadBoardConfig() throws BadConfigFormatException{
		// TODO Auto-generated method stub
		try{
		FileReader reader = new FileReader("BoardLayout.csv");
	}
		catch(FileNotFoundException e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}
