import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

class Node{
	private int parentLine=0;
	private int parentColumn=0;
	private int movementCost=0;
	private double heuristic=0.0;
	protected double fValue=0.0;
	private boolean obstacle=false;

	public int getParentLine(){
		return this.parentLine;
	}
	public void setParentLine(int value){
		this.parentLine=value;
	}
	public int getParentColumn(){
		return this.parentColumn;
	}
	public void setParentColumn(int value){
		this.parentColumn=value;
	}
	public int getMovementCost(){
		return this.movementCost;
	}
	public void setMovementCost(int value){
		this.movementCost=value;
	}
	public double getHeuristic(){
		return this.heuristic;
	}
	public void setHeuristic(double value){
		this.heuristic=value;
	}
	public Double getFValue(){
		return this.fValue;
	}
	public void setFValue(){
		this.fValue=getMovementCost()+getHeuristic();
	}
	public boolean getObstacle(){
		return this.obstacle;
	}
	public void setObstacle(boolean value){
		this.obstacle=value;
	}
}

class ListNode extends Node implements Comparator<ListNode>{
	private int line=0;
	private int column=0;

	public int getLine(){
		return this.line;
	}
	public void setLine(int value){
		this.line=value;
	}
	public int getColumn(){
		return this.column;
	}
	public void setColumn(int value){
		this.column=value;
	}
	public void setFValue(){
		super.setFValue();
		System.out.println(super.fValue);
		Out.printGraph(line, column);
	}
	public void setFValue(double value){
		super.fValue=value;
	}
	@Override
	public int compare(ListNode a, ListNode b){
		return a.getFValue().compareTo(b.getFValue());
	}
}

class MetaData{
	static Node graph[][]=new Node[11][11];
	static int startPoint[]={10, 3};
	static int goalPoint[]={1, 5};
	static LinkedList<ListNode> openList=new LinkedList<ListNode>();
	static LinkedList<ListNode> closedList=new LinkedList<ListNode>();
	static LinkedList<ListNode> shortestPath=new LinkedList<ListNode>();

	public MetaData(){
		for(int i=1;i<graph.length;i++){
			for(int j=1;j<graph[0].length;j++){
				graph[i][j]=new Node();
			}
		}

		graph[6][2].setObstacle(true);
		graph[6][3].setObstacle(true);
		graph[6][4].setObstacle(true);
		graph[6][5].setObstacle(true);
		graph[6][6].setObstacle(true);
		graph[6][7].setObstacle(true);
		graph[6][8].setObstacle(true); //midwall
		graph[7][2].setObstacle(true);
		graph[8][2].setObstacle(true);
		graph[9][2].setObstacle(true);
		graph[10][2].setObstacle(true);
		graph[7][8].setObstacle(true);
		graph[8][8].setObstacle(true);
		graph[9][8].setObstacle(true);
		graph[2][2].setObstacle(true);
		graph[2][3].setObstacle(true);
		graph[2][4].setObstacle(true);
		graph[2][5].setObstacle(true);
		graph[2][6].setObstacle(true);
		graph[2][7].setObstacle(true);
		graph[2][8].setObstacle(true);
		graph[2][9].setObstacle(true);
//		graph[2][1].setObstacle(true);
		graph[3][5].setObstacle(true);
/*
		graph[2][1].setObstacle(true);
		graph[2][2].setObstacle(true);
		graph[2][3].setObstacle(true);
		graph[2][4].setObstacle(true);
		graph[2][5].setObstacle(true);
		graph[2][6].setObstacle(true);
		graph[2][7].setObstacle(true);
		graph[2][8].setObstacle(true);
		graph[2][9].setObstacle(true);
		graph[9][1].setObstacle(true);
		graph[9][2].setObstacle(true);
		graph[9][3].setObstacle(true);
		graph[9][4].setObstacle(true);
		graph[9][5].setObstacle(true);
		graph[9][6].setObstacle(true);
		graph[9][7].setObstacle(true);
		graph[9][8].setObstacle(true);
		graph[9][9].setObstacle(true);
*/
	}
}

class Work extends MetaData{
	private static int line=0;
	private static int column=0;

	private int[] getLowestFunctionFromOpenList(){
		int data[]=new int[5];

		Collections.sort(openList, new ListNode());
		data[0]=openList.get(0).getLine();
		data[1]=openList.get(0).getColumn();
		data[2]=openList.get(0).getParentLine();
		data[3]=openList.get(0).getParentColumn();
		data[4]=openList.get(0).getMovementCost();
		openList.pop();

		Out.printGraph(data[0], data[1]);
		return data;
	}

	private void updateGraphNode(int lineTarget, int columnTarget, int movementCost){
		graph[lineTarget][columnTarget].setParentLine(line);
		graph[lineTarget][columnTarget].setParentColumn(column);
		graph[lineTarget][columnTarget].setMovementCost(movementCost);
		graph[lineTarget][columnTarget].setFValue();
	}

	private void newListNode(int lineTarget, int columnTarget, int movementCost){
		double localHeuristic=Util.calculateHeuristic(lineTarget, columnTarget);

		graph[lineTarget][columnTarget].setHeuristic(localHeuristic);
		updateGraphNode(lineTarget, columnTarget, movementCost);

		//add to the openList
		openList.push(new ListNode());
		openList.get(0).setLine(lineTarget);
		openList.get(0).setColumn(columnTarget);
		openList.get(0).setParentLine(line);
		openList.get(0).setParentColumn(column);
		openList.get(0).setMovementCost(movementCost);
		openList.get(0).setHeuristic(localHeuristic);
		openList.get(0).setFValue();
	}

	private void updateListNode(int lineTarget, int columnTarget, int newMovementCost){
		updateGraphNode(lineTarget, columnTarget, newMovementCost);

		for(int i=0;i<openList.size();i++){
			if(openList.get(i).getLine()==lineTarget&&openList.get(i).getColumn()==columnTarget){
				openList.get(i).setParentLine(line);
				openList.get(i).setParentColumn(column);
				openList.get(i).setMovementCost(newMovementCost);
				openList.get(i).setFValue();
				break;
			}
		}
	}

	private void addToClosedList(int[] data){
		closedList.push(new ListNode());
		closedList.get(0).setLine(data[0]);
		closedList.get(0).setColumn(data[1]);
		closedList.get(0).setParentLine(data[2]);
		closedList.get(0).setParentColumn(data[3]);
		closedList.get(0).setMovementCost(data[4]);
	}

	private static boolean checkNorthBorder(){
		return line>1;
	}
	private static boolean checkNorthEastBorder(){
		return line>1&&column<graph[0].length-1;
	}
	private static boolean checkEastBorder(){
		return column<graph[0].length-1;
	}
	private static boolean checkSouthEastBorder(){
		return line<graph.length-1&&column<graph[0].length-1;
	}
	private static boolean checkSouthBorder(){
		return line<graph.length-1;
	}
	private static boolean checkSouthWestBorder(){
		return line<graph.length-1&&column>1;
	}
	private static boolean checkWestBorder(){
		return column>1;
	}
	private static boolean checkNorthWestBorder(){
		return line>1&&column>1;
	}

	private static boolean checkNewMovementCost(int lineTarget, int columnTarget, int cost){
		return graph[line][column].getMovementCost()+cost<
			graph[lineTarget][columnTarget].getMovementCost();
	}

	private void lookAround(){
		if(checkNorthBorder()&&!graph[line-1][column].getObstacle()){ //n
			if(!Util.openListContains(line-1, column)&&!Util.closedListContains(line-1, column)){
				newListNode(line-1, column, graph[line][column].getMovementCost()+10);
			}
			else if(Util.openListContains(line-1, column)&&checkNewMovementCost(line-1, column, 10)){
				updateListNode(line-1, column, graph[line][column].getMovementCost()+10);
			}
		}

		if(checkNorthEastBorder()&&!graph[line-1][column+1].getObstacle()){ //ne
			if(!Util.openListContains(line-1, column+1)&&!Util.closedListContains(line-1, column+1)){
				newListNode(line-1, column+1, graph[line][column].getMovementCost()+14);
			}
			else if(Util.openListContains(line-1, column+1)&&checkNewMovementCost(line-1, column+1, 14)){
				updateListNode(line-1, column+1, graph[line][column].getMovementCost()+14);
			}
		}

		if(checkEastBorder()&&!graph[line][column+1].getObstacle()){ //e
			if(!Util.openListContains(line, column+1)&&!Util.closedListContains(line, column+1)){
				newListNode(line, column+1, graph[line][column].getMovementCost()+10);
			}
			else if(Util.openListContains(line, column+1)&&checkNewMovementCost(line, column+1, 10)){
				updateListNode(line, column+1, graph[line][column].getMovementCost()+10);
			}
		}

		if(checkSouthEastBorder()&&!graph[line+1][column+1].getObstacle()){ //se
			if(!Util.openListContains(line+1, column+1)&&!Util.closedListContains(line+1, column+1)){
				newListNode(line+1, column+1, graph[line][column].getMovementCost()+14);
			}
			else if(Util.openListContains(line+1, column+1)&&checkNewMovementCost(line+1, column+1, 14)){
				updateListNode(line+1, column+1, graph[line][column].getMovementCost()+14);
			}
		}

		if(checkSouthBorder()&&!graph[line+1][column].getObstacle()){ //s
			if(!Util.openListContains(line+1, column)&&!Util.closedListContains(line+1, column)){
				newListNode(line+1, column, graph[line][column].getMovementCost()+10);
			}
			else if(Util.openListContains(line+1, column)&&checkNewMovementCost(line+1, column, 10)){
				updateListNode(line+1, column, graph[line][column].getMovementCost()+10);
			}
		}

		if(checkSouthWestBorder()&&!graph[line+1][column-1].getObstacle()){ //sw
			if(!Util.openListContains(line+1, column-1)&&!Util.closedListContains(line+1, column-1)){
				newListNode(line+1, column-1, graph[line][column].getMovementCost()+14);
			}
			else if(Util.openListContains(line+1, column-1)&&checkNewMovementCost(line+1, column-1, 14)){
				updateListNode(line+1, column-1, graph[line][column].getMovementCost()+14);
			}
		}

		if(checkWestBorder()&&!graph[line][column-1].getObstacle()){ //w
			if(!Util.openListContains(line, column-1)&&!Util.closedListContains(line, column-1)){
				newListNode(line, column-1, graph[line][column].getMovementCost()+10);
			}
			else if(Util.openListContains(line, column-1)&&checkNewMovementCost(line, column-1, 10)){
				updateListNode(line, column-1, graph[line][column].getMovementCost()+10);
			}
		}

		if(checkNorthWestBorder()&&!graph[line-1][column-1].getObstacle()){ //nw
			if(!Util.openListContains(line-1, column-1)&&!Util.closedListContains(line-1, column-1)){
				newListNode(line-1, column-1, graph[line][column].getMovementCost()+14);
			}
			else if(Util.openListContains(line-1, column-1)&&checkNewMovementCost(line-1, column-1, 14)){
				updateListNode(line-1, column-1, graph[line][column].getMovementCost()+14);
			}
		}
	}

	public void search(){
		int nodeData[]={startPoint[0], startPoint[1], 0, 0, 0};
		addToClosedList(nodeData);

		while(!Util.closedListContains(goalPoint[0], goalPoint[1])){
			line=nodeData[0];
			column=nodeData[1];
			lookAround();
			nodeData=getLowestFunctionFromOpenList();
			addToClosedList(nodeData);
		}
	}

	public void buildShortestPath(){
		int index=0;

		do{
			shortestPath.push(new ListNode());
			shortestPath.get(0).setLine(closedList.get(index).getLine());
			shortestPath.get(0).setColumn(closedList.get(index).getColumn());
			shortestPath.get(0).setParentLine(closedList.get(index).getParentLine());
			shortestPath.get(0).setParentColumn(closedList.get(index).getParentColumn());
			shortestPath.get(0).setMovementCost(closedList.get(index).getMovementCost());
			shortestPath.get(0).setHeuristic(closedList.get(index).getHeuristic());
			shortestPath.get(0).setFValue(closedList.get(index).getFValue());

			Out.printGraph(shortestPath.get(0).getLine(), shortestPath.get(0).getColumn());

			index=Util.findNodeInsideClosedList(shortestPath.get(0).getParentLine(),
					shortestPath.get(0).getParentColumn());

		}while(shortestPath.get(0).getLine()!=startPoint[0]||
				shortestPath.get(0).getColumn()!=startPoint[1]);
	}
}

final class Util{
	private Util(){}
	public static double calculateHeuristic(int line, int column){
		return (sqrt(pow(line-MetaData.goalPoint[0], 2)+pow(column-MetaData.goalPoint[1], 2)))*10;
	}

	//used just to print the graph, not needed for the algorithm to work
	public static boolean shortestPathListContains(int line, int column){
		boolean found=false;

		for(int i=0;i<MetaData.shortestPath.size();i++){
			if(MetaData.shortestPath.get(i).getLine()==line&&
					MetaData.shortestPath.get(i).getColumn()==column)
			{
				found=true;
				break;
			}
		}

		return found;
	}
	public static boolean openListContains(int line, int column){
		boolean found=false;

		for(int i=0;i<MetaData.openList.size();i++){
			if(MetaData.openList.get(i).getLine()==line&&
					MetaData.openList.get(i).getColumn()==column)
			{
				found=true;
				break;
			}
		}

		return found;
	}
	public static boolean closedListContains(int line, int column){
		boolean found=false;

		for(int i=0;i<MetaData.closedList.size();i++){
			if(MetaData.closedList.get(i).getLine()==line&&
					MetaData.closedList.get(i).getColumn()==column)
			{
				found=true;
				break;
			}
		}

		return found;
	}
	public static int findNodeInsideClosedList(int line, int column){
		int index=-1;

		for(int i=0;i<MetaData.closedList.size();i++){
			if(MetaData.closedList.get(i).getLine()==line&&
					MetaData.closedList.get(i).getColumn()==column)
			{
				index=i;
				break;
			}
		}

		return index;
	}
	public static void delay(){
		boolean interrupted=false;

		do{
			interrupted=false;

			try{
				TimeUnit.MILLISECONDS.sleep(100);
			}
			catch(InterruptedException e){
				System.out.println("interrupted.");
				interrupted=true;
				Thread.currentThread().interrupt();
			}
		}while(interrupted);
	}
}

class Out extends MetaData{
	public static void printGraph(int currentL, int currentC){
		System.out.println("\n        1   2   3   4   5   6   7   8   9   10\n\n");

		for(int i=1;i<graph.length;i++){
			System.out.printf(" %2d  ", i);

			for(int j=1;j<graph[0].length;j++){
				if(Util.shortestPathListContains(i, j)){
					System.out.print("   #");
				}
				else if(Util.openListContains(i, j)){
					System.out.print("   o");
				}
				else if(Util.closedListContains(i, j)){
					System.out.print("   c");
				}
				else if(i==startPoint[0]&&j==startPoint[1]){
					System.out.print("   s");
				}
				else if(i==goalPoint[0]&&j==goalPoint[1]){
					System.out.print("   e");
				}
				else if(i==currentL&&j==currentC){
					System.out.print("   f");
				}
				else if(graph[i][j].getObstacle()){
					System.out.print("   X");
				}
				else{
					System.out.print("   -");
				}
			}
			System.out.println("\n");
		}
		System.out.println();
		Util.delay();
	}
}

class Main{
	public static void main(String args[]){
		Work a=new Work();
		Out.printGraph(0, 0);
		a.search();
		Out.printGraph(0, 0);
		a.buildShortestPath();
	}
}
