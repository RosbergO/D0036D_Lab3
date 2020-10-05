package client;

import java.awt.*;
import javax.swing.*;
public class Grids extends JPanel implements Runnable {
    private int width;
    private int height;
    private int rowsColumns;
    private int paintColor;
    private int[][] gameGrid;

    public Grids(int width, int height, int rowsColumns) {
        gameGrid = new int[rowsColumns][rowsColumns]; //x, y
        this.width = width;
        this.height = height;
        this.rowsColumns = rowsColumns;
        setSize(width, height);
        setVisible(true);
        for(int i=0; i< gameGrid.length; i++) {
            for (int j = 0; j < gameGrid.length; j++) {
                gameGrid[i][j] = 0;
            }
        }
    }

    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponents(g);
        for(int i = 0; i < rowsColumns; i++) {
            for(int n = 0; n < rowsColumns; n++) {
                int color = gameGrid[i][n];
                g.setColor(utilities.Color.getColorAtIndex(color));
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

    public boolean withinGrid(int xPos, int yPos) {
        if(xPos < rowsColumns && xPos >= 0 && yPos >= 0 && yPos < rowsColumns) {
            return true;
        }
        return false;
    }

    public void setCell(int x, int y) {
        int xPos = x/5;
        int yPos = y/5;
        if(withinGrid(xPos, yPos)) {
            gameGrid[xPos][yPos] = paintColor;
        }
        else {
            System.out.println("Not within grid");
        }
    }

    public void setCell(int x, int y, int color) {
        int xPos = x;
        int yPos = y;
        if(withinGrid(xPos, yPos)) {
            gameGrid[xPos][yPos] = color;
        }
        else {
            System.out.println("Not within grid");
        }
    }
    public int[][] getGameGrid() {
        return gameGrid;
    }
    public void setGameGrid(int[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public int getPaintColor() {
        return paintColor;
    }
    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
    }

    @Override
    public void run() {

    }
}