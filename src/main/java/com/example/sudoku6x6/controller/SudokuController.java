package com.example.sudoku6x6.controller;

import com.example.sudoku6x6.model.SudokuModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SudokuController implements Initializable {

    private SudokuModel model = new SudokuModel();

    @FXML
    private GridPane sudokuContainer;



    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        createSudoku();
        model.generateSolution();
        model.printBoard(model.getSolution());
    }



    private void createSudoku() {

        for(int row = 0; row < 6; row++) {
            for(int column = 0; column < 6; column++) {

                TextField sudokuCell = new TextField();
                sudokuCell.getStyleClass().add("sudoku-cell");
                sudokuCell.setPrefSize(70,70);
                if (row == 1 || row == 3 ) {
                    sudokuCell.getStyleClass().add("bottom-border");
                }
                if (column == 2) {
                    sudokuCell.getStyleClass().add("right-border");

                if (column == 2 && ( row == 3 || row == 1) ) {
                    sudokuCell.getStyleClass().add("bottom-right-border");

                    }
                }

                sudokuContainer.add(sudokuCell,column,row);
            }


        }
        sudokuContainer.getStyleClass().add("sudoku-grid");

    }


}





