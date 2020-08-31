public class main {
    public static void main(String[] args) {

        int[][] gameBoard = new int[201][201]; //All values are default 0
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


        System.out.println(gameBoard[2][3]);
    }
}


