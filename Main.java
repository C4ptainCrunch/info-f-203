public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            String fileName = args[0];
            Graph graph = Graph.fromFile(fileName);
            graph.toFile("start.dot");
            graph.cycleDetect();
            System.out.println(graph.toDot());
        }
        else {
            System.out.println("Boum !");
        }
        
    }
    
}