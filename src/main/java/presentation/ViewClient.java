package presentation;


import javax.swing.*;
import java.awt.*;

public class ViewClient {
    private JFrame frame;
    private JButton btnShow,btnInsert,btnClear,btnDelete,btnEdit;
    private JTextField tfIdToDelete,tfIdToEdit,tfId,tfName,tfSurname,tfAddress,tfBuget,tfAge;
    private JLabel lId,lName,lSurname,lAddress,lBuget,lAge,lEdit,lDelete;

    /**
     * <p>constructor</p>
     */
    public ViewClient(){

        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setBounds(200,200,700,400);
        frame.setTitle("CLIENT TABLE");

        btnShow=new JButton("SHOW CLIENTS");
        btnShow.setBackground(Color.PINK);
        btnShow.setBounds(40,50,130,40);
        frame.getContentPane().add(btnShow);

        btnDelete=new JButton("DELETE CLIENT");
        btnDelete.setBackground(Color.PINK);
        btnDelete.setBounds(40,110,130,40);
        frame.getContentPane().add(btnDelete);

        btnInsert=new JButton("INSERT CLIENT");
        btnInsert.setBackground(Color.PINK);
        btnInsert.setBounds(40,170,130,40);
        frame.getContentPane().add(btnInsert);

        btnEdit=new JButton("EDIT CLIENT");
        btnEdit.setBackground(Color.PINK);
        btnEdit.setBounds(40,230,130,40);
        frame.getContentPane().add(btnEdit);

        lId=new JLabel("CLIENT ID");
        lId.setBounds(210,20,180,50);
        frame.getContentPane().add(lId);

        tfId=new JTextField();
        //  tfId.setText("CLIENT ID");
        tfId.setBounds(310,30,120,30);
        frame.getContentPane().add(tfId);

        lName=new JLabel("CLIENT NAME");
        lName.setBounds(205,70,180,50);
        frame.getContentPane().add(lName);

        tfName=new JTextField();
        tfName.setBounds(310,80,120,30);
        frame.getContentPane().add(tfName);

        lSurname=new JLabel("CLIENT SURNAME");
        lSurname.setBounds(205,120,180,50);
        frame.getContentPane().add(lSurname);

        tfSurname=new JTextField();
        tfSurname.setBounds(310,130,120,30);
        frame.getContentPane().add(tfSurname);

        lAddress=new JLabel("CLIENT ADDRESS");
        lAddress.setBounds(205,170,180,50);
        frame.getContentPane().add(lAddress);

        tfAddress=new JTextField();
        tfAddress.setBounds(310,180,120,30);
        frame.getContentPane().add(tfAddress);

        lAge=new JLabel("CLIENT AGE");
        lAge.setBounds(205,220,180,50);
        frame.getContentPane().add(lAge);

        tfAge=new JTextField();
        tfAge.setBounds(310,230,120,30);
        frame.getContentPane().add(tfAge);

        lBuget=new JLabel("CLIENT BUGET");
        lBuget.setBounds(205,270,180,50);
        frame.getContentPane().add(lBuget);

        tfBuget=new JTextField();
        tfBuget.setBounds(310,280,120,30);
        frame.getContentPane().add(tfBuget);

        lDelete=new JLabel("ID CLIENT TO DELETE");
        lDelete.setBounds(460,120,180,50);
        frame.getContentPane().add(lDelete);

        tfIdToDelete=new JTextField();
        tfIdToDelete.setBounds(590,130,70,30);
        frame.getContentPane().add(tfIdToDelete);

        lEdit=new JLabel("ID CLIENT TO EDIT");
        lEdit.setBounds(460,200,180,50);
        frame.getContentPane().add(lEdit);

        tfIdToEdit=new JTextField();
        tfIdToEdit.setBounds(590,210,70,30);
        frame.getContentPane().add(tfIdToEdit);




        frame.setVisible(true);
    }

    public void showClients(ControllerClienti.AllClientiListener a){
        btnShow.addActionListener(a);
    }
    public void addInsert(ControllerClienti.InsertClientListener a){
        btnInsert.addActionListener(a);
    }

    public void addDelete(ControllerClienti.DeleteClientListener a){
        btnDelete.addActionListener(a);
    }

    public void addUpdate(ControllerClienti.UpdateClientListener a){
        btnEdit.addActionListener(a);
    }

    public int getUserIdClient() {
        return Integer.parseInt(tfId.getText());
    }
    public String getUserNume() {
        return tfName.getText();
    }
    public String getUserPrenume() {
        return tfSurname.getText();
    }
    public String getUserAdresa() {
        return tfAddress.getText();
    }
    public int getUserBani() {
        return Integer.parseInt(tfBuget.getText());
    }
    public int getUserAge() {
        return Integer.parseInt(tfAge.getText());
    }
    public  void setIdClient(String nou) {
        tfId.setText(nou);
    }
    public  void setNume(String nou) {
        tfName.setText(nou);
    }
    public  void setPrenume(String nou) {
        tfSurname.setText(nou);
    }
    public  void setAdresa(String nou) {
        tfAddress.setText(nou);
    }
    public  void setBani(String nou) {
        tfBuget.setText(nou);
    }
    public  void setAge(String nou) {
        tfAge.setText(nou);
    }
    public int getIdClientDeSters() {
        return Integer.parseInt(tfIdToDelete.getText());
    }
    public int getIdClientUpdate() {
        return Integer.parseInt(tfIdToEdit.getText());
    }
    public  void setIdClientDeSters(String nou) {
        tfIdToDelete.setText(nou);
    }
    public  void setIdClientDeEditat(String nou) {
        tfIdToEdit.setText(nou);
    }


}
