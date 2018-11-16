public interface GeneratorInterface {
    Graphe generate();
    void exportToFile(Graphe graphe, String fileName, String extension);
}
