package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * <p>constructorul fara param</p>
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * <p> constructor cu param</p>
     * @param client clientul transmis
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public ClientBLL(Client client) throws NoSuchFieldException, IllegalAccessException {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientAgeValidator());
        clientDAO = new ClientDAO(client);
    }

    /**
     *
     * @param id id ul clientului
     * @param numeColId dupa care coloana cautam
     * @return clientul cautat
     */
    public Client findClientById(int id,String numeColId) {
        Client cl = clientDAO.findById(id,numeColId);
       // validators.get(0).validate(cl);
        if (cl == null) {
            throw new NoSuchElementException("The client with idClient =" + id + " was not found!");
        }
        return cl;
    }

    /**
     *
     * @param nume numele clientului
     * @return lista cu clientii cu acel nume
     */
    public List<Client> findClientByName(String nume) {
        List<Client> cl = clientDAO.findByName(nume);
        if (cl == null) {
            throw new NoSuchElementException("The client with name =" + nume + " was not found!");
        }
        return cl;
    }

    /**
     * <p>toti clientii</p>
     * @return
     */
    public List<Client> findAll() {
        List<Client> cl = clientDAO.findAll();
        if (cl == null) {
            throw new NoSuchElementException("Nu exista clienti in baza de date");
        }
        return cl;
    }

    /**
     *
     * @param t clientul ce l inseram
     * @return daca am reusit sau nu sa l inseram
     */
    public Client insert(Client t) {
        Client cl = clientDAO.insert(t);
        if (cl == null) {
            throw new NoSuchElementException("Nu s-a putut insera clientul in baza de date sau aici!");
        }
        return cl;
    }

    /**
     *
     * @param t clientul ce l stergem
     * @return daca am reusit sa l sterg sau nu
     */
    public int delete(Client t) {
        int cl = clientDAO.delete(t);
        if (cl == -9)
            throw new NoSuchElementException("Nu s-a putut sterge clientul din baza de date!");
        return cl;
    }

    /**
     *
     * @param t clientul ce l editam
     * @return daca am reusit sau nu
     */
    public Client update(Client t) {
        Client cl = clientDAO.update(t);
        if (cl == null) {
            throw new NoSuchElementException("Nu s-a putut updata clientul in baza de date!");
        }
        return cl;
    }
}

