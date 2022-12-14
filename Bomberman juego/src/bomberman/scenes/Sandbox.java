
package bomberman.scenes;

import static bomberman.cons.GlobalConstants.CANVAS_HEIGHT;
import static bomberman.cons.GlobalConstants.CANVAS_WIDTH;
import static bomberman.cons.GlobalConstants.SCENE_HEIGHT;
import static bomberman.cons.GlobalConstants.SCENE_WIDTH;

import java.util.Vector;

import bomberman.GameLoop;
import bomberman.Rendererizador;
import bomberman.entity.Entity;
import bomberman.entity.player.Player;
import bomberman.entity.staticobjects.BlackBomb;
import bomberman.entity.staticobjects.Wall;
import bomberman.gamecontroller.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sandbox {

    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;
    private static boolean sceneStarted;
    static Player sandboxPlayer;
    static{
        sceneStarted=false;
    }

	private static Vector<Entity> entities = new Vector<Entity>();

	public static Vector<Entity> getEntities(){
		return entities;
	}

	public static boolean addEntityToGame(Entity e){
		if(!entities.contains(e)){
			entities.add(e);
			return true;
		} else {
			return false;
		}
	}

    private static void init() {
        root = new Group();
        s = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.setFill(Color.BLUE);
        Rendererizador.init();
        GameLoop.start(gc);

        
        Player p = new Player();
        setPlayer(p);
        
 
        loadMap();

        EventHandler.attachEventHandlers(s);

    }


    public static void loadMap() {
    	Vector<Wall> walls = new Vector<Wall>();

    	for(int i = 0; i < SCENE_WIDTH; i += 32){
    		for(int j = 0; j < SCENE_HEIGHT; j += 32){
    			if(i == 0 || i + 33 > SCENE_HEIGHT || j == 0 || j + 33 > SCENE_WIDTH) {
    				walls.add(new Wall(i, j));
    			}
    		}
    	}

    	for(Wall wall : walls) {
    		addEntityToGame(wall);
    	}
    }

    public static void setupScene(){
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
    }
    public static Scene getScene() {
        return s;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Canvas getCanvas() {
        return c;
    }

    public static void setPlayer(Player p){
        sandboxPlayer = p;
        addEntityToGame(p);
    }
    public static Player getplayer(){
        return sandboxPlayer;
    }
}
