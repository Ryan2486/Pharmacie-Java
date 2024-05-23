package just.demo3.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.print.DocFlavor;

public class Achat {
    private StringProperty CIP;
    private StringProperty Nom;
    private IntegerProperty Date;
    private IntegerProperty Qte;
    private StringProperty Num_achat;

    public Achat(){
        CIP = new SimpleStringProperty(this,"CIP");
        Nom = new SimpleStringProperty(this,"Nom");
        Date = new SimpleIntegerProperty(this,"Date");
        Qte = new SimpleIntegerProperty(this,"Qte");
        Num_achat=new SimpleStringProperty(this,"Num_achat");

    }

    public void setCIP(String CIP) {this.CIP.set(CIP);
    }

    public String getCIP() {return CIP.get();
    }
    public StringProperty CIPProperty() {return CIP;
    }

    public void setNom(String nom) {this.Nom.set(nom);
    }

    public String getNom() {return Nom.get();
    }

    public StringProperty nomProperty() {return Nom;
    }

    public void setDate(int date) {this.Date.set(date);
    }

    public void setNum_achat(String num_achat) {this.Num_achat.set(num_achat);
    }

    public int getDate() {return Date.get();
    }

    public void setQte(int qte) {this.Qte.set(qte);
    }

    public IntegerProperty dateProperty() {return Date;
    }

    public String getNum_achat() {return Num_achat.get();
    }

    public IntegerProperty qteProperty() {return Qte;
    }

    public int getQte() {return Qte.get();
    }

    public StringProperty num_achatProperty() {return Num_achat;
    }
}
