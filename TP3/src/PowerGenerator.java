import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PowerGenerator extends Generator implements GeneratorInterface {

    private int n;          // Nombre toral de sommets
    private double exp;     // Exposant de la loi de puissance
    private int n1;         // Nombre de sommets de deg(1)

    public PowerGenerator(int n, double exp, int n1) {
        this.n = n;
        this.exp = exp;
        this.n1 = n1;
    }

    @Override
    public Graphe generate() {
        Graphe graphe = new Graphe();
        int[] degres = calcDegres();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < degres[i]; j++) {
                graphe.ajoutSommet(new Sommet(i));
            }
        }
        Collections.shuffle(graphe.getListeSommet());
        int fin = (graphe.getNbSommets() % 2 == 0)
                ? graphe.getNbSommets() : graphe.getNbSommets() - 1;
        for (int i = 0; i < fin; i++) {
            for (int j = i; j < graphe.getNbSommets(); j++) {
                Sommet s1 = graphe.getSommet(i);
                Sommet s2 = graphe.getSommet(j);
                if (s1 == s2 || (s1.getListeAdjacence().contains(j))) {
                    continue;
                }
                graphe.ajoutArete(s1, s2);
            }
        }
        return graphe;
    }

    private int[] calcDegres() {
        int[] degres = new int[n];
        int[] nbs = new int[n];
        int nb;
        int nPrime = 0, x = 1;
        int i = n - 1;

        while (nPrime <= n) {
            nb = (int) (n1 * Math.pow(x, exp));     // Clacul Nb(x)
            if (nPrime + nb > n) {                  // Si n' > n quitter
                break;
            }
            nPrime += nb;                           // Calcul de la somme des Nb(x)
            nbs[x] = nb;                            // Stocker le Nb pour remplir le tab degrés
            x++;
        }
        for (--x; x > 0; x--) {                     // x = plus grand degré calculé
            for (int k = 0; k < nbs[x]; k++) {      // nb[x] = Nombre de sommet de deg(x)
                degres[i] = x;
                i--;                                // i Index dans le tableau degré
            }
        }
        for (; i >= 0; i--) {                       // Comléter avec les degrés 0
            degres[i] = 0;
        }
        return shuffler(degres);
    }

    private int[] shuffler(int[] array) {
        Random random = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
        return array;
    }
}
