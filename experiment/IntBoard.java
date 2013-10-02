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
	private LinkedList<Integer> tempo = new LinkedList<Integer>();
	
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
	public void startTargets(int index, int numSteps){
		
		visited = new boolean[adjList.size()];
		for(int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
		calcTargets(index,numSteps);
		
		
	}
	
	public void calcTargets(int thisCell, int numSteps){
		tempo = adjList.get(thisCell);
		int oldSize = tempo.size();
		visited[thisCell] = true;

			for(int i = 0; i < tempo.size(); i++){
				if(visited[tempo.get(i)]==true){
					System.out.println(tempo.get(i));
					tempo.remove(i);
				}
			}
			for(int i = 0; i < tempo.size(); i++){
				System.out.println(tempo.get(i));
				visited[tempo.get(i)] = true;
				if(numSteps == 1){
					targets.add(tempo.get(i));
				}
				else{
					calcTargets(tempo.get(i), numSteps--);
				}
				visited[tempo.get(i)] = false;
			}
			
		/*if(numSteps > 1){
			for(int i = 0; i < tempo.size(); i++){
				if(visited[tempo.get(i)] == false){
					calcTargets(tempo.get(i), numSteps--);
				}
			}
		}
		else{
			targets = new HashSet<Integer>();
			for(int i = 0; i < oldSize; i++){
					targets.add(tempo.get(i));
					System.out.println(tempo.get(i));
				}*/
				
			
		//}

/*		adjMtx.get(visited);
		for (){
			visited[adjCell] = true;
			if(numSteps ==1)
				Targets.add(adjCell);
			else
				calcTargets(adjCell, numSteps--);
			visited[adjCell] = false;
			
			
		}*/
		
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
