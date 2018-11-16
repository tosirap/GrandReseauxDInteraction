import java.util.ArrayList;

public class Sommet {
    private int id;
    private ArrayList<Sommet> listeAdjacence;

    public Sommet(int id) {
        this.id = id;
        listeAdjacence = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Sommet> getListeAdjacence() {
        return listeAdjacence;
    }

    public void ajoutSommet(Sommet sommet) {
        if (this == sommet) {
            return;
        }
        if (!this.listeAdjacence.contains(sommet)) {
            this.listeAdjacence.add(sommet);
            sommet.ajoutSommet(this);
        }
    }

    public void removeSommet(Sommet s2) {
        this.listeAdjacence.remove(s2);
        s2.listeAdjacence.remove(this);
    }

    /*public int getDegre() {
        return listeAdjacence.size();
    }

    public Sommet getVoisin(int i) {
        return this.listeAdjacence.get(i);
    }*/
}
