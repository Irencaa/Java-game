module com.example.navigationexample {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;


    opens com.example.navigationexample to javafx.fxml;
    exports com.example.navigationexample;
    exports com.example.navigationexample.panes;
    opens com.example.navigationexample.panes to javafx.fxml;
}