package bomberman;

import bomberman.cons.GlobalConstants;
import bomberman.entity.Entity;
import bomberman.entity.staticobjects.BlackBomb;
import bomberman.gamecontroller.InputManager;
import bomberman.scenes.Sandbox;
import java.util.Iterator;
import java.util.Vector;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop {

    static double tiempoDeJuegoActual;
    static double tiempoDeJuegoViejo;
    static double tiempoDelta;
    final static long startNanoTime = System.nanoTime();
    public static double getTiempoDeJuegoActual() {
        return tiempoDeJuegoActual;
    }

    public static void start(GraphicsContext gc) {
        GameState.gameStatus=GlobalConstants.GameStatus.Running;
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
            	tiempoDeJuegoViejo = tiempoDeJuegoActual;
            	tiempoDeJuegoActual = (currentNanoTime - startNanoTime) / 1000000000.0;
            	tiempoDelta = tiempoDeJuegoActual - tiempoDeJuegoViejo;
                gc.clearRect(0, 0, GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_WIDTH);
                updateGame();
                renderGame();
            }
        }.start();
    }

    public static double getDeltaTime() {
    	return tiempoDelta * 100;
    }

    public static void updateGame() {
        InputManager.handlePlayerMovements();
        Vector<Entity> entities = Sandbox.getEntities();
        Iterator<Entity> it = entities.iterator();
        //remove the current bomb
        while (it.hasNext()) {
            Entity entity = it.next();
            if(entity instanceof BlackBomb){
                boolean alive = ((BlackBomb) entity).isAlive();
                if(!alive){
                    it.remove();
                }
            }
        }
    }

    public static void renderGame() {
        for (Entity e : Sandbox.getEntities()) {
            e.draw();
        }
    }

}
