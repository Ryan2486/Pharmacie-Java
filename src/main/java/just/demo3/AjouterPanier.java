package just.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import just.demo3.Action.*;
import just.demo3.Model.*;

public class AjouterPanier implements Initializable {
    @FXML
    private ComboBox<String> Cip_ajouter_panier;
    @FXML
    private Spinner<Integer> Qte_ajouter_panier;
    @FXML
    private TableView<Medicament> Tab_ajouter_panier;
    @FXML
    private TableColumn<Medicament,String> design_ajouter_panier;
    @FXML
    private TableColumn<Medicament, Integer> Pu_ajouter_panier;
    @FXML
    private TableColumn<Medicament,Integer> Stock_ajouter_panier;
    @FXML
    private AnchorPane Panel2;
    @FXML
    private Button Ajouter_confirm_btn;

    private Connection connection;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Panel2.setVisible(false);
        ObservableList Combobox_CIP = FXCollections.observableArrayList();
        connection=new Connect().Connect_to_db();
        String sql="Select * from medicament";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Combobox_CIP.add(resultSet.getString(1));
                Cip_ajouter_panier.setValue(resultSet.getString(1));
            }
            Cip_ajouter_panier.setItems(Combobox_CIP);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void Recherche_btn_ajouter_panier() throws SQLException {
        Panel2.setVisible(true);
        int Qte_initiale=0;
        Integer max_spinner=0;
        ObservableList MedocListe=FXCollections.observableArrayList();
        String sql="Select * from medicament where nummedoc=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,Cip_ajouter_panier.getValue());
        ResultSet resultSet=statement.executeQuery();



        while (resultSet.next()){

            for (Panier_medoc panierMedoc : Data_transf.panier_model){
                if (panierMedoc.getCip().contains(resultSet.getString(1))){
                    Qte_initiale=panierMedoc.getStock();
                }
            }

            Medicament medoc=new Medicament();

            medoc.setCIP(resultSet.getString(1));
            medoc.setDesign(resultSet.getString(2));
            medoc.setPrix(resultSet.getInt(3));
            medoc.setStock(resultSet.getInt(4)-Qte_initiale);
            MedocListe.add(medoc);
            max_spinner=resultSet.getInt(4)-Qte_initiale;

        }
        Tab_ajouter_panier.setItems(MedocListe);
        design_ajouter_panier.setCellValueFactory(f->f.getValue().designProperty());
        Pu_ajouter_panier.setCellValueFactory(f->f.getValue().prixProperty().asObject());
        Stock_ajouter_panier.setCellValueFactory(f->f.getValue().stockProperty().asObject());
        if (max_spinner!=0){

            SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,max_spinner,1);
            Qte_ajouter_panier.setValueFactory(valueFactory);
            Ajouter_confirm_btn.setDisable(false);
        }
        else{
            SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,0,0);
            Qte_ajouter_panier.setValueFactory(valueFactory);
            Ajouter_confirm_btn.setDisable(true);
        }
        }

    public void Confirm(ActionEvent event) throws SQLException {
    HelloController helloController=Data_transf.Loader_principal.getController();
        String sql="Select * from medicament where nummedoc=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,Cip_ajouter_panier.getValue());
        ResultSet resultSet=statement.executeQuery();
        int qte= Qte_ajouter_panier.getValue();

        while (resultSet.next()){
            helloController.ajouter_panier(resultSet.getString(2),qte,resultSet.getInt(3),resultSet.getString(1));

        }
        helloController.Combobox_supp_panier_and_total();
        Data_transf.Interface_panier.close();
    }
    public void Annuler_btn(){
        Data_transf.Interface_panier.close();
    }
    }

