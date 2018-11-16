import java.util.ArrayList;
import java.util.Collections;

public class GNMGenerator extends Generator implements GeneratorInterface {

    private int n;
    private int m;

    public GNMGenerator(int n, int m) {
        this.n = n;     // Nombre de sommets
        this.m = m;     // Nombre d'arêtes
    }

    @Override
    public Graphe generate() {
        Graphe graphe = new Graphe();

        for (int i = 0; i < n; i++) {
            graphe.ajoutSommet(new Sommet(i));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graphe.ajoutArete(graphe.getSommet(i), graphe.getSommet(j));
            }
        }
        Collections.shuffle(graphe.getListeArete());
        graphe.setListeArete(graphe.getListeArete().subList(0, m));
        System.out.println(graphe.getNbSommets() + " " + graphe.getNbAretes());
        return graphe;
    }
}
