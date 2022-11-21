
package bomberman.cons;

import javafx.scene.paint.Color;


public class GlobalConstants {

    public static int SCENE_WIDTH = 640;
    public static int SCENE_HEIGHT = 640;
    public static int CANVAS_WIDTH = 640;
    public static int CANVAS_HEIGHT = 640;
    public static String GAME_NAME = "BomberMan";
    public static String GAME_VERSION = "  v 0.1";
    public static Color BACKGROUND_COLOR = Color.WHITE;
    public static int PLAYER_WIDTH = 18;
    public static int PLAYER_HEIGHT = 21;
    public static int PLAYER_SCALE = 2;
    public static enum GameStatus{
        Running,Paused,GameOver
    }
}
