public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            String fileName = args[0];
            Graph graph = Graph.fromFile(fileName);
            graph.toFile("start.gv");
            graph.cycleDetectAndResolve();
            graph.toFile("dettesNoCycles.gv");
        }
        else {
            System.out.println("Boum !");
        }
        
    }
    
}