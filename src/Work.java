import java.util.Collections;

public class Work extends MetaData{
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

