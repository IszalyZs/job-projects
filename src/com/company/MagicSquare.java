package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MagicSquare {
    private final String FILE;
    private int[][] matrix;
    private int matrixRow;
    private List<String> allLines;
    private int diagonal1;
    private int diagonal2;

    public MagicSquare(String filePath) {
        this.FILE = filePath;
    }

    public void init() {
        initMatrix();
        fillMatrix(allLines);
        if (validMagicSquare()) System.out.printf("Mágikus négyzet! A kulcsérték: %s", diagonal1);
        else
            System.out.println("Nem mágikus négyzet!");
    }

    private void initMatrix() {
        try {
            allLines = Files.readAllLines(Path.of(FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        matrixRow = allLines.size();
        matrix = new int[matrixRow][matrixRow];
    }

    private void fillMatrix(List<String> allLines) {
        for (int row = 0; row < matrixRow; row++) {
            for (int column = 0; column < matrixRow; column++) {
                matrix[row][column] = Integer.parseInt(allLines.get(row).split(" ")[column]);
            }
        }
        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixRow; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean validMagicSquare() {
        int[] rows = new int[matrixRow];
        int[] columns = new int[matrixRow];
        summarizeRowsAndColumns(rows, columns);
        if (!equal(rows) ||
                !equal(columns) ||
                rows[0] != columns[0]) return false;
        summarizeDiagonals();
        if (diagonal1 != diagonal2 || diagonal1 != rows[0])
            return false;
        return checkUniqueness();
    }

    private void summarizeRowsAndColumns(int[] rows, int[] columns) {
        for (int row = 0; row < rows.length; row++) {
            for (int column = 0; column < columns.length; column++) {
                rows[row] = rows[row] + matrix[row][column];
                columns[column] = columns[column] + matrix[row][column];
            }
        }
    }

    private boolean equal(int[] array) {
        int firstItem = array[0];
        for (int index = 1; index < array.length; index++) {
            if (firstItem != array[index]) return false;
        }
        return true;
    }

    private void summarizeDiagonals() {
        for (int i = 0; i < matrixRow; i++) {
            diagonal1 += matrix[i][i];
            diagonal2 += matrix[i][matrixRow - i - 1];
        }
    }

    private boolean checkUniqueness() {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixRow; j++) {
                numbers.add(matrix[i][j]);
            }
        }
        if (numbers.size() != matrixRow * matrixRow)
            return false;
        else return true;
    }


}