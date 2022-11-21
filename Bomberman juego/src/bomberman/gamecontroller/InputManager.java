
package bomberman.gamecontroller;

import java.util.List;

import bomberman.cons.Direcciones;
import bomberman.entity.Entity;
import bomberman.entity.player.Player;
import bomberman.entity.staticobjects.BlackBomb;
import bomberman.scenes.Sandbox;
import java.util.Iterator;
import java.util.Vector;
import javafx.scene.input.KeyCode;

public class InputManager {

    public static void handlePlayerMovements(){
        List keyboardInputs = EventHandler.getInputList();
        Player player = Sandbox.getplayer();
        if(keyboardInputs.contains(KeyCode.UP) || keyboardInputs.contains(KeyCode.W)){
            player.move(5,Direcciones.UP);
        }
        if(keyboardInputs.contains(KeyCode.DOWN) || keyboardInputs.contains(KeyCode.S)){
            player.move(5,Direcciones.DOWN);
        }
        if(keyboardInputs.contains(KeyCode.LEFT) || keyboardInputs.contains(KeyCode.A)){
            player.move(5,Direcciones.LEFT);
        }
        if(keyboardInputs.contains(KeyCode.RIGHT) || keyboardInputs.contains(KeyCode.D)){
            player.move(5,Direcciones.RIGHT);
        }
        if( !keyboardInputs.contains(KeyCode.LEFT) &&
            !keyboardInputs.contains(KeyCode.RIGHT) &&
            !keyboardInputs.contains(KeyCode.UP) &&
            !keyboardInputs.contains(KeyCode.DOWN) &&
            !keyboardInputs.contains(KeyCode.W) &&
            !keyboardInputs.contains(KeyCode.A) &&
            !keyboardInputs.contains(KeyCode.S) &&
            !keyboardInputs.contains(KeyCode.D)
          )
        {
            player.move(0, Direcciones.DOWN);
        }
        
        if(keyboardInputs.contains(KeyCode.SPACE)){           
            Sandbox.addEntityToGame(new BlackBomb(player.getPositionX(), player.getPositionY()));
        }        
    }

}
