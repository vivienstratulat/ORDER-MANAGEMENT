package start;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CreateTable<T> {
    JFrame frame;
    JTable tabel;


    /**
     *
     * @param listaTabel headerul tabelului ce urmeaza a fi construit
     * @throws NoSuchFieldException exceptie
     * @throws IllegalAccessException exceptie
     */
    public  CreateTable(List<T> listaTabel) throws NoSuchFieldException, IllegalAccessException {
        frame = new JFrame();
        frame.setTitle("Tabel "+listaTabel.get(0).getClass().getName());

        ReflectionStrategy refl=new ReflectionStrategy();
        int nrColoane=refl.retrieveheader(listaTabel).size();
        String[] header=new String[nrColoane];

        int i=0;
        while(i<nrColoane) {
            header[i] = refl.retrieveheader(listaTabel).get(i).toString();
            i++;
        }

        int nrLinii= listaTabel.size();
        String[][] tuple=new String[nrLinii][nrColoane];

        i=0;
        int p;
        for (T t : listaTabel) //iterez prin lista de tuple
        { p=0;
            for (Field field : t.getClass().getDeclaredFields()) //iterez prin campurile clasei respective
            {
                field.setAccessible(true);
                Object value = field.get(t);
                tuple[i][p] = value.toString();
                p++;
            }
            i++;
        }

        tabel=new JTable(tuple,header);
        tabel.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(tabel);
        frame.add(sp);
        frame.setSize(500, 200);
        frame.setVisible(true);

    }
}
