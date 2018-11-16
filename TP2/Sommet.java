import java.util.Comparator;
import java.util.List;

public abstract class Sommet implements SommetInterface, Comparable<Sommet> {
    public abstract List<SommetInterface> getListe();

    @Override
    public int compareTo(Sommet o) {
        return Integer.compare(this.getListe().size(), o.getListe().size());
    }
}
