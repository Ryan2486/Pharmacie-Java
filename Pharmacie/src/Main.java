import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame{
    private JTabbedPane tabbedPane1;
    private JTable Stock;
    private JTextField CIP;
    private JTextField Design;
    private JTextField Prix;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton ajouterButton;
    private JTable Entre;
    private JTextField textField5;
    private JButton ajouterButton1;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JTable table3;
    private JTextField textField6;
    private JButton acheterButton;
    private JButton ajouterAuPanierButton;
    private JButton supprimerDuPanierButton;
    private JTextField textField1;
    private JButton button1;
    private JTable Rechercher;

    public Main(){

       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setSize(1000,600);
       add(tabbedPane1);


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver();
                String cip =CIP.getText();
                String design=Design.getText();
                int prix= Integer.parseInt(Prix.getText());
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pharmacie","postgres","Rabearimanana");
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery("insert into medicament(nummedoc,design,prixunitaire,stock) values ('5555'','hhhh',5,0)");
                    affiche();



                    System.out.printf("cip");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }
   public void affiche(){
       driver();
       try {

            DefaultTableModel model=new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"CIP","Designation","Prix","Stock"});
           Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pharmacie","postgres","Rabearimanana");
           System.out.printf("ok");
           Statement statement=connection.createStatement();
           ResultSet resultSet=statement.executeQuery("Select * from medicament");
           while (resultSet.next()){
               Object[] rowdata=new Object[4];
               for (int i=1;i<=4;i++){
                   rowdata[i-1]=resultSet.getObject(i);
               }
               model.addRow(rowdata);
               Stock.setModel(model);é"''"

           }
       } catch (SQLException e) {
           System.out.printf("error");
       }


   }
    public void driver(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public static void main(String[] args) {


        Main FORM =new Main();
        FORM.affiche();



    }
}
