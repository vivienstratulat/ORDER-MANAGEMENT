package bll;

import dao.ComandaDAO;
import model.Comanda;

import java.util.NoSuchElementException;

public class ComandaBLL {
    private ComandaDAO comandaDAO;

    /**
     * <p>constructor fara param</p>
     */
    public ComandaBLL() {
        comandaDAO = new ComandaDAO();
    }

    /**
     * <p>constructor cu param</p>
     * @param comanda comanda
     * @throws NoSuchFieldException eroare
     * @throws IllegalAccessException eroare
     */
    public ComandaBLL(Comanda comanda) throws NoSuchFieldException, IllegalAccessException {
        comandaDAO = new ComandaDAO(comanda);
    }

    /**
     *
     * @param t comanda ce o inserez
     * @return daca am reusit sau nu
     */
    public Comanda insert(Comanda t) {
        Comanda st = comandaDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("Nu s-a putut insera comanda in baza de datedc!!");
        }
        return st;
    }
}

