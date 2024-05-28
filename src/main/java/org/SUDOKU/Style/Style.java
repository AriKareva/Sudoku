package org.SUDOKU.Style;

import javax.swing.*;
import java.awt.*;

public class Style {
    public Color borderColor;
    public int borderThickness = 1;
    public Color BackgroundColor;
    public Color fontColor;
    public Font font;

    public Style(Color borderColor, int borderThickness, Color BackgroundColor, Font font, Color fontColor) {
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        this.BackgroundColor = BackgroundColor;
        this.font = font;
        this.fontColor = fontColor;
    }

    public Style(Color borderColor, int borderThickness, Color BackgroundColor, Color fontColor) {
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        this.BackgroundColor = BackgroundColor;
        this.fontColor = fontColor;
    }

    public Style() {
    }

    public static void setSudokuStyle(JPanel sudokuBoard, Style style) {
        if (sudokuBoard.getLayout() instanceof GridLayout gridLayout) {
            int rows = gridLayout.getRows();
            int cols = gridLayout.getColumns();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Component component = sudokuBoard.getComponent(i * cols + j);
                    if (component instanceof JTextField) {
                        JTextField input = (JTextField) component;
                        input.setPreferredSize(new Dimension(50, 50)); // Установите желаемые размеры
                        input.setOpaque(true);
                        input.setForeground(style.fontColor);
                        input.setBackground(style.BackgroundColor);
                        input.setBorder(BorderFactory.createLineBorder(style.borderColor, 1));
                    } else if (component instanceof JLabel) {
                        JLabel number = (JLabel) component;
                        number.setPreferredSize(new Dimension(50, 50)); // Установите желаемые размеры
                        number.setOpaque(true);
                        number.setForeground(style.fontColor);
                        number.setBackground(style.BackgroundColor);
                        number.setBorder(BorderFactory.createLineBorder(style.borderColor, 1));
                    }
                }
            }
            sudokuBoard.setBackground(style.BackgroundColor);
            sudokuBoard.setBorder(BorderFactory.createLineBorder(style.borderColor, 1));
            sudokuBoard.revalidate(); // Переотрендерить панель для обновления визуального отображения
            sudokuBoard.repaint();
        }
    }

    @Override
    public String toString() {
        return "Style{" +
                "borderColor=" + borderColor +
                ", borderThickness=" + borderThickness +
                ", BackgroundColor=" + BackgroundColor +
                ", fontColor=" + fontColor +
                ", font=" + font +
                '}';
    }
}
