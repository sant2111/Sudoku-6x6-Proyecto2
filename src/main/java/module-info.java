module com.example.sudoku6x6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudoku6x6 to javafx.fxml;
    exports com.example.sudoku6x6;
    exports com.example.sudoku6x6.controller;
    opens com.example.sudoku6x6.controller to javafx.fxml;
    exports com.example.sudoku6x6.view;
    opens com.example.sudoku6x6.view to javafx.fxml;
}