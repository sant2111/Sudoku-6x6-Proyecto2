package com.example.sudoku6x6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sudoku-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 750);
        scene.getStylesheets().add(getClass().getResource("sudoku.css").toExternalForm());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.show();
    }
}
