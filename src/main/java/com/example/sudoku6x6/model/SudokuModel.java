package com.example.sudoku6x6.model;

import com.example.sudoku6x6.controller.SudokuController;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuModel {

    private int[][] solution = new int[6][6];
    private int[][] board = new int[6][6];

    public void generateSolution() {
        solution = new int[6][6];
        createSolution(solution);

    }

    public int[][] getBoard() {
        return board;
    }

    public void updateBoardValue(int row, int column, int value) {
        board[row][column] = value;
    }

    public int[][] getSolution() {
        return solution;
    }

    private boolean createSolution(int [][] sudoku) {
        for (int row = 0; row < 6; row ++) {
            for(int column = 0; column < 6; column ++) {

                if (sudoku[row][column] == 0) {

                    List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
                    Collections.shuffle(numbers);

                    for(int num : numbers) {
                        if (isValid(sudoku,row,column,num)) {
                            sudoku[row][column] = num;

                            if(createSolution(sudoku)) {
                                return true;
                            }

                            sudoku[row][column] = 0;
                        }
                    }
                    return false;
                }
            }


        }

        return true;
    }


    public boolean isValid(int[][] sudoku, int row, int column, int num) {

        for (int c = 0; c < 6; c++) {
            if (sudoku[row][c] == num) return false;
            }

        for (int r = 0; r < 6; r++) {
            if (sudoku[r][column] == num) return false;
        }

        int blockRowStart = (row / 2) * 2;
        int blockColumnStart = (column / 3) * 3;
        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColumnStart; c < blockColumnStart + 3; c++) {
                if (sudoku[r][c] == num) return false;
                }
            }
        return true;

    }

    public void printBoard(int[][] board) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

}
