import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Generator implements GeneratorInterface {

    @Override
    public void exportToFile(Graphe graphe, String fileName, String extension) {
        File file = new File(fileName + "." + extension);
        FileWriter fw = null;
        try {
            if (!file.createNewFile()) {
                System.out.println("Impossible de créer le fichier de sortie");
            }
            fw = new FileWriter(file);
            switch (extension) {
                case "txt":
                    for (Arete a : graphe.getListeArete()) {
                        fw.write(a.toStringTxt());
                    }
                    fw.flush();
                    break;
                case "dot":
                    fw.write("graph sortie {" + '\n');
                    for (Arete a : graphe.getListeArete()) {
                        fw.write(a.toStringDot());
                    }
                    fw.write("}");
                    fw.flush();
                    break;
                default:
                    System.out.println("Format de fichier non pris en charge");
                    break;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println("Fin de l'export");
    }
}
