import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import java.util.concurrent.TimeUnit;

public final class Util{
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

