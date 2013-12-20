import java.util.Vector;

// Proxification of a Vector<Debt> to provide a tweaked stack.
public class DebtStack {
	private Vector<Debt> vect;

	public DebtStack(){
		vect = new Vector<Debt>();
	}

	// Return the first index (begining on top of the stack)
	// where the Debt source is `node`
	public int lastIndexOfNode(Node node){
		int index = -1;
		int i = size();
		while((i > 0) && (index == -1)) {
			i--;
			if(vect.get(i).getFrom() == node) {
				index = i;
			}

		}

		return index;
	}

	public int size(){
		return vect.size();
	}

	public Debt get(int i){
		return vect.get(i);
	}

	public void push(Debt debt){
		vect.add(debt);
	}

	public void pop(){
		vect.remove(vect.size() - 1);
	}

}