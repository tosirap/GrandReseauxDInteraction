public class Arete {
    private int sommetID1;
    private int sommetID2;

    public Arete(int sommetID1, int sommetID2) {
        this.sommetID1 = sommetID1;
        this.sommetID2 = sommetID2;
    }

    public int getSommetID1() {
        return sommetID1;
    }

    public int getSommetID2() {
        return sommetID2;
    }

    public void setSommetID1(int sommetID1) {
        this.sommetID1 = sommetID1;
    }

    public void setSommetID2(int sommetID2) {
        this.sommetID2 = sommetID2;
    }

    public String toStringDot() {
        return sommetID1 + " -- " + sommetID2 + ";\n";
    }

    public String toStringTxt() {
        return sommetID1 + " " + sommetID2 + '\n';
    }
}
