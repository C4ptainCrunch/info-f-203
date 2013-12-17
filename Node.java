import java.util.Vector;

public class Node {
	private int balance;
	private String name;
	private Vector<Debt> debts;
	private boolean isHead;

	public Node(String name, int balance) {
		this.name = name;
		this.balance = balance;
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

	public String toDot() {
		String str = "";
		Debt debt;
		for(int i=0; i < debts.size(); i++){
			debt = debts.get(i);
			str += "\"" + name + "\n" + balance + "\" -> \"" + debt.getTo().getName() + "\n" + debt.getTo().getBalance() + "\" [label=" + debt.getAmount() + "];\n";
		}
		return str;
	}
}