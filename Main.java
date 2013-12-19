public class Main {
    public static void main(String[] args) {
        if (args.length == 1){
            String fileName = args[0];
            Graph graph = Graph.fromFile(fileName);
            graph.toFile("start.gv");
            System.out.println("Cycles supprimés");
            graph.cycleDetectAndResolve();
            graph.toFile("dettesNoCycles.gv");
            System.out.println("\nFichier dettesNoCycles.gv contient la situation sans cycles.\n" +
                "Utilisez la commande : dot dettesNoCycles.gv -Tpng > noCycles.png" + 
                " pour créer l’image.\n");
            graph.resolveDebt();
            graph.toFile("dettesRemb.gv");
            System.out.println("\nFichier dettesRemb.gv contient la situation actuelle.\n" +
                "Utilisez la commande : dot dettesRemb.gv -Tpng > dettesRemb.png" + 
                " pour créer l’image.\n");
        }
        else {
            System.out.println("Ce fichier n'existe pas.");
        }
        
    }
    
}