package just.demo3.Action;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Medicament_action {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void add(String CIP,String Design,int Prix) throws SQLException {
        String sql="Insert into medicament(nummedoc,design,prixunitaire,stock) values(?,?,?,?) ";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,CIP);
        statement.setString(2,Design);
        statement.setInt(3,Prix);
        int Stock=0;
        statement.setInt(4,Stock);
        statement.executeUpdate();
    }
    public void delete(String CIP) throws SQLException {
        if (CIP!=null)
        {
        String sql="delete from medicament where nummedoc=?";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,CIP);
        statement.executeUpdate();}
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Suppression");
            alert.setContentText("Aucune CIP selectionner");
            alert.showAndWait();

        }
    }
    public void Modif(String CIP ,String new_design ,int new_prix) throws SQLException {
        String sql="Update medicament set design=? ,prixunitaire=? where nummedoc=?";
        PreparedStatement statement= connection.prepareStatement(sql);
        statement.setString(1,new_design);
        statement.setInt(2,new_prix);
        statement.setString(3,CIP);
        int resultat=statement.executeUpdate();
    }



}
