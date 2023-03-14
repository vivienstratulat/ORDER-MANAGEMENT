package dao;

import model.Client;

import java.util.List;

public class ClientDAO  extends AbstractDAO<Client>{

    public ClientDAO(Client client) throws NoSuchFieldException, IllegalAccessException {
        super(client);// reflection
    }
    public ClientDAO() //pt findAll
    {
    }

    public Client findById(int id, String numeColId)
    {
        return (Client) super.findById(id,numeColId); // metoda generica
    }
    public List<Client> findByName(String nume)
    {
        return super.findByName(nume);
    }
    public  List<Client> findAll()
    {
        return (List<Client>) super.findAll();
    }
    public Client insert(Client t)
    {
        return super.insert(t);
    }
    public int delete(Client t)
    {
        return super.delete(t);
    }
    public Client update(Client t)
    {
        return super.update(t);
    }
}


