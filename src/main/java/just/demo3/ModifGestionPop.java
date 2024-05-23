package just.demo3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import just.demo3.Action.*;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifGestionPop implements Initializable {

    @FXML
    private TextField CIP_modif_gestion;
    @FXML
    private TextField Design_modif_gestion;
    @FXML
    private TextField Prix_modif_gestion;

    private Connection connection;
    private HelloController principal;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HelloController interfaceAController = new HelloController();
        String CIP=Data_transf.CIP_a_modif;
        CIP_modif_gestion.setText(CIP);
        Connect connect=new Connect();
        connection=connect.Connect_to_db();
        System.out.printf(""+CIP);
        String sql ="Select * from medicament where nummedoc=? ";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,CIP);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                String design=resultSet.getString(2);
                int Prix=resultSet.getInt(3);
                Design_modif_gestion.setText(design);
                Prix_modif_gestion.setText(String.valueOf(Prix));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void Confirm_btn_modif_gestion() throws SQLException, IOException {
        String cip=CIP_modif_gestion.getText();
        String design=Design_modif_gestion.getText();
        int Prix= Integer.parseInt(Prix_modif_gestion.getText());
        Medicament_action medicamentAction=new Medicament_action();
        medicamentAction.setConnection(connection);
        medicamentAction.Modif(cip,design,Prix);

        principal=Data_transf.Loader_principal.getController();
        principal.refreshdata();
        Data_transf.Interface_modif_gestion.close();


    }
    public void annuler_btn_modif_gestion(){
        Data_transf.Interface_modif_gestion.close();

    }


}
