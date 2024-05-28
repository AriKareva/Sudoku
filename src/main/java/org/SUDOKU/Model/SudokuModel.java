package org.SUDOKU.Model;

import java.util.*;

import static org.SUDOKU.Constants.Constants.SIZE;
import static org.SUDOKU.Constants.Constants.SMALL_AREA_SIZE;

public class SudokuModel {
    public int[][] filledSudokuTable;
    public int[][] unFilledSudokuTable;
    public String levelCode;

    public SudokuModel(String levelCode) {
        this.levelCode = levelCode;
        this.filledSudokuTable = fillSudokuTable();
        this.unFilledSudokuTable = clearCells(filledSudokuTable, getLevelCode(levelCode));  //levelCode = 10/ 20/ 30
    }

    public static int getLevelCode(String level){
        if(level.equals("Hard"))
            return 30;
        else if(level.equals("Medium"))
            return 20;
        else
            return 10;
    }

    public static int[][] copyUnfilledTable(int[][] unFilledSudokuTable){
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

            }
        }
        return copy;
    }

    public static int[][] fillSudokuTable(){
        int[][] sudokuTable = new int[SIZE][SIZE];
        for(int  i = 0; i<SIZE; i++){
            sudokuTable[0][i] = i+1;
        }
        for(int  i = 0; i<SIZE; i++){
            for(int  j = 0; j<SIZE; j++) {
                sudokuTable[i][j] = ((i*SMALL_AREA_SIZE + i/SMALL_AREA_SIZE + j) % (SIZE) + 1);
            }
        }
        transpose(sudokuTable);
        swapSmallRolls(sudokuTable);
        swapRowsArea(sudokuTable);
        return sudokuTable;
    }

    public static int[][] transpose(int[][] sudokuTable){
        for (int i = 0; i < sudokuTable.length; i++) {
            for (int j = i + 1; j < sudokuTable.length; j++) {
                int temp = sudokuTable[i][j];
                sudokuTable[i][j] = sudokuTable[j][i];
                sudokuTable[j][i] = temp;
            }
        }
        return sudokuTable;
    }
    public static void swapSmallRolls(int[][] sudokuTable){
            Random random = new Random();
            int area = random.nextInt(SMALL_AREA_SIZE);
            int line1 = random.nextInt(SMALL_AREA_SIZE);
            int N1 = area * SMALL_AREA_SIZE + line1;

            int line2 = random.nextInt(SMALL_AREA_SIZE);
            while (line1 == line2) {
                line2 = random.nextInt(SMALL_AREA_SIZE);
            }
            int N2 = area * SMALL_AREA_SIZE + line2;
            int[] temp = sudokuTable[N1];
            sudokuTable[N1] = sudokuTable[N2];
            sudokuTable[N2] = temp;

    }
    //???swap_columns_small
    public static void swapRowsArea(int[][] sudokuTable) {
        Random random = new Random();
        int area1 = random.nextInt(SMALL_AREA_SIZE);
        int area2 = random.nextInt(SMALL_AREA_SIZE);
        while (area1 == area2) {
            area2 = random.nextInt(SMALL_AREA_SIZE);
        }
        for (int i = 0; i < SMALL_AREA_SIZE; i++) {
            int N1 = area1 * SMALL_AREA_SIZE + i;
            int N2 = area2 * SMALL_AREA_SIZE + i;
            int[] temp = sudokuTable[N1];
            sudokuTable[N1] = sudokuTable[N2];
            sudokuTable[N2] = temp;
        }
    }
    //???swap_columns_area
    public static int[][] clearCells(int[][] filledSudokuTable, int levelCode) {
//        System.out.println(filledSudokuTable);
        int[][] unFilledSudokuTable = new int[SIZE][SIZE];
//        System.out.println(unFilledSudokuTable);
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(filledSudokuTable[i], 0, unFilledSudokuTable[i], 0, SIZE);
        }

        Random rd = new Random();
        int emptyCells = (int) SIZE*SIZE * levelCode / 100;
        System.out.println(emptyCells);
        while (emptyCells != 0) {
            int genI, genJ;
            do {
                genI = rd.nextInt(SIZE);
                genJ = rd.nextInt(SIZE);
                System.out.println(unFilledSudokuTable[genI][genJ]);
            } while (unFilledSudokuTable[genI][genJ] == 0);
            unFilledSudokuTable[genI][genJ] = 0;
            emptyCells--;
        }

        return unFilledSudokuTable;
    }

    public static boolean checkSudoku(int[][] fullyFilledTable){
        boolean areasCheck = true;

        for (int k = 0; k < fullyFilledTable.length-2; k += 3) {
            areasCheck &= checkSmallArea(fullyFilledTable, k);
        }

        return checkLine(fullyFilledTable) && checkLine(transpose(fullyFilledTable)) && areasCheck && checkSmallArea(fullyFilledTable);
    }

    public static boolean checkLine(int[][] table){
        if (table.length == 0) {
            return false;
        }

        for (int i = 0; i < table.length; i++) {
            int[] array = table[i];

            Set<Integer> uniqueNumbers = new HashSet<>();
            for (int number : array) {
                if (!uniqueNumbers.add(number)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSmallArea(int[][] table, int k){
        if (table.length == 0) {
            return false;
        }

        StringBuilder areaNumbers = new StringBuilder();
        Set<Integer> areaNumbersSet = new HashSet<>();

        for (int i = k; i < k + 3; i++) {
            for (int j = k; j < table.length/3; j++) {
                areaNumbers.append(table[i][j]);
                areaNumbersSet.add(table[i][j]);
            }
        }

        return areaNumbers.length() == areaNumbersSet.size();
    }

    public static boolean checkSmallArea(int[][] table){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if(table[i][j] < 0 || table[i][j] > 9)
                    return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        SudokuModel sudokuTable = new SudokuModel("Hard");
//        for(int  i = 0; i<SIZE; i++){
//            for(int  j = 0; j<SIZE; j++) {
//                System.out.printf("%6s", sudokuTable.filledSudokuTable[i][j]);
//            }
//            System.out.println("\n");
//        }
//        System.out.println("\n");
//        for(int  i = 0; i<SIZE; i++){
//            for(int  j = 0; j<SIZE; j++) {
//                System.out.printf("%6s", sudokuTable.unFilledSudokuTable[i][j]);
//            }
//            System.out.println("\n");
//        }
//    }
}
