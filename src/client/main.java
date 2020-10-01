package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        initGUI();

    }

    private static void initGUI() throws InterruptedException, IOException {
        DatagramSocket socket = new DatagramSocket(4446);
        Client client = new Client(4445, socket);


        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1920, 1080));
        Color[] colors = {
                new Color(255, 0, 0),//red
                new Color(255, 255, 0),//yellow
                new Color(255, 0 ,255),//pink
                new Color(255, 255, 255),//black
                new Color(0, 255, 0),//Green
                new Color(0, 255, 255),//Cyan
                new Color(0, 0, 255),//Blue
                new Color(0, 0, 0),//White
                new Color(128, 0, 255),//Purple
                new Color(128, 128, 128)//Grey

        };
        ColorChooser colorChooser = new ColorChooser(colors);
        frame.add(colorChooser, BorderLayout.EAST);
        Grids grid = new Grids(1005,1005,201, colors);
        frame.add(grid);

        NetworkParser parser = new NetworkParser();

        int[] parsedString = parser.parse("99:100:8:");
        grid.setCell(parsedString[0], parsedString[1], colors[parsedString[2]]);
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

                //String message = "color";
                System.out.println("color"+":"+x/5+":"+y/5+":"+colorChooser.getSelecetedColorFromArray());
                try {
                    client.notifyServer("color"+":"+x/5+":"+y/5+":"+colorChooser.getSelecetedColorFromArray()+":");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                //System.out.println(x);
                //System.out.println(y);
            }
        });
        frame.pack();
        frame.setVisible(true);
        SocketRecieve receiver = new SocketRecieve(socket, grid, colors);
        receiver.start();

    }

}


