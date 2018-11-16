import java.util.ArrayList;
import java.util.List;

public class Sommet {
    public int degre;                       // Degré du sommet
    public List<Sommet> listeAdjacence;     // Liste des voisins du sommet
    public boolean marque;                  // Si le sommet a été visité
    public int distance = -1;               // Distance au sommet x

    public Sommet() {                        // Constructeur
        this.degre = 0;
        this.listeAdjacence = new ArrayList<Sommet>();
    }

    public boolean ajoutSommet(Sommet sommet) {      // Ajout d'un sommet voisin à la liste
        if (!listeAdjacence.contains(sommet)) {      // si celui-ci n'y est pas déjà
            listeAdjacence.add(sommet);
            sommet.listeAdjacence.add(this);
            this.degre = this.listeAdjacence.size();
            return true;
        } else {
            return false;
        }
    }
}
