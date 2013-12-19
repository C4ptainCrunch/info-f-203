public class Debt {
	private int amount;
	private Node from;
	private Node to;
	
	public Debt(Node from, Node to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
        from.addDebt(this);
        to.addCreance();
	}

	public Node getFrom() {
		return from;
	}

    public Node getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    public int amountSubstract(int amount) {
        this.amount -= amount;
        if(this.amount == 0){
            from.removeDebt(this);
            to.delCreance();
        }
        return this.amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
        if(this.amount == 0){
            from.removeDebt(this);
            to.delCreance();
        }
    }

    // Create a debt with Nodes name and take corresponding Nodes.
    public static Debt fromInfo(Graph graph, String from, String to, int amount) {
        return new Debt(graph.getNode(from), graph.getNode(to), amount);
    }

    public void resolveDebt() {
        int newAmount = from.pay(amount); 
        if(amount != newAmount) {
            System.out.print(String.format("%s (%d) -> %s", from.getName(), amount - newAmount, to.getName()));
            if(newAmount > 0)
                System.out.println(String.format("(Il reste %d à rembourser)", newAmount));
            else
                System.out.println("");
        }
        to.add(amount - newAmount);
        setAmount(newAmount);
        to.resolveDebt();
    }
}