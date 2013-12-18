import java.util.Vector;

public class Node {
	private int balance;
	private String name;
	private Vector<Debt> debts;
	private boolean reached;

	public Node(String name, int balance) {
		this.name = name;
		this.balance = balance;
		reached = true;
		debts = new Vector<Debt>();
	}

	public String getName() {
		return name;
	}
	
	public int getBalance() {
		return balance;
	}

	public void addDebt(Debt debt) {
		debts.add(debt);
	}

	public boolean isHead() {
		return isHead;
	}

	public void tag() {
		reached = true;
	}

	public Vector<Debt> getDebts() {
		return debts;
	}

	public void removeDebt(Debt debt) {
		debts.remove(debt);
	}

	public String toDot() {
		String str = "";
		Debt debt;
		Node debtTo;
		for(int i=0; i < debts.size(); i++){
			debt = debts.get(i);
			debtTo = debt.getTo();
			str += String.format("\"%s\\n%d\" -> \"%s\\n%d\" [label=\"%d\"]%n",
				name, balance, debtTo.getName(), debtTo.getBalance(), debt.getAmount());
		}
		return str;
	}
}