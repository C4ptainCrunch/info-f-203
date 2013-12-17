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
            while (((buffer = reader.readLine()) != null) && (i < nodeNumber)) {
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
                debt = new Debt(str[0], str[1], Integer.parseInt(str[2]));
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
        String output = "graph plop {\n";
        Iterator<Node>nodesIterator = nodes.values().iterator();
        while(nodesIterator.hasNext()) {
            output += nodesIterator.next().toDot();
            output += "\n";
        }
        output += "}";
        return output;
    }
    
}