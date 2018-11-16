import java.util.LinkedList;
import java.util.List;

public class SommetO extends Sommet implements SommetInterface {

    public List<SommetInterface> listSucc;
    public List<SommetInterface> listPred;

    public SommetO() {
        listSucc = new LinkedList<>();
        listPred = new LinkedList<>();
    }

    @Override
    public void ajoutSommet(SommetInterface s) {
        this.listSucc.add(s);
        ((SommetO) s).listPred.add(this);
    }

    @Override
    public void supprSommet() {
        for (SommetInterface s : this.listSucc) {
            s.getListe().remove(this);
        }
        for (SommetInterface s : this.listPred) {
            s.getListe().remove(this);
        }
    }

    @Override
    public List<SommetInterface> getListe() {
        return listSucc;
    }
}
