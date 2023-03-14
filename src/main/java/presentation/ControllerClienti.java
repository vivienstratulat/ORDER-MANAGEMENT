package presentation;

import bll.ClientBLL;
import model.Client;
import start.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerClienti {
    private ViewClient windowClienti;

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /** Constructor */
    public ControllerClienti( ViewClient view) {
        windowClienti=view;

        this.windowClienti.showClients(new AllClientiListener());
        this.windowClienti.addInsert(new InsertClientListener());
        this.windowClienti.addDelete(new DeleteClientListener());
        this.windowClienti.addUpdate(new UpdateClientListener());
    }

    class AllClientiListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBLL = new ClientBLL();

            Client client1=null;

            List<Client> listaClienti=clientBLL.findAll();

            try {
                CreateTable tabelas = new CreateTable<>(listaClienti);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }


    class InsertClientListener   implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userIdClient  = windowClienti.getUserIdClient();
            String userNume=windowClienti.getUserNume();
            String userPrenume=windowClienti.getUserPrenume();
            String userAdresa=windowClienti.getUserAdresa();
            int userBani=windowClienti.getUserBani();
            int userAge=windowClienti.getUserAge();

            Client clientNou=new  Client(userIdClient,userNume,userPrenume,userAdresa,userBani,userAge);
            ClientBLL clientBLL1=null;
            try {
                clientBLL1 = new ClientBLL(clientNou);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            Client client2=null;
            try {
                client2 = clientBLL1.insert(clientNou);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
        }
    }
    class DeleteClientListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idClient= windowClienti.getIdClientDeSters();
            ClientBLL clientBLL = new ClientBLL();
            Client client=null;
            try {
                client = clientBLL.findClientById(idClient,"idClient");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }

            clientBLL.delete(client);
        }
    }
    class UpdateClientListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idClientDeEditat = windowClienti.getIdClientUpdate();
            ClientBLL clientBLL = new ClientBLL();
            Client client = null;
            try {
                client = clientBLL.findClientById(idClientDeEditat, "idClient");

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
            }
            //in client am clientulDeUpdatat
            clientBLL.update(client);
        }
    }

}
