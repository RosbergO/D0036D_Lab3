package utilities;

public class Color extends java.awt.Color {
    int red;
    int green;
    int blue;
    private static Color[] colors = {
            new Color(255, 255, 255),//white
            new Color(255, 0, 0),//red
            new Color(255, 255, 0),//yellow
            new Color(255, 0 ,255),//pink
            new Color(0, 255, 0),//Green
            new Color(0, 255, 255),//Cyan
            new Color(0, 0, 255),//Blue
            new Color(0, 0, 0),//Black
            new Color(128, 0, 255),//Purple
            new Color(128, 128, 128)//Grey
    };

    public Color(int red, int green, int blue)
    {
        super(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Color[] getColorArray()
    {
        return Color.colors;
    }

    public static Color getColorAtIndex(int i) {
        return colors[i];
    }

}

