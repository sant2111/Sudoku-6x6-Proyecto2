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

        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {

                TextField sudokuCell = new TextField();
                sudokuCell.getStyleClass().add("sudoku-cell");
                sudokuCell.setPrefSize(70, 70);

                final int currentRow = row;
                final int currentColumn = column;

                sudokuCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.isEmpty()) {
                        model.updateBoardValue(currentRow, currentColumn, 0);
                        refreshBoardValidation();
                        return;
                    }
                    if (!newValue.matches("[1-6]") || newValue.length() > 1) {
                        sudokuCell.setText(oldValue);
                        return;
                    }

                    int enteredNumber = Integer.parseInt(newValue);
                    model.updateBoardValue(currentRow, currentColumn, enteredNumber);
                    refreshBoardValidation();
                });

                if (row == 1 || row == 3) {
                    sudokuCell.getStyleClass().add("bottom-border");
                }
                if (column == 2) {
                    sudokuCell.getStyleClass().add("right-border");
                    if (column == 2 && (row == 3 || row == 1)) {
                        sudokuCell.getStyleClass().add("bottom-right-border");

                    }
                }

                sudokuContainer.add(sudokuCell, column, row);
            }

        }
        sudokuContainer.getStyleClass().add("sudoku-grid");
    }

    private void refreshBoardValidation () {
        for (javafx.scene.Node node : sudokuContainer.getChildren()) {
            if (node instanceof TextField && node.getStyleClass().contains("sudoku-cell")) {
                TextField cell = (TextField) node;

                Integer column = GridPane.getColumnIndex(cell);
                Integer row = GridPane.getRowIndex(cell);

                int r = (row != null) ? row : 0;
                int c = (column != null) ? column : 0;

                String text = cell.getText();

                if (text.isEmpty()) {
                    cell.setStyle("-fx-text-fill: black; -fx-background-color: white;");
                    continue;
                }

                int value = Integer.parseInt(text);

                model.updateBoardValue(r, c, value);

                model.updateBoardValue(r, c, 0);

                if (model.isValid(model.getBoard(), r, c, value)) {
                    cell.setStyle("-fx-text-fill: #1e88e5; -fx-font-weight: bold; -fx-background-color: white;");
                } else {
                    System.out.println("Regla rota en fila " + r + ", columna " + c + "para el numero " + value);
                    cell.setStyle("-fx-text-fill: #e53935; -fx-font-weight: bold; -fx-background-color: #feebeb;");
                }
                model.updateBoardValue(r, c, value);
            }
        }
    }
}





