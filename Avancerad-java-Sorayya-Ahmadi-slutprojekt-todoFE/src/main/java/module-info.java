module org.example.todofe {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind; // Ensure you're importing the right module
    exports org.example.todofe;
    opens org.example.todofe to javafx.fxml;
}