package just.demo3.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Panier_medoc {
    private StringProperty Design;
    private IntegerProperty Prix;
    private IntegerProperty Stock;
    private IntegerProperty Montant;
    private StringProperty cip;
    public Panier_medoc(){
        Montant = new SimpleIntegerProperty(this,"Montant");
        Design = new SimpleStringProperty(this,"Design");
        Prix = new SimpleIntegerProperty(this,"Prix");
        Stock = new SimpleIntegerProperty(this,"Stock");
        cip = new SimpleStringProperty(this,"cip");

    }
    public void setMontant(int Montant) {this.Montant.set(Montant);
    }

    public int getMontant() {return Montant.get();
    }

    public IntegerProperty montantProperty() {return Montant;
    }

    public void setStock(int stock) {this.Stock.set(stock);
    }

    public void setPrix(int prix) {this.Prix.set(prix);
    }

    public void setDesign(String design) {this.Design.set(design);
    }

    public IntegerProperty stockProperty() {return Stock;
    }

    public IntegerProperty prixProperty() {return Prix;
    }

    public StringProperty designProperty() {return Design;
    }

    public String getDesign() {return Design.get();}

    public int getStock() {return Stock.get();
    }

    public int getPrix() {return Prix.get();
    }

    public void setCip(String cip) {this.cip.set(cip);
    }

    public String getCip() {return cip.get();
    }

    public StringProperty cipProperty() {return cip;
    }
}
