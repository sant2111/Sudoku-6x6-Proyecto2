package com.example.sudoku6x6;

import com.example.sudoku6x6.view.SudokuView;
import javafx.application.Application;

/** @author SANTIAGO BARRAGAN TRIANA
 * @author JHONNY ALEXANDER MORENO FLORES
 */

/**
 * Application launcher. Kept as a separate main class so the app can be
 * started without the main class extending {@link Application} directly.
 */
public class Launcher {

    /**
     * Program entry point; launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(SudokuView.class, args);
    }
}
