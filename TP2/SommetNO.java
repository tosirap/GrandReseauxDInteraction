import java.util.LinkedList;
import java.util.List;

public class SommetNO extends Sommet implements SommetInterface {

    public List<SommetInterface> listeAdj;

    public SommetNO() {
        listeAdj = new LinkedList<>();
    }

    @Override
    public void ajoutSommet(SommetInterface s) {
        if (!this.listeAdj.contains(s)){
            this.listeAdj.add(s);
            s.ajoutSommet(this);
        }


    }

    @Override
    public void supprSommet() {
        for (SommetInterface s : this.listeAdj) {
            s.getListe().remove(this);
        }
    }

    @Override
    public List<SommetInterface> getListe() {
        return listeAdj;
    }
}
