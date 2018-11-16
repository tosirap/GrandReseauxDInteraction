public class GrapheNO extends Graphe implements GrapheInterface {

    public GrapheNO() {
        super();
    }


    @Override
    public void distri() {
        int[] distribution = new int[degreMax + 1];
        for (SommetInterface s : listeSommets.values()) {
            distribution[s.getListe().size()]++;
        }
        for (int i = 0; i < distribution.length; i++) {
            System.out.println(i + " " + distribution[i]);
        }
    }

    public double cluG(){
        //fonct todo int nb triangle = 
        int nbTriangle=0;
        double nb =0;
        for (SommetInterface s : listeSommets.values()) {
            nb+= ((s.getListe().size())*(s.getListe().size()-1))/2;
            
        }
    }
}
