
public class Out extends MetaData{
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
