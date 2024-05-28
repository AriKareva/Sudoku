package org.SUDOKU.Controller;

import org.SUDOKU.Style.Style;

import javax.swing.*;
import java.awt.*;

import static org.SUDOKU.Constants.Constants.SIZE;

public class SudokuController {
    public static void sudokuFill(int[][] model, JLabel[][] sudokuNumbers, JPanel panel, Style style) {
        panel.removeAll();
        JTextField[][] inputs = new JTextField[sudokuNumbers[0].length][sudokuNumbers.length];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(model[i][j] != 0) {
                    sudokuNumbers[i][j] = new JLabel(String.valueOf(model[i][j]), SwingConstants.CENTER);
                    sudokuNumbers[i][j].setOpaque(true);
                    sudokuNumbers[i][j].setBorder(BorderFactory.createLineBorder(style.borderColor, style.borderThickness));
                    sudokuNumbers[i][j].setBackground(style.BackgroundColor);
                    sudokuNumbers[i][j].setForeground(style.fontColor);
                    panel.add(sudokuNumbers[i][j]);
                }
                else {
                    inputs[i][j] = new JTextField();
                    inputs[i][j].setHorizontalAlignment(JTextField.CENTER);
                    inputs[i][j].setBorder(BorderFactory.createLineBorder(style.borderColor, style.borderThickness));
                    inputs[i][j].setBackground(style.BackgroundColor);
                    inputs[i][j].setForeground(style.fontColor);
                    panel.add(inputs[i][j]);
                }
            }
        }
    }

    public static int[][] readFilledTable(JPanel panel) {
        int[][] fullyFilledTable = new int[SIZE][SIZE];

        if (panel.getLayout() instanceof GridLayout) {
            GridLayout gridLayout = (GridLayout) panel.getLayout();

            int rows = gridLayout.getRows();
            int cols = gridLayout.getColumns();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Component component = panel.getComponent(i * cols + j);

                    if (component instanceof JLabel) {
                        JLabel cell = (JLabel) component;
                        fullyFilledTable[i][j] = Integer.parseInt(cell.getText());
                    }
                    if (component instanceof JTextField) {
                        JTextField cell = (JTextField) component;
                        fullyFilledTable[i][j] = Integer.parseInt(cell.getText());
                    }
                }
            }
        }
       return fullyFilledTable;
    }
}
