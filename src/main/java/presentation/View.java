package presentation;


import javax.swing.*;
import java.awt.*;

public class View extends Component {
    private JFrame frame;
    private JButton btnClient,btnProduct,btnOrder;

    /**
     * <p>constructor</p>
     */
    public View(){
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBounds(200,200,700,500);

        btnClient=new JButton("CLIENT");
        btnOrder=new JButton("ORDER");
        btnProduct=new JButton("PRODUCT");

        btnClient.setBounds(40,20,600,120);
        btnClient.setBackground(Color.pink);
        frame.getContentPane().add(btnClient);

        btnProduct.setBounds(40,150,600,120);
        btnProduct.setBackground(Color.pink);
        frame.getContentPane().add(btnProduct);

        btnOrder.setBounds(40,280,600,120);
        btnOrder.setBackground(Color.pink);
        frame.getContentPane().add(btnOrder);



        frame.setVisible(true);
    }

    public void showClient(ControllerMain.ClientiWindowListener a){
        btnClient.addActionListener(a);
    }

    public void showMessage(String mesaj){
        JOptionPane.showMessageDialog(this,mesaj);

    }
    public void showProducts(ControllerMain.ProduseWindowListener a){
        btnProduct.addActionListener(a);
    }

    public void showOrders(ControllerMain.ComenziWindowListener a){
        btnOrder.addActionListener(a);
    }



}

