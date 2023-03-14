package presentation;

import bll.ClientBLL;
import bll.ProdusBLL;
import model.Client;
import model.Produs;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.awt.Font.BOLD;


public class ViewComenzi extends JFrame {

    private  View fereastraComenzi;
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    private Button comanda=new Button("Comanda");
    private JTextField cantitateDeCumparat=new JTextField(4);
    private JComboBox<String> clientiNumeChoice=new JComboBox<String>();
    private JComboBox<String> clientiPrenumeChoice=new JComboBox<String>();
    private JComboBox<String> produseNumeChoice=new JComboBox<String>();

    /**
     * <p>constructor</p>
     */
    public ViewComenzi()
    {
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Interfata cu tabelul comenzi");

        JPanel content=new JPanel(new FlowLayout());

        ClientBLL clientBLL = new ClientBLL();
        Client client1=null;
       /* try {
            client1 = clientBLL.findClientById(1,"idClient");

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }*/

        List<Client> listaClienti=clientBLL.findAll();
        ProdusBLL produsBLL = new ProdusBLL();

        Produs produs1=null;
        /*try {
            produs1 = produsBLL.findProdusById(1,"idProdus");

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }*/

        List<Produs> listaProduse=produsBLL.findAll();

        String[] numeClienti = new String[listaClienti.size()+1];
        String[] numeProduse = new String[listaProduse.size()+1];
        Integer[] stocProduse=new Integer[listaProduse.size()+1];

        for(int i=0;i<listaClienti.size();i++) {
            numeClienti[i] = listaClienti.get(i).getNume();
        }
        JPanel content1= new JPanel(new FlowLayout());
        clientiNumeChoice=new JComboBox<>(numeClienti);
        clientiPrenumeChoice=new JComboBox<>();
        content1.add(new JLabel("Selecteaza clientul care doresti sa plaseze comanda: "));
        content1.add(clientiNumeChoice);
        clientiNumeChoice.addActionListener(event -> {
            // Get the source of the component, which is our comboBox
            JComboBox comboBox1 = (JComboBox) event.getSource();

            //  numele clientului selectat
            Object selected = comboBox1.getSelectedItem();
            // numele comenzii facute
            String command = event.getActionCommand();
            if ("comboBoxChanged".equals(command)) {
                //aici voi permite selectia doar pt cine are numele din selected
                ClientBLL clientBLL1 = new ClientBLL();
                Client client2 = null;
                List<Client> listaClientiPermisi = null;
                try {
                    listaClientiPermisi = clientBLL.findClientByName(selected.toString());

                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }


                // clientiPrenumeChoice.removeAllItems();
                String[] prenumeClienti = new String[listaClientiPermisi.size() + 1];
                for (int i = 0; i < listaClientiPermisi.size(); i++) {
                    prenumeClienti[i] = listaClientiPermisi.get(i).getPrenume();
                    System.out.println(prenumeClienti[i]);
                    clientiPrenumeChoice.addItem(prenumeClienti[i]);
                }
                content1.add(clientiPrenumeChoice);
            }
        });
        content1.add(clientiPrenumeChoice);
        for(int i=0;i<listaProduse.size();i++) {
            numeProduse[i] = listaProduse.get(i).getNume();
            stocProduse[i]=listaProduse.get(i).getStoc();
        }


        JPanel content2= new JPanel(new FlowLayout());
        produseNumeChoice=new JComboBox<>(numeProduse);
        content2.add(new JLabel("Selecteaza produsul pe care doresti sa il comanzi: "));
        content2.add(produseNumeChoice);

        JPanel content3= new JPanel(new FlowLayout());
        content3.add(new JLabel("Selecteaza cantitatea: "));
        content3.add(cantitateDeCumparat);
        comanda.setPreferredSize(new Dimension(60,40));
        comanda.setBackground(Color.pink);
        comanda.setFont(new Font("Arial", BOLD,12));
        content3.add(comanda);


        content.add(content1);
        content.add(content2);
        content.add(content3);

        this.add(content);
    }

    public int getCantitateDeCumparat()
    {
        return Integer.parseInt(cantitateDeCumparat.getText());
    }
    public  void setCantitateDeCumparat(String nou)
    {
        cantitateDeCumparat.setText(nou);
    }
    public String getNumeClient()
    {
        return (String) clientiNumeChoice.getItemAt( clientiNumeChoice.getSelectedIndex());
    }
    public String getPrenumeClient()
    {
        return (String) clientiPrenumeChoice.getItemAt( clientiPrenumeChoice.getSelectedIndex());
    }
    public String getNumeProdus()
    {
        return (String) produseNumeChoice.getItemAt( produseNumeChoice.getSelectedIndex());
    }
    public void reset() {
        setCantitateDeCumparat("");
    }
    public void addComandaListener(ControllerComenzi.ComandaListener mal)
    {
        comanda.addActionListener(mal);
    }
}

