package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser extends JPanel {
    private JLabel label;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private Color selectedColor;
    private Color[] colors;

    public ColorChooser(Color[] colors) {
        super(new BorderLayout());
        this.colors = colors;
        setPreferredSize(new Dimension(800, 800));
        label = new JLabel("ColoR", JLabel.CENTER);
        label.setOpaque(true);
        int offsetX=0;//, offsetY = 0;
        for(Color color : colors) {
            System.out.println(color);
            JToggleButton btn = new JToggleButton();
            btn.setBackground(color);
            btn.setSize(50,50);
            offsetX+=50;
            btn.setLocation(offsetX,50);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    AbstractButton button = (AbstractButton) e.getSource();
                    Color color = (Color) button.getBackground();
                    selectedColor = color;
                }
            });
            btn.setVisible(true);
            add(btn);
            buttonGroup.add(btn);
        }
        add(label, BorderLayout.CENTER);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public int getSelecetedColorFromArray() {
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == selectedColor) {
                return i;
            }
        }
        return -1;
    }
}
