package com.example.sudoku6x6.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author SANTIAGO BARRAGAN TRIANA
 * @author JHONNY ALEXANDER MORENO FLORES
 */

/**
 * Default implementation of {@link SudokuLogic} for a 6x6 Sudoku.
 * Holds the generated solution and the player's board, and provides the
 * operations to generate, validate, hint and check the game state.
 */
public class SudokuModel implements SudokuLogic {

    private int[][] solution = new int[6][6];
    private int[][] board = new int[6][6];

    /** Generates a new complete, valid solution grid. */
    @Override
    public void generateSolution() {
        solution = new int[6][6];
        createSolution(solution);

    }

    /** Returns the current player's board (0 means an empty cell). */
    @Override
    public int[][] getBoard() {
        return board;
    }

    /** Stores the given value in the specified cell. */
    @Override
    public void updateBoardValue(int row, int column, int value) {
        board[row][column] = value;
    }

    /** Returns the generated solution grid. */
    @Override
    public int[][] getSolution() {
        return solution;
    }

    /**
     * Recursively fills the grid with a valid solution using backtracking.
     *
     * @param sudoku the grid being solved
     * @return {@code true} once the grid is completely and validly filled
     */
    private boolean createSolution(int[][] sudoku) {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {

                if (sudoku[row][column] == 0) {

                    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (isValid(sudoku, row, column, num)) {
                            sudoku[row][column] = num;

                            if (createSolution(sudoku)) {
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

    /** Clears the player's board, leaving every cell empty. */
    @Override
    public void cleanBoard() {

        board = new int[6][6];

    }


    /** Checks that the number breaks no row, column or 2x3 block rule. */
    @Override
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

    /** Prints the given grid to the console. */
    @Override
    public void printBoard(int[][] board) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    /** Places two clue numbers per 2x3 block, taken from the solution. */
    @Override
    public void sudokuInitialNumbers() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                int startRow = row * 2;
                int startCol = col * 3;

                List<Integer> nums = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
                Collections.shuffle(nums);
                int num1 = nums.get(0);
                int num2 = nums.get(1);

                int row1 = startRow + (num1 / 3);
                int col1 = startCol + (num1 % 3);
                int row2 = startRow + (num2 / 3);
                int col2 = startCol + (num2 % 3);

                board[row1][col1] = solution[row1][col1];
                board[row2][col2] = solution[row2][col2];

            }
        }

    }

    /** Reveals a random empty cell as a hint, or {@code null} if none can be given. */
    @Override
    public int[] getHint() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (emptyCells.size() <= 1) {
            return null;
        }

        Collections.shuffle(emptyCells);
        int[] hintCell = emptyCells.get(0);
        board[hintCell[0]][hintCell[1]] = solution[hintCell[0]][hintCell[1]];
        return hintCell;
    }

    /** Returns {@code true} when the board fully matches the solution. */
    @Override
    public boolean hasWon() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == 0 || board[row][col] != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
}


