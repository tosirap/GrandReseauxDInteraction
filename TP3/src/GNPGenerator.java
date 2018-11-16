public class GNPGenerator extends Generator implements GeneratorInterface {

    private int n;          // Nombre de sommets
    private double p;       // Probabilité

    public GNPGenerator(int n, double p) {
        this.n = n;
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
        for (int i = 0; i < graphe.getNbSommets() - 1; i++) {
            for (int j = i + 1; j < graphe.getNbSommets(); j++) {
                double rAsFloat = Math.random();
                if (rAsFloat < p) {
                    graphe.ajoutArete(graphe.getSommet(i), graphe.getSommet(j));
                }
            }
        }
        System.out.println(graphe.getNbSommets() + " " + graphe.getNbAretes());
        return graphe;
    }
}
