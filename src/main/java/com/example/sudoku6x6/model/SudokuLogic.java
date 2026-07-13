package com.example.sudoku6x6.model;

public interface SudokuLogic {

    void generateSolution();

    int[][] getBoard();

    void updateBoardValue(int row, int column, int value);

    int[][] getSolution();

    void cleanBoard();

    boolean isValid(int[][] sudoku, int row, int column, int num);

    void printBoard(int[][] board);

    void sudokuInitialNumbers();

    int[] getHint();

    boolean hasWon();
}
