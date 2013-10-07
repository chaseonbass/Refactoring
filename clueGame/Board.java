package clueGame;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>() ;
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
				loadRoomConfig();
				loadBoardConfig();

		}
		catch(BadConfigFormatException e){
			System.out.println(e.getMessage());
		}
	}
	
	public int calcIndex(int rowNum, int columnNum){
			int index = rowNum*numColumns + columnNum;	
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

	public BoardCell getRoomCellAt(int row, int column) {
		return (RoomCell) cells.get(calcIndex(row,column));
/*		if(cells.get(calcIndex(row,column)).isRoom() == true){
			if(cells.get(calcIndex(row,column)).isRoom() == true){
				return (RoomCell) cells.get(calcIndex(row,column));
			}
		}
			return null;*/
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
				String[] keyAndRoom = in.nextLine().split(", ");
				if (keyAndRoom.length >2){
					throw new BadConfigFormatException();
				}
				rooms.put(keyAndRoom[0].toCharArray()[0], keyAndRoom[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadBoardConfig() throws BadConfigFormatException{
		// TODO Auto-generated method stub
		try{
		FileReader reader = new FileReader(sheetName);
		Scanner in = new Scanner(reader);
		boolean format = false;
		int tempRows = 0;
		String[] tempColumns = {""};
		while(in.hasNext()){
			tempColumns = in.nextLine().split(",");
			for(int i = 0; i < tempColumns.length; i++){
				RoomCell room = new RoomCell(tempRows, i, tempColumns[i]);
					if(rooms.containsKey(tempColumns[i].charAt(0)) == false)
						throw new BadConfigFormatException();
				cells.add(room);
				if(tempRows == 0){
					numColumns = tempColumns.length;
				}
				else if(tempColumns.length != numColumns){
					throw new BadConfigFormatException();
				}
			}
			tempRows++;
		}
		numRows = tempRows;
		
	}
		catch(FileNotFoundException e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}
