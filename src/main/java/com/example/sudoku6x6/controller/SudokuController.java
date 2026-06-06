package com.example.sudoku6x6.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import com.example.sudoku6x6.model.SudokuModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SudokuController implements Initializable {

    private SudokuModel model = new SudokuModel();
    int hintsUsed = 0;

    @FXML
    private GridPane sudokuContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createSudoku();
        model.cleanBoard();
        model.generateSolution();
        model.printBoard(model.getSolution());
        model.sudokuInitialNumbers();
        updateBoard();

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

    private void refreshBoardValidation() {
        int[][] board = model.getBoard();

        for (javafx.scene.Node node : sudokuContainer.getChildren()) {
            if (node instanceof TextField && node.getStyleClass().contains("sudoku-cell")) {
                TextField cell = (TextField) node;

                if (cell.getStyleClass().contains("fixed-cell") || cell.getStyleClass().contains("hint-cell"))
                {
                    continue;
                }

                Integer column = GridPane.getColumnIndex(cell);
                Integer row = GridPane.getRowIndex(cell);

                int r = (row != null) ? row : 0;
                int c = (column != null) ? column : 0;

                int value = board[r][c];

                if (value == 0) {
                    cell.setStyle("-fx-text-fill:  black; -fx-background-color: white;");
                    continue;
                }

                board[r][c] = 0;
                boolean valid = model.isValid(board, r, c, value);
                board[r][c] = value;

                if (valid) {
                    cell.setStyle("-fx-text-fill: #1e88e5; -fx-font-weight: bold; -fx-background-color: white;");
                } else {
                    cell.setStyle("-fx-text-fill: #e53935; -fx-font-weight: bold; -fx-background-color: #feebeb;");
                }
            }
        }
        if(model.hasWon()) {
            freezeBoard();

            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setTitle("Victoria");
            winAlert.setHeaderText("Felicidades, has ganado!");
            winAlert.setContentText("Completaste el sudoku correctamente sin romper las reglas");
            winAlert.showAndWait();
        }
    }


    @FXML

    public void newGame() {

        Alert newGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
        newGameAlert.setTitle("Nueva Partida");
        newGameAlert.setHeaderText("¿Quieres iniciar una nueva partida de Sudoku?");
        newGameAlert.setResizable(false);
        newGameAlert.showAndWait().ifPresent(response ->
        {
            if (response == ButtonType.OK) {
                model.cleanBoard();
                model.generateSolution();
                hintsUsed = 0;
                model.printBoard(model.getSolution());
                model.sudokuInitialNumbers();
                updateBoard();


            } else {
                newGameAlert.close();
            }
        });
    }

    public void updateBoard() {

        for (javafx.scene.Node node : sudokuContainer.getChildren()) {
            if (node instanceof TextField) {
                TextField cell = (TextField) node;

                int row = GridPane.getRowIndex(cell) != null ? GridPane.getRowIndex(cell) : 0;
                int col = GridPane.getColumnIndex(cell) != null ? GridPane.getColumnIndex(cell) : 0;
                int value = model.getBoard()[row][col];

                cell.getStyleClass().removeAll("fixed-cell", "hint-cell");
                cell.setStyle("");

                if (value != 0) {

                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.getStyleClass().add("fixed-cell");
                } else {

                    cell.setText("");
                    cell.setEditable(true);
                    cell.getStyleClass().remove("fixed-cell");
                }
            }

        }
    }

    @FXML
    public void hint() {
        if (hintsUsed >= 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pista");
            alert.setHeaderText("No se puede dar una pista");
            alert.setContentText("Has alcanzado el límite máximo de 3 pistas por partida");
            alert.showAndWait();
            return;
        }

        int[] hintCell = model.getHint();

        if (hintCell != null) {
            hintsUsed++;

            for (javafx.scene.Node node : sudokuContainer.getChildren()) {
                if (node instanceof TextField) {
                    TextField cell = (TextField) node;

                    Integer rowIndex = GridPane.getRowIndex(cell);
                    Integer colIndex = GridPane.getColumnIndex(cell);
                    int row = rowIndex != null ? rowIndex : 0;
                    int col = colIndex != null ? colIndex : 0;

                    if (row == hintCell[0] && col == hintCell[1]) {
                        cell.getStyleClass().add("hint-cell");
                        cell.setEditable(false);
                        cell.setText(String.valueOf(model.getBoard()[row][col]));
                        refreshBoardValidation();
                        break;
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pista");
            alert.setHeaderText("No se puede dar una pista");
            alert.setContentText("El tablero está lleno o no se encontraron casillas válidas");
            alert.showAndWait();
        }
    }

    private void freezeBoard() {
        for (javafx.scene.Node node : sudokuContainer.getChildren()) {
            if(node instanceof TextField) {
                ((TextField) node).setEditable(false);
            }
        }
    }

}

