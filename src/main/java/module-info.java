module just.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires pdfbox.app;
    requires java.desktop;


    exports just.demo3;
    opens just.demo3 to javafx.fxml;
}