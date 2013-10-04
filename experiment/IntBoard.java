package experiment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class IntBoard {
	private int row, column, index;
	private LinkedList<LinkedList> adjList = new LinkedList<LinkedList>();
	//ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private Set<Integer> targets;
	
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
				LinkedList <Integer> indexAdjacencies = new <Integer> LinkedList();
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
	public void startTargets(int index, int numSteps){
		calcAdjacencies();
		visited = new boolean[adjList.size()];
		for(int i = 0; i < visited.length; i++){
			visited[i] = false;
		}

		targets = new HashSet<Integer>();
		visited[index] = true;
	
		calcTargets(index,numSteps);

	}
	public void calcTargets(int thisCell, int numSteps){

		LinkedList<Integer> tempo = new LinkedList<Integer>();
	
		tempo = adjList.get(thisCell);  // tempo is a LinkedList<Integer>	
		tempo = (LinkedList<Integer>) tempo.clone();
		for(int i = 0; i < tempo.size(); i++){
			if(visited[tempo.get(i)] == true){
				tempo.remove(i);             // if visited is true, remove this guy from tempo
			}
		}
		for(int i = 0; i < tempo.size(); i++){
			visited[tempo.get(i)] = true;    // set adjCell = true
			if(numSteps == 1){
				targets.add(tempo.get(i));		//add each adjCell to targets
			}
			else{
				calcTargets(tempo.get(i),(numSteps-1));   //recursive call
			}
			visited[tempo.get(i)] = false;    //visited[adjCell] = false
		}
	}
	public Set<Integer> getTargets(){
		return targets;
	}
	public LinkedList<Integer> getAdjList(int cell){
		return adjList.get(cell);
	}
	public int calcIndex(int row, int column){
		int index = row* 4 + column;
		return index;	
	}

}
