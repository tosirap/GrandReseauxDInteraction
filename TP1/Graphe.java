import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graphe {
    public int nbSommets;       // Nombre de sommets dans le graphe
    public int nbAretes;        // Nombre d'arêtes dans le graphe
    public int plusGrandNum = -1;   // Plus grand numéro de sommet dans le graphe
    public HashMap<Integer, Sommet> hashMap;    // HashMap des sommets du graphe

    public Graphe() {       // Constructeur
        this.nbSommets = 0;
        this.nbAretes = 0;
        this.hashMap = new HashMap<>();
    }

    public int getMaxDeg() {        // Retourne le degré maximum d'un sommet
        int maxDeg = -1;
        Iterator i = this.hashMap.keySet().iterator();
        int cle;
        Sommet tmp;
        while (i.hasNext()) {
            cle = (int) i.next();
            tmp = (Sommet) this.hashMap.get(cle);
            if (tmp.degre > maxDeg) {
                maxDeg = tmp.degre;
            }
            tmp.marque = false;
        }
        return maxDeg;
    }

    public int getNbSommetsAccessible(Sommet sommet1) {
        int res = 0;
        ArrayList<Sommet> list = new ArrayList<>();
        list.add(sommet1);
        sommet1.marque = true;
        sommet1.distance = 0;
        while (!list.isEmpty()) {
            Sommet sommetTmp = list.get(0);
            list.remove(0);
            res++;
            for (Sommet sommet: sommetTmp.listeAdjacence) {
                if (!sommet.marque) {
                    list.add(sommet);
                    sommet.marque = true;
                    sommet.distance = sommetTmp.distance + 1;
                }
            }
        }
        return res;
    }
}