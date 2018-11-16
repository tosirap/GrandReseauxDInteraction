import java.io.BufferedReader;
import java.io.FileReader;

public class TP1 {

    public static void main(String[] args) {
        int doublon = 0;            // Nombre de doublons
        int boucleIgnore = 0;       // Nombre de boucles
        if (args.length != 3) {
            System.out.println("Veuillez entrez les arguments dans l'ordre suivant");
            System.out.println("<Nom du fichier> <Numéro du premier sommet> <Numéro du deuxième sommet>");
            System.exit(-1);
        }
        String fileName = args[0];
        Graphe graphe = new Graphe();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split("\\s|\\t");
                if (splittedLine[0].equals("#")) {      // Ignore les commentaires
                    continue;
                }
                int s1 = Integer.parseInt(splittedLine[0]);
                int s2 = Integer.parseInt(splittedLine[1]);
                Sommet sommet1, sommet2;

                if ((sommet1 = graphe.hashMap.putIfAbsent(s1, new Sommet())) == null) {
                    // Le sommet n'existait pas et a été inséré puis récupéré
                    sommet1 = graphe.hashMap.get(s1);
                    // Recheche du plus grand numéro de sommet
                    if (graphe.plusGrandNum < s1) {
                        graphe.plusGrandNum = s1;
                    }
                }

                if ((sommet2 = graphe.hashMap.putIfAbsent(s2, new Sommet())) == null) {
                    // Le sommet n'existait pas et a été inséré puis récupéré
                    sommet2 = graphe.hashMap.get(s2);
                    // Recheche du plus grand numéro de sommet
                    if (graphe.plusGrandNum < s2) {
                        graphe.plusGrandNum = s2;
                    }
                }

                if (s1 == s2) {
                    boucleIgnore++;
                } else if (sommet1.ajoutSommet(sommet2)) {
                    graphe.nbAretes++;
                } else {
                    doublon++;
                }
            }
            graphe.nbSommets = graphe.hashMap.size();
        } catch (Exception e) {
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("Nom de fichier inexistant");
            } else
                e.printStackTrace();
        }

        System.out.println(doublon + " doublons ont ete supprimes ");
        System.out.println(boucleIgnore + " boucles ont ete ignorees");
        System.out.println("nb arêtes: " + graphe.nbAretes + " -- nb sommets " + graphe.nbSommets + " -- num sommet max " +
                graphe.plusGrandNum + " -- degre max " + graphe.getMaxDeg());
        // Mémoire allouée à la fin du chargement
        System.out.println("Mémoire allouée: " +
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                + " Octets");

        //on recupere les sommets dont le numéro est passé en paramètre
        Sommet firstSommet = null;
        Sommet secondSommet = null;
        try {
            firstSommet = graphe.hashMap.get(Integer.parseInt(args[1]));
            secondSommet = graphe.hashMap.get(Integer.parseInt(args[2]));
        } catch (Exception e) {
            System.out.println("Arguments de numero de sommet invalides");
            System.exit(-1);
        }

        if (firstSommet == null) {
            System.out.println("Le sommet " + args[1] + " n'existe pas");
        } else if (secondSommet == null) {
            System.out.println("Le sommet " + args[2] + " n'existe pas");
        } else {
            int nbSommetAccessible = graphe.getNbSommetsAccessible(firstSommet);
            System.out.print(nbSommetAccessible + " sommets accessibles depuis le sommet : " + args[1]);
            if (secondSommet.distance == -1) {
                System.out.println(" et " + args[2] + " est inaccessible ou inexistant");
            } else {
                System.out.println(" et sa distance à " + args[2] + " est : " + secondSommet.distance);
            }
        }


        // Memoire allouée à la fin du programme
        System.out.println("Mémoire allouée: " +
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                + " Octets");
    }
}
