package presentation;

import bll.ProdusBLL;
import model.Produs;
import start.CreateTable;
import start.Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProduse {
    private ViewProduse fereastraProduse;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());


    public ControllerProduse( ViewProduse view) {

        fereastraProduse=view;
        this.fereastraProduse.showProducts(new ControllerProduse.AllProduseListener());
        this.fereastraProduse.addInsert(new ControllerProduse.InsertProdusListener());
        this.fereastraProduse.addDelete(new ControllerProduse.DeleteProdusListener());
        this.fereastraProduse.addUpdate(new ControllerProduse.UpdateProdusListener());
    }

    class AllProduseListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProdusBLL produsBLL = new ProdusBLL();


            List<Produs> listaProduse=produsBLL.findAll();

            try {
                CreateTable tabelas = new CreateTable<Produs>(listaProduse);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }


        }
    }


    class InsertProdusListener   implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userIdProdus  = fereastraProduse.getUserIdProdus();
            String userNume=fereastraProduse.getUserNume();
            String userProducator=fereastraProduse.getUserProducator();
            int userStoc=fereastraProduse.getUserStoc();

            Produs produsNou=new  Produs(userIdProdus,userNume,userProducator,userStoc);
            ProdusBLL produsBLL1=null;
            try {
                produsBLL1 = new ProdusBLL(produsNou);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            Produs produs2=null;
            try {
                produs2 = produsBLL1.insert(produsNou);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

        }
    }
    class DeleteProdusListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idProdus= fereastraProduse.getIdProdusDeSters();
            ProdusBLL produsBLL = new ProdusBLL();
            Produs produs=null;
            try {
                produs = produsBLL.findProdusById(idProdus,"idProdus");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            produsBLL.delete(produs);
        }
    }
    class UpdateProdusListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idProdusDeEditat = fereastraProduse.getIdProdusUpdate();

            ProdusBLL produsBLL = new ProdusBLL();
            Produs produs = null;
            try {
                produs = produsBLL.findProdusById(idProdusDeEditat, "idProdus");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            produsBLL.update(produs);
        }
    }
}

