package com.example.sudoku6x6;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {

   @FXML
    private GridPane sudokuContainer;

   @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        createSudoku();
   }

   private void createSudoku() {

       for(int row = 0; row < 6; row++) {
           for(int column = 0; column < 6; column++) {

               TextField sudokuCell = new TextField();
               sudokuCell.setAlignment(Pos.CENTER);
               sudokuCell.setPrefSize(70,70);
               sudokuCell.setStyle("-fx-font-size: 30px;");




               sudokuContainer.add(sudokuCell,column,row);
           }


       }


   }


}





