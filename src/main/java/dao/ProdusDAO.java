package dao;
import model.Produs;
import java.util.List;

public class ProdusDAO extends AbstractDAO<Produs> {

    public ProdusDAO(Produs produs) throws NoSuchFieldException, IllegalAccessException {//pt inserare
        super(produs);// reflection
    }
    public ProdusDAO()
    {
    }

    public Produs findById(int id, String numeColId)
    {
        return super.findById(id,numeColId);
    }
    public List<Produs> findByName(String nume)
    {
        return super.findByName(nume);
    }
    public List<Produs> findAll()
    {
        return (List<Produs>) super.findAll();
    }
    public Produs insert(Produs t)
    {
        return super.insert(t);
    }
    public int delete(Produs t)
    {
        return super.delete(t);
    }
    public Produs update(Produs t)
    {
        return super.update(t);
    }
}
