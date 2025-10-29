module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.makery.address to javafx.fxml;
    exports ch.makery.address;
    exports ch.makery.address.view;
    opens ch.makery.address.view to javafx.fxml;
}