import java.lang.Object;
import java.io.*;

public class Graph {
    private Node[] nodes;

    public Graph(int size) {
        nodes = new Node[size];
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
            while (((buffer = reader.readLine()) != null) && (i <= nodeNumber)) {
                str = new String[2];
                str = buffer.split(" ");
                node = new Node(str[0], Integer.parseInt(str[1]));
                graph.addNode(node);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    public void addNode(Node node){
        
    }
    
}