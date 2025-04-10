module org.example.forthtry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.forthtry to javafx.fxml;
    exports org.example.forthtry;
}