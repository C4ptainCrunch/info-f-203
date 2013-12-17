public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            String fileName = args[0];
            Graph graph = Graph.fromFile(fileName);
        }
        else {
            System.out.println("Boum !");
        }
        
    }
    
}