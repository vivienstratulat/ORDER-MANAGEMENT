package model;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Client {
    private int idClient;
    private String nume;
    private String  prenume;
    private String  adresa;
    private int bani;//poate fac ceva fun si il as sacumpere doar daca mai are suficienti bani, we'll see
    private int age;



    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * <p>constr fara param</p>
     */
    public Client()
    {
    }

    /**
     *<p>constructor cu param</p>
     * @param idClient id ul
     * @param nume numele
     * @param prenume prenumele
     * @param adresa adresa
     * @param bani buget
     * @param age varsta
     */
    public Client(int idClient, String nume, String prenume, String adresa, int bani, int age) {
        this.idClient = idClient;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.bani = bani;
        this.age = age;
    }

    /**
     *
     * @return numele clientului
     */
    public String getNume() {
        return nume;
    }

    /**
     *
     * @return varsta client
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age varsta nou
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @param nume noul nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     *
     * @return prenume client
     */
    public String getPrenume() {
        return prenume;
    }

    /**
     *
     * @param prenume set
     */
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    /**
     *
     * @return adresa
     */
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     *
     * @return banii
     */
    public int getBani() {
        return bani;
    }

    public void setBani(int bani) {
        this.bani = bani;
    }

    /**
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Client: id="+this.getIdClient()+", nume=" + this.getNume() + ", prenume=" + this.getPrenume() + ", adresa=" + this.getAdresa() +
                ", ramas in cont=" + this.getBani() + ", varsta=" + this.getAge();
    }


}

