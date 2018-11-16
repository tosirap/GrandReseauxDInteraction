import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Graphe implements GrapheInterface {

    public HashMap<Integer, SommetInterface> listeSommets;
    private int nbAretes;
    private int nbSommet;
    int degreMax;

    public Graphe() {
        listeSommets = new HashMap<>();
        nbAretes = 0;
        nbSommet = 0;
        degreMax = 0;
    }

    @Override
    public void chargement(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split("\\s|\\t");
                if (splittedLine[0].equals("#")) {      // Ignore les commentaires
                    continue;
                }
                int n1 = Integer.parseInt(splittedLine[0]);
                int n2 = Integer.parseInt(splittedLine[1]);
                SommetInterface sommet1, sommet2;
                if (n1 != n2) {
                    SommetInterface s1, s2;
                    s1 = (this instanceof GrapheO) ? new SommetO() : new SommetNO();
                    s2 = (this instanceof GrapheO) ? new SommetO() : new SommetNO();

                    sommet1 = ((sommet1 = listeSommets.putIfAbsent(n1, s1)) == null)
                            ? listeSommets.get(n1) : sommet1;

                    sommet2 = ((sommet2 = listeSommets.putIfAbsent(n2, s2)) == null)
                            ? listeSommets.get(n2) : sommet2;

                    sommet1.ajoutSommet(sommet2);
                    this.nbAretes++;
                    degreMax = (sommet1.getListe().size() > degreMax) ? sommet1.getListe().size() : degreMax;
                    degreMax = (sommet2.getListe().size() > degreMax) ? sommet2.getListe().size() : degreMax;
                }
            }
            nbSommet = listeSommets.size();
            this.nbAretes /= 2;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    @Override
    public SommetInterface[] triSommetC() {      // retourne le tableau de sommets trié croissant
        SommetInterface[] sommets = new SommetInterface[nbSommet];
        this.listeSommets.values().toArray(sommets);
        Arrays.sort(sommets);
        return sommets;
    }

    @Override
    public SommetInterface[] triSommetD() {      // retourne le tableau de sommets trié décroissant
        SommetInterface[] sommets = new SommetInterface[nbSommet];
        this.listeSommets.values().toArray(sommets);
        Arrays.sort(sommets, Collections.reverseOrder());
        return sommets;
    }

    @Override
    public void bascule() {
        SommetInterface[] sommetsTries = this.triSommetD();

        int sommePartiel = 0;
        for (int b = 0; b < sommetsTries.length; b++) {
            sommePartiel += sommetsTries[b].getListe().size();
            double m2 = 2 * nbAretes;
            if ((double)b / nbSommet < (1 - (sommePartiel / m2))) {
                if (((double)(b + 1) / nbSommet) > (1 - ((sommePartiel + sommetsTries[b + 1].getListe().size()) / m2))) {
                    System.out.println("valeur d’équirépartition : " + ((double)b/nbSommet*100) + "%");
                    System.out.println("bascule : " + b + " sur " + nbSommet);
                    System.out.println("degré de bascule : " + sommetsTries[b].getListe().size());
                    break;
                }
            }
        }
    }
    @Override
    public GrapheInterface coeur() {
        int k = 0;
        SommetInterface[] st = this.triSommetC();

        List<SommetInterface> sommets =  new LinkedList<>(Arrays.asList(st));
        SommetInterface s;
        while (sommets.size() > 0) {
            if (sommets.get(0).getListe().size() >= k) {
                System.out.println(k+"-coeur restent " + sommets.size() + " sommets");
                k++;
                continue;
            }
            int i = 0;
            while (i < sommets.size()) {
                s = sommets.get(i);
                if (s.getListe().size() < k) {
                    s.supprSommet();
                    sommets.remove(s);
                    continue;
                }
                i++;
            }
            System.out.println(k+"-coeur restent " + sommets.size() + " sommets");
            sommets.sort(Comparator.comparingInt(o -> o.getListe().size()));
            k++;
        }
        return null;
    }


    
}
