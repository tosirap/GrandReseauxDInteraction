
public class Main {

    public static void main(String[] args) {
        String oriente = "noriente";
        if(args.lenght>=3 && args[2].equals("oriente")){
            oriente = "oriente";
        }
        GrapheInterface graphe;
        if (oriente.equals("oriente")) {
            graphe = new GrapheO();
        } else {
            graphe = new GrapheNO();
        }
        graphe.chargement("roadNet-CA.txt");
        graphe.coeur();

    }
}
