package presentation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CreareFisier {
    private FileWriter fw;

    /**
     *
     * @param numeFisier nuele fisierului
     */
    public void creeazaFisier(String numeFisier) {
        try {
            File fisier = new File(numeFisier);
            if (!fisier.exists())
                fisier.createNewFile();
            fw = new FileWriter(fisier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scrie(String linieNoua)
    {
        try {
            fw.write(linieNoua + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

