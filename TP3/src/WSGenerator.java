import java.util.List;
import java.util.Random;

public class WSGenerator extends Generator implements GeneratorInterface {

    private int n;          // Nombre de sommets
    private int k;          // 1/2 Degré des sommets
    private double p;       // Probabilité de rebranchement

    public WSGenerator(int n, int k, double p) {
        this.n = n;
        this.k = k;
        this.p = p;
    }

    @Override
    public Graphe generate() {
        Graphe graphe = new Graphe();
        // Création des sommets
        for (int i = 0; i < n; i++) {
            graphe.ajoutSommet(new Sommet(i));
        }
        // Création des arêtes
        for (Sommet s : graphe.getListeSommet()) {
            for (int i = 1; i <= k; i++) {
                Sommet voisin = graphe.getSommet((s.getId() + i) % n);
                graphe.ajoutArete(s, voisin);
            }
        }
        // Rebranchement
        for (int i = 0; i < k; i++) {                   // K tours de rebranchement
            for (Arete a: graphe.getListeArete()) {     // Pour chaque arête
                if (Math.random() < p) {                // Test de rebranchement
                    Sommet s1 = graphe.getSommet(a.getSommetID1());     // Récupérer les deux sommets
                    Sommet s2 = graphe.getSommet(a.getSommetID2());
                    List<Sommet> nonVoisins = graphe.getNonVoisins(s1);
                    nonVoisins.add(s2);
                    s1.removeSommet(s2);                // Débrancher
                    Sommet nouveauVoisin = nonVoisins.get(new Random().nextInt(nonVoisins.size()));
                    s1.ajoutSommet(nouveauVoisin);
                    a.setSommetID2(nouveauVoisin.getId());
                }
            }
        }
        return graphe;
    }
}
