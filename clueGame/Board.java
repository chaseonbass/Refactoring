package clueGame;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character,String> rooms;
	private int maxRows, maxColumns;
	private int numRows, numColumns;
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
					throw new BadConfigFormatException("Bad legend Config");
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
			numRows = 0;
			String[] tempColumns = {""};
			while(in.hasNext()){
				tempColumns = in.nextLine().split(",");
				for(int i = 0; i < tempColumns.length; i++){
					RoomCell room = new RoomCell(tempRows, i, tempColumns[i]);
					if(rooms.containsKey(tempColumns[i].charAt(0)) == false){
						throw new BadConfigFormatException(tempColumns[i]+"is an invalid room letter assignment");
					}
					cells.add(room);
					if(tempRows == 0){
						numColumns = tempColumns.length;
					}
					else if(tempColumns.length != numColumns){
						throw new BadConfigFormatException("Bad Columns");
					}
				}
				tempRows++;
				numRows = tempRows;
			}
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	// copied from int board class below this lineee

	private LinkedList<LinkedList> adjList = new LinkedList<LinkedList>();
	//ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private Set<BoardCell> targets;

	private boolean[] visited;
	private int index, row, column, visitedIndex;
	private ArrayList<BoardCell> helper = new ArrayList<BoardCell>();
	public void calcAdjacencies(){
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		index = 0;
		row = 0;
		column = 0;
		for(row = 0; row < numRows; row++){
			for(column = 0; column < numColumns; column++){
				LinkedList <Integer> indexAdjacencies = new LinkedList <Integer>();
				if(cells.get(index).isDoorway() == true){
					// if current cell is doorway, check for correct direction and add approp. cell
					RoomCell room = (RoomCell)cells.get(index);
					if(room.getDoorDirection() == room.doorDirection.UP){
						indexAdjacencies.add(index-numColumns);
					}
					else if(room.getDoorDirection() == room.doorDirection.DOWN){
						indexAdjacencies.add(index+numColumns);
					}
					else if(room.getDoorDirection() == room.doorDirection.LEFT){
						indexAdjacencies.add(index-1);
					}
					else if(room.getDoorDirection() == room.doorDirection.RIGHT){
						indexAdjacencies.add(index+1);
					}
				}
				else if(cells.get(index).isRoom() == true){
					// If the current cell is a room, don't add any adjacencies
				}
				else{
					if(row != 0 && cells.get(index-numColumns).isRoom() == false){
						// if cell above is not doorway or is but has correct direction, add to list
						RoomCell room = (RoomCell)cells.get(index-numColumns);
						if(room.getDoorDirection() == room.doorDirection.DOWN || room.isDoorway() == false)
							indexAdjacencies.add(index-numColumns);
					}

					if(column != 0 && cells.get(index-1).isRoom() == false){
						// if cell to left is not doorway or is but has correct direction, add to list
						RoomCell room = (RoomCell)cells.get(index-1);
						if(room.getDoorDirection() == room.doorDirection.RIGHT || room.isDoorway() == false)
							indexAdjacencies.add(index-1);
					}

					if(row != numRows -1 && cells.get(index+numColumns).isRoom() == false){
						// if cell below is not doorway or is but has correct direction, add to list
						RoomCell room = (RoomCell)cells.get(index+numColumns);
						if(room.getDoorDirection() == room.doorDirection.UP || room.isDoorway() == false) 
							indexAdjacencies.add(index+numColumns);
					}

					if(column != numColumns -1 && cells.get(index+1).isRoom() == false){
						// if cell to right is not doorway or is but has correct direction, add to list
						RoomCell room = (RoomCell)cells.get(index+1);
						if(room.getDoorDirection() == room.doorDirection.LEFT || room.isDoorway() == false)
							indexAdjacencies.add(index+1);
					}
				}
				adjMtx.put(index,indexAdjacencies);  // add list to map
				index++;  //increment index
			}
		}	
	}
	public void startTargets(int row, int column, int numSteps){
		int thisCell = calcIndex(row, column);

		LinkedList<Integer> tempo = new LinkedList<Integer>();

		tempo = adjMtx.get(thisCell);  // tempo is a LinkedList<Integer>	
		tempo = (LinkedList<Integer>) tempo.clone();
		for(int i = 0; i < tempo.size(); i++){
			if(visited[tempo.get(i)] == true){
				tempo.remove(i);             // if visited is true, remove this guy from tempo
			}
		}
		for(int i = 0; i < tempo.size(); i++){
			visited[tempo.get(i)] = true;    // set adjCell = true
			if(numSteps == 1 || cells.get(tempo.get(i)).isDoorway() == true ){
				if(cells.get(tempo.get(i)).isDoorway() == true){
					targets.add(cells.get(tempo.get(i)));
				}

				if(targets.contains(cells.get(tempo.get(i))) != true){
					targets.add(cells.get(tempo.get(i)));		//add each adjCell to targets
				}
			}
			else{
				startTargets(tempo.get(i)/numColumns, tempo.get(i)%numColumns, (numSteps-1));   //recursive call
			}
			visited[tempo.get(i)] = false;    //visited[adjCell] = false
		}
	}
	public void calcTargets(int row, int column, int numSteps){

		visited = new boolean[adjMtx.size()];
		for(int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
		int index = calcIndex(row,column);
		targets = new HashSet<BoardCell>();
		visited[index] = true;

		startTargets(row, column, numSteps);
		while(targets.contains(cells.get(calcIndex(row,column))))
			targets.remove(cells.get(index));

	}
	public Set<BoardCell> getTargets(){
		return targets;
	}
	public LinkedList<Integer> getAdjList(int cell){
		LinkedList<Integer> bleh = new LinkedList<Integer>();
		bleh = adjMtx.get(cell);
		return bleh;
	}
}