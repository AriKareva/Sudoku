package org.SUDOKU.Images;

import org.SUDOKU.Style.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImagePanel extends JPanel {
    private int id;
    private final BufferedImage image;
    private boolean selected;
    private Style style;

    public ImagePanel(BufferedImage image, Style style) {
        this.image = image;
        this.selected = false;
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public static ArrayList<ImagePanel> imgToDB(ArrayList<ImagePanel> imgs){
        ArrayList<ImagePanel> images = new ArrayList<>(imgs);
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setId(i);
        }

        return images;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    public static int getIdOfSelected(ArrayList<ImagePanel> images){
        for (int i = 0; i < images.size(); i++) {
            if(images.get(i).isSelected()) {
                System.out.println("selected id: " + images.get(i).getId());
                return images.get(i).getId();
            }
        }
        return -1;
    }

    public static Style getStyleByID(ArrayList<ImagePanel> images, int requiredId){
        for (int i = 0; i < images.size(); i++) {
            System.out.println(images.get(i).toString());
            if(images.get(i).getId() == requiredId)
                return images.get(i).getStyle();
        }
        return new Style();
    }

    @Override
    public String toString() {
        return "ImagePanel{" +
                "id=" + id +
                ", selected=" + selected +
                ", style=" + style +
                '}';
    }
}
