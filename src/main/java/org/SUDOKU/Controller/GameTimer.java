package org.SUDOKU.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.SUDOKU.Constants.Constants.*;

public class GameTimer extends JPanel {

    private Timer timer;
    private long startTime;
    private ImageIcon icon;
    private boolean timerRunning = false;
    public JButton startTimerButton;
    public JLabel timeLabel;



    public GameTimer() {
        setBackground(MAIN_COLOR);

        icon = new ImageIcon("/Users/arinaaleksandrovna/IdeaProjects/LearningJava/src/SUDOKU/Images/TimerIcon.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(scaledImg);

        JLabel iconLabel = new JLabel(newIcon);

        timeLabel = new JLabel();
        timeLabel.setOpaque(true);
        timeLabel.setBackground(MAIN_COLOR);
        timeLabel.setForeground(BORDER_AND_TEXT_COLOR);
        add(timeLabel);
        add(iconLabel);

        startTimerButton = new JButton("Start timer");
        startTimerButton.setOpaque(true);
        startTimerButton.setBackground(BUTTON_COLOR);
        startTimerButton.setForeground(BORDER_AND_TEXT_COLOR);
        startTimerButton.setBorder(BorderFactory.createLineBorder(BORDER_AND_TEXT_COLOR, 1));
        startTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timerRunning) {
                    startTime = System.currentTimeMillis();
                    startTimerButton.setText("Stop timer");
                    startTimer();
                    timerRunning = true;
                } else {
                    stopTimer();
                    startTimerButton.setText("Start timer");
                    timerRunning = false;
                }
            }
        });
        add(startTimerButton);
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTimeLabel();
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void resetTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        startTime = System.currentTimeMillis();
        timeLabel.setText("00:00");
        timerRunning = false;
        startTimerButton.setText("Start timer");
    }

    private void updateTimeLabel() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        Date now = new Date(elapsedTime);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formattedTime = formatter.format(now);
        timeLabel.setText(formattedTime);
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame();
        fr.add(new GameTimer());
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}