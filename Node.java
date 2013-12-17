import java.util.Vector;

public class Node {
	private int balance;
	private String name;
	private Vector<Debt> debts;
	private boolean isHead;

	public Node(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}
	
}