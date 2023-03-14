package bll;

import bll.validators.ProductStocValidator;
import bll.validators.Validator;
import connection.ConnectionFactory;
import dao.ProdusDAO;
import model.Client;
import model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;

import static java.awt.Font.MONOSPACED;
import static java.lang.Integer.parseInt;

public class ProdusBLL {

    private List<Validator<Produs>> validators;
    private ProdusDAO produsDAO;

    /**
     * <p>constructor fara param</p>
     */
    public ProdusBLL() {
        validators = new ArrayList<Validator<Produs>>();
        validators.add(new ProductStocValidator());

        produsDAO = new ProdusDAO();
    }

    /**
     * <p>constructor cu param</p>
     * @param produs produsul
     * @throws NoSuchFieldException exceptie
     * @throws IllegalAccessException exceptie
     */
    public ProdusBLL(Produs produs) throws NoSuchFieldException, IllegalAccessException {
        validators = new ArrayList<Validator<Produs>>();
        validators.add(new ProductStocValidator());
        produsDAO = new ProdusDAO(produs);//validez, apoi folosesc constructorul de la produsDAO
    }

    /**
     *
     * @param id id ul produsului
     * @param numeColId coloana dupa care caut
     * @return daca am reusit sau nu
     */
    public Produs findProdusById(int id, String numeColId) {
        Produs st = produsDAO.findById(id, numeColId);

        if (st == null) {
            throw new NoSuchElementException("The product with idProduct =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param nume numele produsului de cautat
     * @return daca a gasit sau nu
     */
    public List<Produs> findProdusByName(String nume) {// ambele campuri se numesc nume( din Client si Produs)
        List<Produs> st = produsDAO.findByName(nume);

        if (st == null) {
            throw new NoSuchElementException("The product with name =" + nume + " was not found!");
        }
        return st;
    }

    /**
     *
     * @return daca a gasit toate produsele sau nu
     */
    public List<Produs> findAll() {
        List<Produs> st = produsDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("Nu exista produse in baza de date");
        }
        return st;
    }

    /**
     *
     * @param t produsul de inserat
     * @return daca a reusit inserarea sau nu
     */
    public Produs insert(Produs t) {
        Produs st = produsDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("Nu s-a putut insera produsul in baza de date!!");
        }
        return st;
    }

    /**
     *
     * @param t produsul de sters
     * @return daca a reusit stergerea sau nu
     */
    public int delete(Produs t) {
        int st = produsDAO.delete(t);
        if (st == -9)
            throw new NoSuchElementException("Nu s-a putut sterge produsul din baza de date!!");
        return st;
    }

    /**
     *
     * @param t produsul de modificat
     * @return daca s a reusit modificarea
     */
    public Produs update(Produs t) {
        Produs st = produsDAO.update(t);
        if (st == null) {
            throw new NoSuchElementException("Nu s-a putut updata produsul in baza de date!!");
        }
        return st;
    }

    //aici implementez metoda specifica pt scaderea stocului

    /**
     *
     * @param tupla tupla
     * @param newStoc noul stoc
     * @throws SQLException exceptie
     */
    public void updateStoc(Produs tupla, int newStoc) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "UPDATE Produs SET stoc=? WHERE idProdus=?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newStoc);
            statement.setInt(2, tupla.getIdProdus());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //System.out.println(statement.toString());
            statement.executeUpdate();
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
