package just.demo3.Model;

import javafx.beans.property.*;

public class Medicament {
    private StringProperty CIP;
    private StringProperty Design;
    private IntegerProperty Prix;
    private IntegerProperty Stock;

    public Medicament(){
        CIP = new SimpleStringProperty(this,"CIP");
        Design = new SimpleStringProperty(this,"Design");
        Prix = new SimpleIntegerProperty(this,"Prix");
        Stock = new SimpleIntegerProperty(this,"Stock");

    }
    public void setCIP(String CIP) {this.CIP.set(CIP);
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

    public StringProperty CIPProperty() {return CIP;
    }

    public int getStock() {return Stock.get();
    }

    public int getPrix() {return Prix.get();
    }

    public String getCIP() {return CIP.get();
    }

}
