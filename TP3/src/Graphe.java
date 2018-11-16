import java.util.ArrayList;
import java.util.List;


public class Graphe {
    private List<Sommet> listeSommet;
    private List<Arete> listeArete;

    public Graphe() {
        listeSommet = new ArrayList<>();
        listeArete = new ArrayList<>();
    }

    public List<Sommet> getListeSommet() {
        return listeSommet;
    }

    public List<Arete> getListeArete() {
        return listeArete;
    }

    public void setListeArete(List<Arete> listeArete) {
        this.listeArete = listeArete;
    }

    public void ajoutArete(Sommet s1, Sommet s2) {
        if (s1 == s2) {
            return;
        }
        s1.ajoutSommet(s2);
        Arete arete = new Arete(s1.getId(), s2.getId());
        listeArete.add(arete);
    }

    public void ajoutSommet(Sommet s) {
        this.listeSommet.add(s);
    }

    public Sommet getSommet(int id) {
        return this.listeSommet.get(id);
    }

    public int getNbSommets() {
        return this.listeSommet.size();
    }

    public int getNbAretes() {
        return this.listeArete.size();
    }

    public List<Sommet> getNonVoisins(Sommet sommet) {
        List<Sommet> nonVoisins = new ArrayList<>(listeSommet);
        nonVoisins.removeAll(sommet.getListeAdjacence());
        return nonVoisins;
    }
}
