package just.demo3.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock_entree {
    private StringProperty CIP;
    private StringProperty Numentree;
    private StringProperty Date;
    private IntegerProperty Stock;

    public Stock_entree(){
        CIP = new SimpleStringProperty(this,"CIP");
        Numentree = new SimpleStringProperty(this,"Design");
        Date = new SimpleStringProperty(this,"Date");
        Stock = new SimpleIntegerProperty(this,"Stock");

    }
    public void setCIP(String CIP) {this.CIP.set(CIP);
    }

    public void setStock(int stock) {this.Stock.set(stock);
    }

    public void setDate(String prix) {this.Date.set(prix);
    }

    public void setDesign(String design) {this.Numentree.set(design);
    }

    public IntegerProperty stockProperty() {return Stock;
    }

    public StringProperty DateProperty() {return Date;
    }

    public StringProperty designProperty() {return Numentree;
    }

    public String getDesign() {return Numentree.get();}

    public StringProperty CIPProperty() {return CIP;
    }

    public int getStock() {return Stock.get();
    }

    public String getDate() {return Date.get();
    }

    public String getCIP() {return CIP.get();
    }

}
