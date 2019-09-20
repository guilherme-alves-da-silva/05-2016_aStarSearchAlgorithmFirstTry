
public class Node{
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

