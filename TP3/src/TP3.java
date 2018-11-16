import java.io.File;
import java.io.FileWriter;

public class TP3 {
    public static final String DOT = "dot";
    public static final String TXT = "txt";

    public static void main(String[] args) {


        if(args.length==0){
            GeneratorInterface generator = null;
            Graphe graphe;
            String sortie = "sortie";

            generator = new PowerGenerator(100,2.1,50);

            graphe = generator.generate();
            generator.exportToFile(graphe, sortie, DOT);
            generator.exportToFile(graphe, sortie, TXT);
            return;
        }
        String commande, sortie, param1, param2, param3;
        commande = sortie = param1 = param2 = param3 = null;
        if (args.length >= 4) {
            commande = args[0];
            sortie = args[1];
            param1 = args[2];
            param2 = args[3];
            param3 = args.length >= 5 ? args[4] : null;
        } else {
            System.out.println("Erreurs Arguments");
            System.exit(-1);
        }
        GeneratorInterface generator = null;
        Graphe graphe;
        switch (commande) {
            case "GnP": //OK
                generator = new GNPGenerator(Integer.parseInt(param1), Double.parseDouble(param2));
                break;
            case "GnM": //OK
                generator = new GNMGenerator(Integer.parseInt(param1), Integer.parseInt(param2));
                break;
            case "WS":
                generator = new WSGenerator(Integer.parseInt(param1), Integer.parseInt(param2), Double.parseDouble(param3));
                break;
            case "Power":
                generator = new PowerGenerator(Integer.parseInt(param1), Double.parseDouble(param2), Integer.parseInt(param3));
                break;
            default:
                System.out.println("Erreur dans la commande!");
        }
        if (generator == null) {
            System.exit(-1);
        }
        graphe = generator.generate();
        generator.exportToFile(graphe, sortie, DOT);
        generator.exportToFile(graphe, sortie, TXT);

    }
}
