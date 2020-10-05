package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ColorChooser extends JPanel {
    private JLabel label;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private static int selectedColor;
    utilities.Color[] colors = utilities.Color.getColorArray();

    public ColorChooser() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(800, 800));
        label = new JLabel("", JLabel.CENTER);
        label.setOpaque(true);
        int offsetX=0;
        for(int i = 0; i < colors.length; i++) {
            System.out.println(i);
            JToggleButton btn = new JToggleButton();
            btn.setBackground(colors[i]);
            btn.setSize(50,50);
            offsetX+=50;
            btn.setLocation(offsetX,50);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    AbstractButton button = (AbstractButton) e.getSource();
                    java.awt.Color color = button.getBackground();
                    selectedColor = Arrays.asList(colors).indexOf(color);
                }
            });
            btn.setVisible(true);
            add(btn);
            buttonGroup.add(btn);
        }
        add(label, BorderLayout.CENTER);
    }

    public static int getSelectedColor() {
        return selectedColor;
    }
}
