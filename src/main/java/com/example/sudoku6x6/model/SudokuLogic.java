package com.example.sudoku6x6.model;

/** @author SANTIAGO BARRAGAN TRIANA
 * @author JHONNY ALEXANDER MORENO FLORES
 */

/**
 * Defines the game logic for a 6x6 Sudoku.
 * Declares the operations a model must provide: building the solution,
 * managing the player's board, validating moves, giving hints and
 * detecting a win.
 */
public interface SudokuLogic {

    /** Builds a complete, valid 6x6 solution grid. */
    void generateSolution();

    /**
     * Returns the current player's board.
     *
     * @return the 6x6 board, where 0 means an empty cell
     */
    int[][] getBoard();

    /**
     * Sets the value of a single board cell.
     *
     * @param row    the cell row (0-5)
     * @param column the cell column (0-5)
     * @param value  the value to store (0 to clear, 1-6 for a number)
     */
    void updateBoardValue(int row, int column, int value);

    /**
     * Returns the generated solution for the current game.
     *
     * @return the 6x6 solution grid
     */
    int[][] getSolution();

    /** Clears the player's board, leaving every cell empty. */
    void cleanBoard();

    /**
     * Checks whether a number can be placed in a cell without breaking
     * the Sudoku rules (row, column and 2x3 block).
     *
     * @param sudoku the grid to validate against
     * @param row    the cell row (0-5)
     * @param column the cell column (0-5)
     * @param num    the number to test (1-6)
     * @return {@code true} if the number is valid at that position
     */
    boolean isValid(int[][] sudoku, int row, int column, int num);

    /**
     * Prints the given grid to the standard output.
     *
     * @param board the 6x6 grid to print
     */
    void printBoard(int[][] board);

    /**
     * Fills the board with the initial clues: two numbers per 2x3 block,
     * taken from the generated solution.
     */
    void sudokuInitialNumbers();

    /**
     * Reveals one empty cell as a hint by copying its solution value.
     *
     * @return the {row, column} of the revealed cell, or {@code null}
     *         if no hint can be given
     */
    int[] getHint();

    /**
     * Indicates whether the player has completed the board correctly.
     *
     * @return {@code true} if the board matches the solution
     */
    boolean hasWon();
}
