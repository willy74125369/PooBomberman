
package AnimacionBomberman;

import bomberman.Rendererizador;
import bomberman.cons.GlobalConstants;
import bomberman.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class AnimacionBomba {
    Sprite blackBomb;
    double playSpeed;

    public Sprite getBlackBomb() {
        return blackBomb;
    }

    public void setBlackBomb(Sprite blackBomb) {
        this.blackBomb = blackBomb;
    }
    public AnimacionBomba(Entity e) {
        Image img = Rendererizador.getSpiteSheet();
        playSpeed=0.3;
        
        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(181, 93,17,16));
        specs.add(new Rectangle(211, 93,16,16));
        specs.add(new Rectangle(240, 93,18,17));
        blackBomb = new Sprite(e,30,playSpeed,img, specs,GlobalConstants.PLAYER_WIDTH+2, GlobalConstants.PLAYER_HEIGHT+2, 2, false);
    }
}
