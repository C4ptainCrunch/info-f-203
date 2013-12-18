import java.util.Vector;

public class Node {
	private int balance;
	private String name;
	private Vector<Debt> debts;
	private boolean isHead;

	public Node(String name, int balance) {
		this.name = name;
		this.balance = balance;
		isHead = true;
		debts = new Vector<Debt>();
	}

	public String getName() {
		return name;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}

	public void addDebt(Debt debt) {
		debts.add(debt);
	}

	public boolean isHead() {
		return isHead;
	}

	public String toDot() {
		String str = "";
		Debt debt;
		Node debtTo;
		for(int i=0; i < debts.size(); i++){
			debt = debts.get(i);
			debtTo = debt.getTo()
			str += String.format("\"%s %d\" -> \"%s %d\" [label=\"%d\"]%n",name, balance, debtTo.getName(), debtTo.getBalance(), debt.getAmount());
		}
		return str;
	}
}