public class Debt {
	private int amount;
	private Node from;
	private Node to;
	
	public Debt(Node from, Node to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
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
        }
        return this.amount;
    }

    // Create a debt with Node and take corresponding Nodes.
    public static Debt fromInfo(Graph graph, String from, String to, int amount) {
        return new Debt(graph.getNode(from), graph.getNode(to), amount);
    }
}