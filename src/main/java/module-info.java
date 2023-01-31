module com.example.currencyfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens com.example.currencyfx to javafx.fxml;
    exports com.example.currencyfx;
}