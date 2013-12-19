import java.util.Vector;

public class Node {
	private int balance;
	private String name;
	private Vector<Debt> debts;
	private boolean reached;
	private int creanceCounter;

	public Node(String name, int balance) {
		this.name = name;
		this.balance = balance;
		reached = false;
		debts = new Vector<Debt>();
		creanceCounter = 0;
	}

	public String getName() {
		return name;
	}
	
	public int getBalance() {
		return balance;
	}

	public boolean isHead() {
		return creanceCounter == 0;
	}

	public void addCreance() {
		creanceCounter++;
	}

	public void delCreance() {
		creanceCounter--;
	}

	public void addDebt(Debt debt) {
		debts.add(debt);
	}

	public void tag() {
		reached = true;
	}

	public boolean isReached() {
		return reached;
	}

	public Vector<Debt> getDebts() {
		return debts;
	}

	public void removeDebt(Debt debt) {
		debts.set(debts.indexOf(debt), null);
	}

	public String toDot() {
		String output = "";
		Debt debt;
		Node debtTo;

		// read through every debt the node has
		for(int i=0; i < debts.size(); i++) {
			debt = debts.get(i);
			// if debt is solved, it is setted to null
			if(debt != null) {
				debtTo = debt.getTo();
				// format a string like that : "CI\n75" -> "CJC\n40" [label="15"]
				//								"source\nsource-money" -> "destination\ndestination-money" [label="debt-amount"]
				output += String.format("\"%s\\n%d\" -> \"%s\\n%d\" [label=\"%d\"]%n",
					name, balance, debtTo.getName(), debtTo.getBalance(), debt.getAmount());
			}
		}
		return output;
	}

	public void resolveDebt() {
		Debt debt;
		for(int i = 0; i < debts.size() && balance > 0; i++){
			debt = debts.get(i);
			if(debt != null){
			System.out.println(debt.getFrom().name + " -> " + debt.getTo().name);
				debt.resolveDebt();
			}
		}
	}

	public int pay(int amount){
		if(amount > balance){
			amount -= balance;
			balance = 0;
		}
		else{
			balance -= amount;
			amount = 0;
		}
		return amount;
	}
	public void add(int amount) {
		balance +=amount;
	}
}