package experiment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private int row, column, index;
	private LinkedList<LinkedList> adjList = new LinkedList<LinkedList>();
	//ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	private int maxRows;
	private int maxColumns;
	
	public IntBoard() {
	
	}
	public IntBoard(int maxRows, int maxColumns) {
		super();
		this.maxRows = maxRows;
		this.maxColumns = maxColumns;
	}
	public void calcAdjacencies(){
		index = 0;
		row = 0;
		column = 0;
		for(row = 0; row < maxRows; row++){
			for(column = 0; column < maxColumns; column++){
				LinkedList indexAdjacencies = new LinkedList();
				if(row != 0)
					indexAdjacencies.add(index-maxColumns);
				
				if(column != 0)
					indexAdjacencies.add(index-1);
					
				if(row != maxRows -1)
					indexAdjacencies.add(index+maxColumns);
				
				if(column != maxColumns -1)
					indexAdjacencies.add(index+1);
				index++;
				adjList.add(indexAdjacencies);
			}
		}
		
	}
	public void startTargets(int index, int steps){
		
	}
	public Set<Integer> getTargets(){
		
		return new HashSet<Integer>();
	}
	public LinkedList<Integer> getAdjList(int cell){
		
		
		return adjList.get(cell);
	}
	public int calcIndex(int row, int column){
		int index = row* 4 + column;
		
		return index;	
	}

}
