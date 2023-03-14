package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerMain {
;
    private View  interfataMea;

    /** Constructor */
    public ControllerMain( View view) {

        interfataMea  = view;

        this.interfataMea.showClient(new ControllerMain.ClientiWindowListener());
        this.interfataMea.showProducts(new ControllerMain.ProduseWindowListener());
        this.interfataMea.showOrders(new ControllerMain.ComenziWindowListener());

    }
    class ClientiWindowListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                ViewClient viewclienti = new ViewClient();
                ControllerClienti contr=new ControllerClienti(viewclienti);
            }
            catch(Exception exceptie)
            {
                interfataMea.showMessage(exceptie.getMessage());
            }

        }
    }
    class ProduseWindowListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ViewProduse viewProduse = new ViewProduse();
                ControllerProduse contr=new ControllerProduse(viewProduse);
            }
            catch(Exception exceptie)
            {
                interfataMea.showMessage(exceptie.getMessage());
            }

        }
    }


    class ComenziWindowListener   implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ViewComenzi viewComenzi = new ViewComenzi();
                viewComenzi.setVisible(true);
                ControllerComenzi contr=new ControllerComenzi(viewComenzi);
            }
            catch(Exception exceptie)
            {
                interfataMea.showMessage(exceptie.getMessage());
            }

        }
    }
}



