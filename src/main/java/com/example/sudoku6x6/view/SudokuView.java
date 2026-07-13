package com.example.sudoku6x6.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** @author SANTIAGO BARRAGAN TRIANA
 * @author JHONNY ALEXANDER MORENO FLORES
 */

/**
 * JavaFX application that loads the FXML view and stylesheet and shows
 * the main Sudoku window.
 */
public class SudokuView extends Application {

    /**
     * Loads the scene from FXML, applies the stylesheet and displays it.
     *
     * @param stage the primary stage provided by JavaFX
     * @throws IOException if the FXML resource cannot be loaded
     */
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

    /**
     * Returns the lazily-created singleton instance of the view.
     *
     * @return the shared {@code SudokuView} instance
     * @throws IOException if instance creation fails
     */
    public static SudokuView getInstance() throws IOException {
        if (SudokuViewHolder.INSTANCE == null) {
            SudokuViewHolder.INSTANCE = new SudokuView();
        }
        return SudokuViewHolder.INSTANCE;


    }

    /** Holder for the lazily-initialized singleton instance. */
    private static class SudokuViewHolder {
        private static SudokuView INSTANCE = null;
    }
}
