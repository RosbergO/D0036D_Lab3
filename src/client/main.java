package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramSocket;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        initGUI();
    }

    private static void initGUI() throws InterruptedException, IOException {
        DatagramSocket socket = new DatagramSocket(4446);
        Client client = new Client(4445, socket);
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1920, 1080));
        ColorChooser colorChooser = new ColorChooser();
        frame.add(colorChooser, BorderLayout.EAST);
        Grids grid = new Grids(1005,1005,201);
        frame.add(grid);
        grid.repaint();
        grid.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getX();
                int y = e.getY();
                grid.setPaintColor(colorChooser.getSelectedColor());
                grid.setCell(x, y);
                grid.repaint();
                System.out.println("color"+":"+x/5+":"+y/5+":"+ ColorChooser.getSelectedColor());
                try {
                    if(grid.withinGrid(x/5, y/5)) {
                        client.notifyServer("color"+":"+x/5+":"+y/5+":" + ColorChooser.getSelectedColor()+":");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
        socketRecieve receiver = new socketRecieve(socket, grid);
        receiver.start();

    }

}


