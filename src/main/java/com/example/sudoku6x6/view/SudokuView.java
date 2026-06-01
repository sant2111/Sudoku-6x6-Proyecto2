package com.example.sudoku6x6.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuView.class.getResource("/com/example/sudoku6x6/sudoku-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 750);
        scene.getStylesheets().add(getClass().getResource("/com/example/sudoku6x6/sudoku.css").toExternalForm());
        stage.setTitle("Sudoku");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    public static SudokuView getInstance() throws IOException {
        if (SudokuViewHolder.INSTANCE == null) {
            SudokuViewHolder.INSTANCE = new SudokuView();
        }
        return SudokuViewHolder.INSTANCE;


    }

    private static class SudokuViewHolder {
        private static SudokuView INSTANCE = null;
    }
}
