package just.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import just.demo3.Model.*;
import just.demo3.Action.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    //
    //MEDICAMENT
    //

    @FXML
    private TableView<Medicament> Medicament_gestion;
    @FXML
    private TableColumn<Medicament,String> CIP_medicament;

    @FXML
    private TableColumn<Medicament,String>Design_medicament;
    @FXML
    private TableColumn<Medicament, Integer> Prix_medicament;
    @FXML
    private TableColumn<Medicament, Integer> Stock_medicament;
    @FXML
    private Button Ajouter_gestion;
    @FXML
    private TextField Cip_gestion;
    @FXML
    private TextField Design_gestion;
    @FXML
    private TextField Prix_gestion;
    @FXML
    private ComboBox<String> CIP_a_supp_gestion;

    //
    //ACHAT
    //

    @FXML
    private Tab Achat_partie;
    @FXML
    private TableView<Panier_medoc> Panier;
    @FXML
    private TableColumn<Panier_medoc,String> Designation;
    @FXML
    private TableColumn<Panier_medoc,Integer> PU;
    @FXML
    private TableColumn<Panier_medoc,Integer> Montant;
    @FXML
    private TableColumn<Panier_medoc,Integer> Qte;
    @FXML
    private ComboBox<String> Medoc_a_retirer_panier;
    @FXML
    private TextField Total;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField Nom_client;

    //
    //Entree de stock
    //

    @FXML
    private TableView<Stock_entree> Entree_tab;
    @FXML
    private TableColumn<Stock_entree,String> Numentree;
    @FXML
    private TableColumn<Stock_entree,String> Date_entree;
    @FXML
    private TableColumn<Stock_entree,String> Cip_entree;
    @FXML
    private TableColumn<Stock_entree,Integer> Qte_entree;
    @FXML
    private ComboBox<String> Cip_entree_txt;
    @FXML
    private Spinner<Integer> Qte_entree_txt;
    @FXML
    private DatePicker Date_entree_txt;

    //
    //RECHERCHE
    //
    @FXML
    private TableView<Medicament> recherche_tbl;
    @FXML
    private TableColumn<Medicament,String> CIP_recherche;
    @FXML
    private TableColumn<Medicament,String> Design_recherche;
    @FXML
    private TableColumn<Medicament, Integer> Prix_recherche;
    @FXML
    private TableColumn<Medicament, Integer> Stock_recherche;
    public TextField rechercher_lbl;
    public Button rechercher_btn;
    public Button Modifier_gestion;
    public Button Supprimer_gestion;
    public TableView rechercher_tbl;
    @FXML
    private TableView<Medicament> recheche_tbl;

    //
    //STAT RECETTE
    //
    @FXML
    public ComboBox box_recette_mois;
    @FXML
    public ComboBox box_recette_annee;
    @FXML
    public TableView<Achat> tbl_recette;
    @FXML
    public TableColumn<Achat,String> col_numachat;
    @FXML
    public TableColumn<Achat,String> col_nomclient;
    @FXML
    public TableColumn<Achat,String> col_date;
    @FXML
    public TableColumn<Achat, Integer> col_quantite;
    @FXML
    public TableColumn<Achat, Integer> col_montant;

    public Label lbl_recette;

    //
    //STAT PLUS VENDUES
    //
    @FXML
    private CategoryAxis X_axis;
    @FXML
    private NumberAxis Y_axis;
    @FXML
    private BarChart<Integer, String> histo_plusvendues;


    //
    //STAT HISTOGRAMME
    //

    public BarChart histo_parmois;
    public ComboBox box_histo_annee;

    //
    //Section
    //
    @FXML
    private Tab Home;
    @FXML
    private Tab Stock;
    @FXML
    private Tab Entree;
    @FXML
    private Tab Rech;
    @FXML
    private Tab Stat;

    //
    //Autre
    //


    


    @FXML
    private AnchorPane Ptn_rouge;
    @FXML
    private TabPane tabpanel;

    private Connection conn;
    private ObservableList<Medicament> MedocList;
    private ObservableList<Medicament> Medoclist2;
    private ObservableList<String> Cip_combobox_gestion2;
    private ObservableList<String> Cip_combobox_gestion;
    private ObservableList<Panier_medoc> panier_model;
    private ObservableList<Stock_entree> Stock_model;
    private String CIP_Transfert;
    private Stage stage_Notif;



private void Affiche_medoc_and_config(){

    SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.MAX_VALUE,1);
    Qte_entree_txt.setValueFactory(valueFactory);

    MedocList = FXCollections.observableArrayList();
    Cip_combobox_gestion= FXCollections.observableArrayList();
    panier_model =FXCollections.observableArrayList();
    Stock_model =FXCollections.observableArrayList();


    try{
        conn = new Connect().Connect_to_db();

        String query = "SELECT * FROM medicament";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){

            Medicament medoc=new Medicament();

            medoc.setCIP(resultSet.getString(1));
            medoc.setDesign(resultSet.getString(2));
            medoc.setPrix(resultSet.getInt(3));
            medoc.setStock(resultSet.getInt(4));
            MedocList.add(medoc);
            Cip_combobox_gestion.add(resultSet.getString(1));
            CIP_a_supp_gestion.setValue(resultSet.getString(1));

        }
        System.out.printf("medoc");
        CIP_a_supp_gestion.setItems(Cip_combobox_gestion);
        Cip_entree_txt.setItems(Cip_combobox_gestion);
        Medicament_gestion.setItems(MedocList);
        CIP_medicament.setCellValueFactory(f->f.getValue().CIPProperty());
        Design_medicament.setCellValueFactory(f->f.getValue().designProperty());
        Prix_medicament.setCellValueFactory(f->f.getValue().prixProperty().asObject());
        Stock_medicament.setCellValueFactory(f->f.getValue().stockProperty().asObject());

    }catch (Exception e){
        System.out.println(e);
    }
    try {
    conn = new Connect().Connect_to_db();

    String query = "SELECT * FROM entree";
    PreparedStatement preparedStatement = conn.prepareStatement(query);

    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){

        Stock_entree stockEntree=new Stock_entree();

        stockEntree.setCIP(resultSet.getString(1));
        stockEntree.setDesign(resultSet.getString(4));
        stockEntree.setDate(resultSet.getString(3));
        stockEntree.setStock(resultSet.getInt(2));
        Stock_model.add(stockEntree);



    }


    Entree_tab.setItems(Stock_model);
    Numentree.setCellValueFactory(f->f.getValue().designProperty());
    Cip_entree.setCellValueFactory(f->f.getValue().CIPProperty());
    Date_entree.setCellValueFactory(f->f.getValue().DateProperty());
    Qte_entree.setCellValueFactory(f->f.getValue().stockProperty().asObject());

    Affiche_ptn_rouge();

}catch (Exception e){
        System.out.println(e);
    }

}
public void Affiche_ptn_rouge() throws SQLException {
    int compter=0;
    String sql="SELECT * FROM medicament";
    PreparedStatement statement= conn.prepareStatement(sql);
    ResultSet resultSet= statement.executeQuery();
    while (resultSet.next()){

        if (resultSet.getInt(4)<=5) compter++;

    }
    if (compter>0) Ptn_rouge.setVisible(true);
    else Ptn_rouge.setVisible(false);

}
public void refreshdata() throws SQLException {
    MedocList.clear();
    Stock_model.clear();
    Cip_combobox_gestion.clear();
    String sql="Select * from medicament";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);

    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){

        Medicament medoc=new Medicament();

        medoc.setCIP(resultSet.getString(1));
        medoc.setDesign(resultSet.getString(2));
        medoc.setPrix(resultSet.getInt(3));
        medoc.setStock(resultSet.getInt(4));
        MedocList.add(medoc);
        Cip_combobox_gestion.add(resultSet.getString(1));
        CIP_a_supp_gestion.setValue(resultSet.getString(1));

    }
    String query = "SELECT * FROM entree";
    PreparedStatement preparedStatement2 = conn.prepareStatement(query);

    ResultSet resultSet2 = preparedStatement2.executeQuery();
    while(resultSet2.next()){

        Stock_entree stockEntree=new Stock_entree();

        stockEntree.setCIP(resultSet2.getString(1));
        stockEntree.setDesign(resultSet2.getString(4));
        stockEntree.setDate(resultSet2.getString(3));
        stockEntree.setStock(resultSet2.getInt(2));
        Stock_model.add(stockEntree);



    }

    Medicament_gestion.refresh();
    Entree_tab.refresh();
    CIP_a_supp_gestion.setItems(Cip_combobox_gestion);
    Cip_entree_txt.setItems(Cip_combobox_gestion);

    Affiche_ptn_rouge();
}
public void ajouter_panier(String Design,int qte,int pu,String cip){
boolean Ajouter =false;
    for (Panier_medoc panierMedoc: panier_model){

        if (panierMedoc.getCip().equals(cip))
        {
            int Stock_init=panierMedoc.getStock();
            int Stock_final=Stock_init+qte;
            panierMedoc.setStock(Stock_final);
            panierMedoc.setMontant(Stock_final*pu);
            Panier.refresh();
            Ajouter =true;
            break;
        }

    }
    if (!Ajouter){

        Panier_medoc panierMedoc=new Panier_medoc();
        panierMedoc.setDesign(Design);
        panierMedoc.setPrix(pu);
        panierMedoc.setStock(qte);
        panierMedoc.setMontant(qte*pu);
        panierMedoc.setCip(cip);
        panier_model.add(panierMedoc);

        Panier.setItems(panier_model);
        Designation.setCellValueFactory(f->f.getValue().designProperty());
        PU.setCellValueFactory(f->f.getValue().prixProperty().asObject());
        Qte.setCellValueFactory(f->f.getValue().stockProperty().asObject());
        Montant.setCellValueFactory(f->f.getValue().montantProperty().asObject());
    }

}
public void Combobox_supp_panier_and_total()
{
    ObservableList<String> combobox_model=FXCollections.observableArrayList();
    int ligne=Panier.getItems().size();
    int Totals=0;
    for (int i=1;i<=ligne;i++){

        combobox_model.add(i+"."+panier_model.get(i-1).getDesign());
        Totals+=panier_model.get(i-1).getMontant();

    }
    Data_transf.Total_conf= String.valueOf(Totals);
    Total.setText(String.valueOf(Totals));
    Medoc_a_retirer_panier.setItems(combobox_model);
}
    private void showSection(Tab section) {
        tabpanel.getSelectionModel().select(section);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    tabpanel.setTabMaxHeight(0);
        Connect db = new Connect();
        System.out.printf("Bonjour");
        conn= db.Connect_to_db();

        affichebarchart();
        Affiche_medoc_and_config();
        Home();


    }
    public void Home(){
    showSection(Home);
    }
    public void Stock(){
    showSection(Stock);
    }
    public void Entree(){
    showSection(Entree);
    }
    public void Achat(){
    showSection(Achat_partie);
    }
    public void Rech(){
    showSection(Rech);
    }
    public void Stat(){
    showSection(Stat);
    }
    public void AJOUTER_gestion_Button(ActionEvent event) throws SQLException {
        String cip=Cip_gestion.getText();
        String design=Design_gestion.getText();
        int prix= Integer.parseInt(Prix_gestion.getText());
        Medicament_action medicamentAction=new Medicament_action();
        medicamentAction.setConnection(conn);
        medicamentAction.add(cip,design,prix);
        refreshdata();

    }
    public void onSUPPRIMER_getion_Button(ActionEvent event) throws SQLException{
        String cip= CIP_a_supp_gestion.getValue();
        Medicament_action medicamentAction=new Medicament_action();
        medicamentAction.setConnection(conn);
        medicamentAction.delete(cip);
        refreshdata();



    }
    public void onModifier_gestion_Button(ActionEvent event) throws SQLException, IOException {
        Data_transf.CIP_a_modif=CIP_a_supp_gestion.getValue();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Modif_gestion_pop.fxml"));
        Parent root = fxmlLoader.load();

        Stage newStage = new Stage();

        // Charger l'icône
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("icon2.png")).toExternalForm());

        // Définir l'icône de l'application
        newStage.getIcons().add(icon);



        newStage.setScene(new Scene(root));
        newStage.setResizable(false);
        newStage.setTitle("Modification");
        Data_transf.Interface_modif_gestion=newStage;
        Stock.setDisable(true);
        newStage.showAndWait();
        Stock.setDisable(false);


    }
    public void Ajouter_panier_btn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajouter_panier.fxml"));
        Data_transf.panier_model=panier_model;
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();

        // Charger l'icône
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("icon2.png")).toExternalForm());

        // Définir l'icône de l'application
        newStage.getIcons().add(icon);

        newStage.setTitle("Ajouter au panier");
        newStage.setScene(new Scene(root));

        newStage.setResizable(false);
        Data_transf.Interface_panier=newStage;
        Achat_partie.setDisable(true);
        newStage.showAndWait();
        Achat_partie.setDisable(false);

    }
    public void Supp_panier_btn(){

        int ligne=Panier.getItems().size();
        String Value=null;
        for (int i=1;i<=ligne;i++){

            Value=i+"."+panier_model.get(i-1).getDesign();
            if (Medoc_a_retirer_panier.getValue()!=null && Value.contains(Medoc_a_retirer_panier.getValue())){
                Panier.getItems().remove(i-1);
                break;
            }
        }
        Combobox_supp_panier_and_total();
    }
    public void Ajouter_stock_btn(ActionEvent event) throws SQLException {
    String cip=Cip_entree_txt.getValue();
    String Date= String.valueOf(Date_entree_txt.getValue());
    int Qte=Qte_entree_txt.getValue();
    String sql="Insert into entree(nummedoc,dateentree,stockentree) values(?,?,?)";
    PreparedStatement statement=conn.prepareStatement(sql);
    statement.setString(1,cip);
    statement.setString(2, Date);
    statement.setInt(3,Qte);
    statement.executeUpdate();
    String sql2="select stock from medicament where nummedoc=?";
    PreparedStatement statement2=conn.prepareStatement(sql2);
    statement2.setString(1,cip);
    ResultSet resultSet=statement2.executeQuery();
    resultSet.next();
        int Stock_initiale=resultSet.getInt(1);
        int Stocj_final=Stock_initiale+Qte;
        String sql3="update medicament set stock=? where nummedoc=?";
        PreparedStatement statement3=conn.prepareStatement(sql3);
        statement3.setInt(1,Stocj_final);
        statement3.setString(2,cip);
        statement3.executeUpdate();
        refreshdata();


    }
    public void Confirm_achat_btn() throws IOException, SQLException {
    Data_transf.Nom_client_conf=Nom_client.getText();
    Data_transf.Date_conf= String.valueOf(Date.getValue());
    Data_transf.Panier_conf=panier_model;

        for (Panier_medoc panierMedoc : panier_model)
        {
            int Qte_init=0;
            int Qte_final=0;

            String sql="select * from medicament where nummedoc=?";
            PreparedStatement statement= conn.prepareStatement(sql);
            statement.setString(1,panierMedoc.getCip());
            ResultSet resultSet=statement.executeQuery();
            resultSet.next();
            Qte_init=resultSet.getInt(4);
            Qte_final=Qte_init-panierMedoc.getStock();

            String sql1="UPDATE medicament SET stock =? WHERE nummedoc=?";
            PreparedStatement statement1=conn.prepareStatement(sql1);
            statement1.setInt(1,Qte_final);
            statement1.setString(2,panierMedoc.getCip());
            statement1.executeUpdate();

            String sql2="INSERT INTO achat (nummedoc, nomclient,dateachat,nbr) VALUES (?,?,?,?)";
            PreparedStatement statement2= conn.prepareStatement(sql2);
            statement2.setString(1,panierMedoc.getCip());
            statement2.setString(2,Nom_client.getText());
            statement2.setString(3, String.valueOf(Date.getValue()));
            statement2.setInt(4,panierMedoc.getStock());
            statement2.executeUpdate();

        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Achat effectuer");
        refreshdata();
        alert.showAndWait();




        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Facture2.fxml"));
        Parent root= fxmlLoader.load();
        Scene scene = new Scene(root);
        Pdf pdf=new Pdf();
        pdf.generatePDF(scene,Data_transf.Nom_client_conf,Data_transf.Date_conf);

        panier_model.clear();
        Panier.refresh();



    }
    public void rechercher(ActionEvent actionEvent) throws SQLException, IOException {
        String recherche = rechercher_lbl.getText();
        Medoclist2 = FXCollections.observableArrayList();
        Cip_combobox_gestion2= FXCollections.observableArrayList();

        try{
            conn = new Connect().Connect_to_db();
            String query = "SELECT * FROM medicament where \"design\" LIKE ? OR \"nummedoc\" LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,"%"+recherche+"%");
            preparedStatement.setString(2,"%"+recherche+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                Medicament medoc2=new Medicament();

                medoc2.setCIP(resultSet.getString(1));
                medoc2.setDesign(resultSet.getString(2));
                medoc2.setPrix(resultSet.getInt(3));
                medoc2.setStock(resultSet.getInt(4));
                Medoclist2.add(medoc2);
                Cip_combobox_gestion2.add(resultSet.getString(1));

            }
            System.out.printf("Recherche trouvée");
            CIP_a_supp_gestion.setItems(Cip_combobox_gestion2);
            rechercher_tbl.setItems(Medoclist2);
            CIP_recherche.setCellValueFactory(f->f.getValue().CIPProperty());
            Design_recherche.setCellValueFactory(f->f.getValue().designProperty());
            Prix_recherche.setCellValueFactory(f->f.getValue().prixProperty().asObject());
            Stock_recherche.setCellValueFactory(f->f.getValue().stockProperty().asObject());

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void Notif() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Notification.fxml"));
        Parent root= fxmlLoader.load();


        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Notification");
        stage.setResizable(false);
        stage.show();
        stage_Notif=stage;
    }
    public void affichebarchart(){
        Statement stmt = null;
        Connect db = new Connect();
        conn= db.Connect_to_db();
        try{
            String sql = "SELECT medicament.design,achat.nbr FROM achat INNER JOIN medicament ON medicament.nummedoc = achat.nummedoc ORDER BY achat.nbr DESC limit 5";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ArrayList<String> medoc = new ArrayList<String>();
            ArrayList<Integer> nombrevendu = new ArrayList<Integer>();

            while (resultSet.next())
            {
                medoc.add(resultSet.getString(1));
                nombrevendu.add(resultSet.getInt(2));
            }
            resultSet.close();
            XYChart.Series dataseries1 = new XYChart.Series();

            for ( int i = 0; i < medoc.size(); i++) {
                dataseries1.getData().add(new XYChart.Data<>(medoc.get(i),nombrevendu.get(i)));
            }
            histo_plusvendues.getData().add(dataseries1);



        }catch (Exception e){ e.printStackTrace();}
    }
}

class Data_transf {
    public static String CIP_a_modif;
    public static Stage Interface_modif_gestion;
    public static Stage Interface_panier;
    public static FXMLLoader Loader_principal;
    public static ObservableList<Panier_medoc> Panier_conf;
    public static String Nom_client_conf;
    public static String Date_conf;
    public static String Total_conf;
    public static ObservableList<Panier_medoc> panier_model;



}