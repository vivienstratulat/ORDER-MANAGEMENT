package presentation;

import bll.ComandaBLL;
import bll.ProdusBLL;
import model.Comanda;
import model.Produs;
import start.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControllerComenzi {

    private ViewComenzi windowComenzi;
    private int billNo=0;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /** Constructor */
    public ControllerComenzi( ViewComenzi view) {
        windowComenzi=view;
        this.windowComenzi.addComandaListener(new ComandaListener());

    }

    class ComandaListener  implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String numeClient = windowComenzi.getNumeClient();
            String prenumeClient = windowComenzi.getPrenumeClient();
            String numeProdus = windowComenzi.getNumeProdus();
            int cantitateDorita = windowComenzi.getCantitateDeCumparat();


            int idProdusDeComandat;

            ProdusBLL produsBLL = new ProdusBLL();
            Produs produs = null;
            try {
                produs = produsBLL.findProdusByName(numeProdus).get(0);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            int stocExistent = produs.getStoc();
            if (stocExistent < cantitateDorita) {
                JOptionPane.showMessageDialog(windowComenzi, "STOC EPUIZAT");
                windowComenzi.reset();
            } else {
                if (cantitateDorita==0)
                {
                    JOptionPane.showMessageDialog(windowComenzi, "CANTITATE INVALIDA");
                    windowComenzi.reset();
                }
                else
                {  //update la stock
                    try {
                        produsBLL.updateStoc(produs, stocExistent - cantitateDorita);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    Comanda comandaNoua = new Comanda(numeClient, prenumeClient, numeProdus, cantitateDorita);
                    ComandaBLL comandaBLL1 = null;
                    try {
                        comandaBLL1 = new ComandaBLL(comandaNoua);
                    } catch (NoSuchFieldException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                    Comanda comanda2 = null;

                    try {
                        comanda2 = comandaBLL1.insert(comandaNoua);

                    } catch (Exception ex) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                        System.out.println("offf");
                    }

                    String textFisier=("Clientul "+numeClient+" "+prenumeClient+
                            " a comandat produsul "+produs.getNume() +" in cantitatea "+cantitateDorita);
                    CreareFisier nouFisi=new CreareFisier();
                    nouFisi.creeazaFisier("BILL"+billNo);
                    nouFisi.scrie(textFisier);
                    billNo++;
                }
            }


        }
    }
}
