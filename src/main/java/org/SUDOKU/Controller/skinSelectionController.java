package org.SUDOKU.Controller;

import org.SUDOKU.Images.ImagePanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import static org.SUDOKU.Constants.Constants.SELECTED;

public class skinSelectionController {
    public static void addSkinsSelector(ArrayList<ImagePanel> images) {
        for (int i = 0; i < images.size(); i++) {
            ImagePanel clickedImg = images.get(i);
            clickedImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (ImagePanel img : images) {
                        if (img != clickedImg) {
                            img.setSelected(false);
                            img.setBorder(BorderFactory.createEmptyBorder());
                        }
                    }

                    clickedImg.setSelected(!clickedImg.isSelected());
                    if (clickedImg.isSelected()) {
                        clickedImg.setBorder(BorderFactory.createEtchedBorder(20, SELECTED, SELECTED));
                    } else {
                        clickedImg.setBorder(BorderFactory.createEmptyBorder());
                    }
                }
            });
        }
    }
}
