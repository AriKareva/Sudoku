package org.SUDOKU.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;
import java.util.Random;
import java.util.Scanner;

public class FileSave {
    public FileSave(int[][] data, String timerTime, String level) {

        String desktopPath = System.getProperty("user.home") + "/Desktop";
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        String fileName = "sudoku-game-results-" + currentDateTime + ".txt";

        String filePath = desktopPath + File.separator + fileName;

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (int i = 0; i < data.length ; i++) {
                fileWriter.write(Arrays.toString(data[i]));
                fileWriter.write("\n");
            }
            fileWriter.write("\nLevel: " + level);
            fileWriter.write("\n");
            fileWriter.write("\nTime: " + timerTime);
            System.out.println("Данные сохранены в файл " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
