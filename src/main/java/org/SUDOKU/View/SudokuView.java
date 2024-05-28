package org.SUDOKU.View;

import org.SUDOKU.Controller.FileSave;
import org.SUDOKU.Controller.GameTimer;
import org.SUDOKU.Images.ImagePanel;
import org.SUDOKU.Model.SudokuModel;
import org.SUDOKU.Style.Style;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.SUDOKU.Constants.Constants.*;
import static org.SUDOKU.Controller.SudokuController.readFilledTable;
import static org.SUDOKU.Controller.SudokuController.sudokuFill;
import static org.SUDOKU.Controller.skinSelectionController.addSkinsSelector;
import static org.SUDOKU.Images.ImagePanel.*;
import static org.SUDOKU.Model.SudokuModel.checkSudoku;
import static org.SUDOKU.Model.SudokuModel.copyUnfilledTable;
import static org.SUDOKU.Style.Styles.*;
import static org.SUDOKU.Constants.Constants.DEFAULT_WINDOW_WIDTH;


public class SudokuView {
    public static String userLevel;

    public static void App() {
        // app window
        JFrame app = new JFrame("SUDOKU");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);

        // utils page 1
        GridLayout mainGrid = new GridLayout(2, 1);
        GridLayout skinsGrid = new GridLayout(1, 3);
        skinsGrid.setHgap(50);
        GridLayout optionsGrid = new GridLayout(1, 2);
        optionsGrid.setHgap(50);
        optionsGrid.setVgap(50);

        // page 1
        JPanel main = new JPanel();
        main.setOpaque(true);
        main.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        main.setBackground(MAIN_COLOR);
        main.setLayout(mainGrid);

        JPanel skins = new JPanel(skinsGrid);
        skins.setBackground(MAIN_COLOR);
        skins.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        JPanel options = new JPanel(optionsGrid);
        options.setOpaque(true);
        options.setBackground(MAIN_COLOR);

        // images for page 1
        ImagePanel pinkMode = null;
        ImagePanel blueMode = null;
        ImagePanel classicMode = null;

        try {
            BufferedImage pink = ImageIO.read(new File("/Users/arinaaleksandrovna/IdeaProjects/LearningJava/src/SUDOKU/Images/pinkMode.png"));
            BufferedImage blue = ImageIO.read(new File("/Users/arinaaleksandrovna/IdeaProjects/LearningJava/src/SUDOKU/Images/blueMode.png"));
            BufferedImage classic = ImageIO.read(new File("/Users/arinaaleksandrovna/IdeaProjects/LearningJava/src/SUDOKU/Images/classicMode.png"));

            pinkMode = new ImagePanel(pink, CRANBERRY_CRUSH);
            blueMode = new ImagePanel(blue, BLUE_CURAÇAO);
            classicMode = new ImagePanel(classic, CLASSIC);

            skins.add(pinkMode);
            skins.add(blueMode);
            skins.add(classicMode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // classify images
        ArrayList<ImagePanel> images = new ArrayList<>();
        images.add(pinkMode);
        images.add(blueMode);
        images.add(classicMode);
        images = imgToDB(images);
        addSkinsSelector(images);

        // buttons page 1
        JButton startButton = new JButton("start");
        startButton.setOpaque(true);
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(BORDER_AND_TEXT_COLOR);
        startButton.setBorder(BorderFactory.createLineBorder(BORDER_AND_TEXT_COLOR, 1));

        // dropdown menu page 1
        Border menuBorder = BorderFactory.createLineBorder(BORDER_AND_TEXT_COLOR, 1);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(BUTTON_COLOR);
        menuBar.setForeground(BORDER_AND_TEXT_COLOR);
        menuBar.setBorder(menuBorder);

        JMenu levels = new JMenu("           Level:           ");
        levels.setOpaque(true);
        levels.setBackground(BUTTON_COLOR);
        levels.setForeground(BORDER_AND_TEXT_COLOR);
        levels.setBorder(menuBorder);

        JLabel selectedLevel = new JLabel("", SwingConstants.CENTER);
        selectedLevel.setOpaque(true);
        selectedLevel.setBackground(BUTTON_COLOR);
        selectedLevel.setForeground(BORDER_AND_TEXT_COLOR);
        selectedLevel.setBorder(menuBorder);

        JMenuItem easyLevel = new JMenuItem("Easy");
        easyLevel.setOpaque(true);
        easyLevel.setBackground(BUTTON_COLOR);
        easyLevel.setForeground(BORDER_AND_TEXT_COLOR);
        easyLevel.setBorder(menuBorder);
        easyLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLevel.setText("Easy");
                userLevel = "Easy";
            }
        });

        JMenuItem mediumLevel = new JMenuItem("Medium");
        mediumLevel.setOpaque(true);
        mediumLevel.setBackground(BUTTON_COLOR);
        mediumLevel.setForeground(BORDER_AND_TEXT_COLOR);
        mediumLevel.setBorder(menuBorder);
        mediumLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLevel.setText("Medium");
                userLevel = "Medium";
            }
        });


        JMenuItem hardLevel = new JMenuItem("Hard");
        hardLevel.setOpaque(true);
        hardLevel.setBackground(BUTTON_COLOR);
        hardLevel.setForeground(BORDER_AND_TEXT_COLOR);
        hardLevel.setBorder(menuBorder);
        hardLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLevel.setText("Hard");
                userLevel = "Hard";
            }
        });

        levels.getPopupMenu().setBorder(menuBorder);
        for (Component c : levels.getMenuComponents()) {
            c.setBackground(BUTTON_COLOR);
            c.setForeground(BORDER_AND_TEXT_COLOR);
        }

        levels.add(easyLevel);
        levels.add(mediumLevel);
        levels.add(hardLevel);
        menuBar.add(levels);

        // layout page 1
        JPanel p = new JPanel(new GridLayout(1,2));
        p.setOpaque(true);
        p.setBackground(BUTTON_COLOR);
        p.add(menuBar);
        p.add(selectedLevel);
        options.add(p);
        options.add(startButton, BorderLayout.NORTH);
        options.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));

        main.add(skins);
        main.add(options);
        main.setVisible(true);


        // utils for page 2
        GridLayout sudokuGrid = new GridLayout(9, 9);
        GridLayout buttonGrid = new GridLayout(3, 1);
        GridLayout gameInfoGrid = new GridLayout(1, 2);
        buttonGrid.setVgap(50);

        GameTimer timer = new GameTimer();

        Border border = BorderFactory.createLineBorder(BORDER_AND_TEXT_COLOR, 1);

        // page 2
        JPanel game = new JPanel();
        game.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        game.setBackground(MAIN_COLOR);
        game.setLayout(new BorderLayout());

        JPanel sudokuBoard = new JPanel(sudokuGrid);
        sudokuBoard.setBackground(MAIN_COLOR);
        sudokuBoard.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 20));
        JPanel buttons = new JPanel(buttonGrid);
        buttons.setBackground(MAIN_COLOR);
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        JLabel[][] sudokuNumbers = new JLabel[SIZE][SIZE];

        // buttons page 2
        JButton checkButton = new JButton("Check");
        checkButton.setOpaque(true);
        checkButton.setBorder(border);
        checkButton.setBackground(BUTTON_COLOR);
        checkButton.setForeground(BORDER_AND_TEXT_COLOR);
        checkButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        JButton saveButton = new JButton("Save");
        saveButton.setOpaque(true);
        saveButton.setBorder(border);
        saveButton.setBackground(BUTTON_COLOR);
        saveButton.setForeground(BORDER_AND_TEXT_COLOR);
        saveButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        JButton restartButton = new JButton("Restart");
        restartButton.setOpaque(true);
        restartButton.setBorder(border);
        restartButton.setBackground(BUTTON_COLOR);
        restartButton.setForeground(BORDER_AND_TEXT_COLOR);
        restartButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        buttons.add(checkButton);
        buttons.add(saveButton);
        buttons.add(restartButton);

        game.add(sudokuBoard, BorderLayout.CENTER);
        game.add(buttons, BorderLayout.EAST);
        game.setVisible(true);

        // collecting pages together
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(main, "Main page");
        cardPanel.add(game, "Game page");
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

        // events page 1
        final int[][][] filledTableStorage = {new int[SIZE][SIZE]};
        ArrayList<ImagePanel> finalImages = images;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals(selectedLevel.getText(), "") && getIdOfSelected(finalImages) != -1) {
                    //style
                    Style selectedStyle = getStyleByID(finalImages, getIdOfSelected(finalImages));
                    System.out.println(selectedStyle.toString());
                    // model
                    SudokuModel model = new SudokuModel(selectedLevel.getText());
                    filledTableStorage[0] = copyUnfilledTable(model.filledSudokuTable);
                    sudokuFill(model.unFilledSudokuTable, sudokuNumbers, sudokuBoard, selectedStyle);
                    cardLayout.show(cardPanel, "Game page");

                }
                else if(Objects.equals(selectedLevel.getText(), "") && getIdOfSelected(finalImages) != -1)
                    JOptionPane.showMessageDialog(main, "select game level, please");
                else if(!Objects.equals(selectedLevel.getText(), "") && getIdOfSelected(finalImages) == -1)
                    JOptionPane.showMessageDialog(main, "select style, please");
                else if(Objects.equals(selectedLevel.getText(), "") && getIdOfSelected(finalImages) == -1)
                    JOptionPane.showMessageDialog(main, "select style and fame level, please");
                else
                    JOptionPane.showMessageDialog(main, "unknown error");
            }
        });

        // events page 2
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.resetTimer();
                cardLayout.show(cardPanel, "Main page");
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Arrays.deepToString(readFilledTable(sudokuBoard)));
                if (checkSudoku(readFilledTable(sudokuBoard))) { // не работает
                    JOptionPane.showMessageDialog(game, "Sudoku is filled correctly");
                } else {
                    JOptionPane.showMessageDialog(game, "Sudoku is not filled correctly");
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FileSave(readFilledTable(sudokuBoard), timer.timeLabel.getText(), userLevel);
                    JOptionPane.showMessageDialog(game, "File with results is saved to your desktop");
                }
                catch (NumberFormatException ex) {
                    // Обработка ошибки преобразования данных в файл
                    JOptionPane.showMessageDialog(game, "Error formatting data for file: " + ex.getMessage());
                    System.err.println("Error formatting data: " + ex.getMessage());
                } catch (Exception ex) {
                    // Обработка любых других исключений
                    JOptionPane.showMessageDialog(game, "An unexpected error occurred: " + ex.getMessage());
                    System.err.println("Unexpected error: " + ex.getMessage());
                }
            }
        });

        JPanel gameInfo = new JPanel(gameInfoGrid);
        gameInfo.setOpaque(true);
        gameInfo.setBackground(MAIN_COLOR);
        gameInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));


        JPanel margin = new JPanel();
        margin.setOpaque(true);
        margin.setBackground(MAIN_COLOR);


        gameInfo.add(timer);

        game.add(gameInfo, BorderLayout.NORTH);

        app.add(cardPanel);
        app.setVisible(true);
    }
}
