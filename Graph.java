import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Graph {
    private HashMap<String,Node> nodes;
    private int order;

    public Graph(int size) {
        nodes = new HashMap<String,Node>();
        order = size;
    }

    // Create a new graph from a file.
    public static Graph fromFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        Graph graph = null;
        
        try {
            int order;
            reader = new BufferedReader(new FileReader(file));
            order = Integer.parseInt(reader.readLine());
            graph = new Graph(order);
            
            graph.addNodesFromReader(reader);
            graph.addDebtsFromReader(reader);
            
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private void addNodesFromReader(BufferedReader reader) {
        String buffer = null;
        int i = 0;
        Node node = null;
        String output[];
        try{
            while ((i < order) && ((buffer = reader.readLine()) != null)) {
                output = new String[2];
                output = buffer.split(" ");
                node = new Node(output[0], Integer.parseInt(output[1]));
                addNode(node);
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addDebtsFromReader(BufferedReader reader){
        Debt debt = null;
        String buffer = null;
        String output[];
        try{
            while ((buffer = reader.readLine()) != null) {                
                output = new String[3];
                output = buffer.split(" ");
                debt = Debt.fromInfo(this, output[0], output[1], Integer.parseInt(output[2]));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toFile(String fileName) {
        try {
            PrintWriter file = new PrintWriter(fileName, "UTF-8");
            file.print(toDot());
            file.close();
        }
        catch (IOException ex){

        }
    }

    public void addNode(Node node){
        nodes.put(node.getName(), node);
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }
    public String toDot() {
        String output = "digraph plop {\n";
        Iterator<Node>nodesIterator = nodes.values().iterator();
        while(nodesIterator.hasNext()) {
            output += nodesIterator.next().toDot();
        }
        output += "}";
        return output;
    }

    public void cycleDetect(){
        Iterator<Node>nodesIterator = nodes.values().iterator();
        Node node;
        Vector<Debt> stack;
        Vector<Node> stackNodes;
        while (nodesIterator.hasNext()) {
            node = nodesIterator.next();
            if (!node.isReached()){
                //System.out.println("dfsfd");
                stack = new Vector<Debt>();
                stackNodes = new Vector<Node>();
                cycleDetect(node, stack, stackNodes);
            }
        }
    }

    private void cycleDetect(Node node, Vector<Debt> stack, Vector<Node> stackNodes) {
        Vector<Debt>debts = node.getDebts();
        Debt arrete = null;
        int minAmount;
        boolean stop;
        int position;
        int i;
        int j;
        Debt debt = null;
        node.tag();
        String reduce = "";

        for (j = 0; j < debts.size(); j++) {
            debt = debts.get(j);
            if(debt != null){
                if (stackNodes.lastIndexOf(node) == -1) {
                    stack.add(debt);
                    stackNodes.add(node);

                    cycleDetect(debt.getTo(), stack, stackNodes);
                    stack.remove(stack.size() - 1);
                    stackNodes.remove(stackNodes.size() - 1);
                }
                else {
                    position = stackNodes.lastIndexOf(node);
                    minAmount = 0;
                    stop = false;
                    reduce = "";
                    for (i = position; i < stack.size() && !stop; i++) {
                        arrete = stack.get(i);
                        if (arrete.getAmount() == 0) {
                            stop = true;
                        }
                        else {
                            if (arrete.getAmount() < minAmount || minAmount == 0) {
                                minAmount = arrete.getAmount();
                            }
                            reduce += String.format("%s (%d) -> ", arrete.getFrom().getName(), arrete.getAmount());
                        }
                    }
                    if (!stop) {
                        System.out.println("\nRéduction de " + minAmount);
                        reduce += String.format("%s (%d) -> ...", debt.getFrom().getName(), debt.getAmount());
                        System.out.println(reduce);

                        reduce = "";
                        for (; position < stack.size(); position++) {
                            arrete = stack.get(position);
                            if (arrete.amountSubstract(minAmount) == 0){
                                if(reduce.length() > 0)
                                    reduce += arrete.getFrom().getName() + "\n";
                            }
                            else{
                                reduce += String.format("%s (%d) -> ", arrete.getFrom().getName(), arrete.getAmount());
                            }
                        }
                        if(!reduce.endsWith("\n"))
                            reduce += node.getName();
                        else
                            reduce = reduce.substring(0, reduce.length()-1);
                        System.out.println("Nouvelle situation:");
                        System.out.println(reduce);
                    }
                }
            }
        }
    }


    public void resolveDebt() {
        Iterator<Node>nodesIterator = nodes.values().iterator();
        Node node;
        while(nodesIterator.hasNext()) {
            node = nodesIterator.next();
            // System.out.println(node);
            if(node.isHead()){
                node.resolveDebt();
            }
        }
    }
}