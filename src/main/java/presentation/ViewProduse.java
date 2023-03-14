package presentation;


import javax.swing.*;
import java.awt.*;

public class ViewProduse {
    private JFrame frame;
    private JButton btnShow, btnInsert, btnClear, btnDelete, btnEdit;
    private JTextField tfIdToDelete, tfIdToEdit, tfId, tfName, tfSupplier, tfStock;
    private JLabel lId, lName, lSupplier, lStock, lEdit, lDelete;

    /**
     * <p>constructor</p>
     */
    public ViewProduse() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBounds(200, 200, 700, 400);
        frame.setTitle("Product TABLE");

        btnShow = new JButton("SHOW PRODUCTS");
        btnShow.setBackground(Color.PINK);
        btnShow.setBounds(40, 50, 130, 40);
        frame.getContentPane().add(btnShow);

        btnDelete = new JButton("DELETE PRODUCT");
        btnDelete.setBackground(Color.PINK);
        btnDelete.setBounds(40, 110, 130, 40);
        frame.getContentPane().add(btnDelete);

        btnInsert = new JButton("INSERT PRODUCT");
        btnInsert.setBackground(Color.PINK);
        btnInsert.setBounds(40, 170, 130, 40);
        frame.getContentPane().add(btnInsert);

        btnEdit = new JButton("EDIT PRODUCT");
        btnEdit.setBackground(Color.PINK);
        btnEdit.setBounds(40, 230, 130, 40);
        frame.getContentPane().add(btnEdit);

        lId = new JLabel("PRODUCT ID");
        lId.setBounds(210, 20, 180, 50);
        frame.getContentPane().add(lId);

        tfId = new JTextField();
        //  tfId.setText("CLIENT ID");
        tfId.setBounds(310, 30, 120, 30);
        frame.getContentPane().add(tfId);

        lName = new JLabel("PRODUCT NAME");
        lName.setBounds(205, 70, 180, 50);
        frame.getContentPane().add(lName);

        tfName = new JTextField();
        tfName.setBounds(310, 80, 120, 30);
        frame.getContentPane().add(tfName);

        lSupplier = new JLabel("SUPPLIER");
        lSupplier.setBounds(205, 130, 180, 50);
        frame.getContentPane().add(lSupplier);

        tfSupplier = new JTextField();
        tfSupplier.setBounds(310, 140, 120, 30);
        frame.getContentPane().add(tfSupplier);


        lStock = new JLabel("STOCK");
        lStock.setBounds(205, 180, 180, 50);
        frame.getContentPane().add(lStock);

        tfStock = new JTextField();
        tfStock.setBounds(310, 190, 120, 30);
        frame.getContentPane().add(tfStock);

        lDelete = new JLabel("ID PRODUCT TO DELETE");
        lDelete.setBounds(440, 120, 180, 50);
        frame.getContentPane().add(lDelete);

        tfIdToDelete = new JTextField();
        tfIdToDelete.setBounds(590, 130, 70, 30);
        frame.getContentPane().add(tfIdToDelete);

        lEdit = new JLabel("ID PRODUCT TO EDIT");
        lEdit.setBounds(440, 200, 180, 50);
        frame.getContentPane().add(lEdit);

        tfIdToEdit = new JTextField();
        tfIdToEdit.setBounds(590, 210, 70, 30);
        frame.getContentPane().add(tfIdToEdit);


        frame.setVisible(true);
    }

    public void showProducts(ControllerProduse.AllProduseListener a){
        btnShow.addActionListener(a);
    }

    public void addInsert(ControllerProduse.InsertProdusListener a){
        btnInsert.addActionListener(a);
    }

    public void addDelete(ControllerProduse.DeleteProdusListener a){
        btnDelete.addActionListener(a);
    }

    public void addUpdate(ControllerProduse.UpdateProdusListener a){
        btnEdit.addActionListener(a);
    }

    public int getUserIdProdus() {
        return Integer.parseInt(tfId.getText());
    }
    public String getUserNume() {
        return tfName.getText();
    }
    public String getUserProducator() {
        return tfSupplier.getText();
    }
    public int getUserStoc() {
        return Integer.parseInt(tfStock.getText());
    }

    public  void setIdProdus(String nou) {
        tfId.setText(nou);
    }
    public  void setNume(String nou) {
        tfName.setText(nou);
    }
    public  void setProducator(String nou) {
        tfSupplier.setText(nou);
    }
    public  void setStoc(String nou) {
        tfStock.setText(nou);
    }

    public int getIdProdusDeSters() {
        return Integer.parseInt(tfIdToDelete.getText());
    }
    public int getIdProdusUpdate() {
        return Integer.parseInt(tfIdToEdit.getText());
    }
    public  void setIdProdusDeSters(String nou) {
        tfIdToDelete.setText(nou);
    }
    public  void setIdProdusDeEditat(String nou) {
        tfIdToEdit.setText(nou);
    }

}

