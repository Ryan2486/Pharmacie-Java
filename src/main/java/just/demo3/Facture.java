package just.demo3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import just.demo3.Model.Panier_medoc;

import java.net.URL;
import java.util.ResourceBundle;

public class Facture implements Initializable {
    @FXML
    private TextField Date_f;
    @FXML
    private TextField Nom_client_f;
    @FXML
    private TextField Total_f;
    @FXML
    private TableView<Panier_medoc> Panier;
    @FXML
    private TableColumn<Panier_medoc,String> Designation_f;
    @FXML
    private TableColumn<Panier_medoc, Integer> PU_f;
    @FXML
    private TableColumn<Panier_medoc, Integer>Qte_f;
    @FXML
    private TableColumn<Panier_medoc, Integer>Montant_f;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Panier.setItems(Data_transf.Panier_conf);
        Designation_f.setCellValueFactory(f->f.getValue().designProperty());
        PU_f.setCellValueFactory((f->f.getValue().prixProperty().asObject()));
        Qte_f.setCellValueFactory(f->f.getValue().stockProperty().asObject());
        Montant_f.setCellValueFactory(f->f.getValue().montantProperty().asObject());

        Nom_client_f.setText(Data_transf.Nom_client_conf);
        Date_f.setText(Data_transf.Date_conf);
        Total_f.setText(Data_transf.Total_conf);

    }
}
