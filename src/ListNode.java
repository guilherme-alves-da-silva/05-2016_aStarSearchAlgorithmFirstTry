import java.util.Comparator;

public class ListNode extends Node implements Comparator<ListNode>{
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

