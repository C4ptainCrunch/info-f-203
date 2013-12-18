import java.lang.Object;
import java.io.*;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class Graph {
    private HashMap<String,Node> nodes;

    public Graph(int size) {
        nodes = new HashMap<String,Node>();
    }

    public static Graph fromFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        Graph graph = null;
        int nodeNumber;
        try {
            reader = new BufferedReader(new FileReader(file));
            nodeNumber = Integer.parseInt(reader.readLine());
            graph = new Graph(nodeNumber);
            String buffer = null;
            int i = 0;
            Node node = null;
            String str[];
            while ((i < nodeNumber) && ((buffer = reader.readLine()) != null)) {
                str = new String[2];
                str = buffer.split(" ");
                node = new Node(str[0], Integer.parseInt(str[1]));
                graph.addNode(node);
                i++;
            }
            Debt debt = null;
            while ((buffer = reader.readLine()) != null) {                
                str = new String[3];
                str = buffer.split(" ");
                debt = Debt.fromInfo(graph, str[0], str[1], Integer.parseInt(str[2]));
                graph.addDebt(debt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    public void addNode(Node node){
        nodes.put(node.getName(), node);
    }

    public void addDebt(Debt debt){
        getNode(debt.getFrom().getName()).addDebt(debt);
        
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

    private cycleDetect(Callable<void> callback, Node node=null, stack) {
        if (node == null) {
            Iterator<Node>nodesIterator = nodes.values().iterator();
            if (nodesIterator.hasNext()) {
                node = nodesIterator.next();
            }
        }
        node.tag();
        for debt in node.debts():
            if debt not in stack:
                stack.push(debt)
                cycleDetect(callback, debt, stack)
                stack.pop()
            else:
                i = position = stack.pos(debt)
                minAmount = 0
                stop = false
                while i < stack.len && !stop:
                    arrete = stack.get(i)
                    if  arrete.amount == 0:
                        stop = true;
                    else:
                        if arrete.amount < minAmount || minAmount == 0:
                            minAmount = arrete.amount
                    i++
                if ! stop:
                    while position < stack.len:
                        arrete = stack.get(i)
                        if arrete.amountRemove(minAmount) == 0
                            arrete.getFrom().removeDebt(arrete)
                        position++




    }

    // private printResolveCycle(){
    //     resolveCycle();
    //     print "dfqsdf"
    // }

    // private resolveCycle(){
    //     //résoudre 
    // }
    
}