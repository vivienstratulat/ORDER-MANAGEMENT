package dao;

import model.Client;
import model.Comanda;
import model.Produs;

public class ComandaDAO extends AbstractDAO<Comanda>{
    public ComandaDAO(Comanda comanda) throws NoSuchFieldException, IllegalAccessException {//pt inserare
        super(comanda);// reflection
    }
    public ComandaDAO()
    {
    }
    public Comanda insert(Comanda t)
    {
        return super.insert(t);
    }
}
