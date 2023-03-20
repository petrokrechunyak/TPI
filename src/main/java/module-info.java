module com.studing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.studing.ex1 to javafx.fxml;
    exports com.studing.ex1;

    opens com.studing.ex2 to javafx.fxml;
    exports com.studing.ex2;
}