public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            String fileName = args[0];
            Graph graph = Graph.fromFile(fileName);
            graph.toFile("start.gv");
            graph.cycleDetect();
            graph.toFile("dettesNoCycles.gv");
            System.out.println(graph.toDot());
        }
        else {
            System.out.println("Boum !");
        }
        
    }
    
}