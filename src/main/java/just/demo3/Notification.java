package just.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import just.demo3.Action.Connect;
import just.demo3.Model.Medicament;
import just.demo3.Model.Stock_entree;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Notification implements Initializable {
    @FXML
    private TableView<Medicament> notif;
    @FXML
    private TableColumn<Medicament,String> CIP_notif;
    @FXML
    private TableColumn<Medicament,String> Design_notif;
    @FXML
    private TableColumn<Medicament,Integer> Stock_notif;

    private Connection conn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn=new Connect().Connect_to_db();
        ObservableList<Medicament> Notif_liste= FXCollections.observableArrayList();
        String sql="SELECT * FROM medicament where stock<=5";
        PreparedStatement statement= null;
        try {
            statement = conn.prepareStatement(sql);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()){

                Medicament medoc =new Medicament();

                medoc.setCIP(resultSet.getString(1));
                medoc.setDesign(resultSet.getString(2));
                medoc.setPrix(resultSet.getInt(3));
                medoc.setStock(resultSet.getInt(4));
                Notif_liste.add(medoc);

            }
            notif.setItems(Notif_liste);
            Design_notif.setCellValueFactory(f->f.getValue().designProperty());
            CIP_notif.setCellValueFactory(f->f.getValue().CIPProperty());
            Stock_notif.setCellValueFactory(f->f.getValue().stockProperty().asObject());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
