import java.util.LinkedList;

public class MetaData{
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

