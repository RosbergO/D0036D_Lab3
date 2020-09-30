package client;

import java.awt.*;
import javax.swing.*;
public class Grids extends JPanel implements Runnable {
    private int width;
    private int height;
    private int rowsColumns;
    private Color paintColor;
    private Color[][] gameGrid;

    public Grids(int width, int height, int rowsColumns, Color[] colors) {
        gameGrid = new Color[rowsColumns][rowsColumns]; //x, y
        this.width = width;
        this.height = height;
        this.rowsColumns = rowsColumns;
        setSize(width, height);
        setVisible(true);
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponents(g);
        for(int i = 0; i < rowsColumns; i++) {
            for(int n = 0; n < rowsColumns; n++) {
                Color color = gameGrid[i][n];
                g.setColor(color);
                g.fillRect(i*5, n*5, 5, 5);
                g.setColor(Color.BLACK);
            }
        }
    }

    public void paintGrid(Graphics g) {
        int a = 0;
        int b = 0;
        for (int x = height / rowsColumns; x < height; x += height / rowsColumns) {
            g.drawLine(0, height - x, width, height - x);
            a++;
        }
        for (int y = width / rowsColumns; y < width; y += width / rowsColumns) {
            g.drawLine(0 + y, 0, 0 + y, height);
            b++;
        }
    }


    public void setCell(int x, int y) {
        int xPos = x/5;
        int yPos = y/5;
        if(xPos < rowsColumns && yPos < rowsColumns) {
            gameGrid[xPos][yPos] = paintColor;
        }
        else {
            System.out.println("Not within grid");
        }
    }

    public void setCell(int x, int y, Color color) {
        int xPos = x;
        int yPos = y;
        if(xPos < rowsColumns && yPos < rowsColumns) {
            gameGrid[xPos][yPos] = color;
        }
        else {
            System.out.println("Not within grid");
        }
    }
    public Color[][] getGameGrid() {
        return gameGrid;
    }
    public void setGameGrid(Color[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public Color getPaintColor() {
        return paintColor;
    }
    public void setPaintColor(Color paintColor) {
        this.paintColor = paintColor;
    }

    @Override
    public void run() {

    }
}