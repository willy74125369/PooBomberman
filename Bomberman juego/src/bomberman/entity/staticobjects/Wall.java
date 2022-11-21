
package bomberman.entity.staticobjects;

import bomberman.Rendererizador;
import bomberman.animations.Sprite;
import bomberman.entity.Entity;
import bomberman.entity.StaticEntity;
import bomberman.entity.boundedbox.RectBoundedBox;
import javafx.scene.paint.Color;

public class Wall implements StaticEntity {
    public int positionX = 0;
    public int positionY = 0;
    private int height;
    private int width;
    private Color wallColor;
    private Sprite sprite;
    RectBoundedBox entityBoundary;


    public Wall (int x, int y) {
    	positionX = x;
    	positionY = y;

    	width = 16;
    	height = 16;

    	sprite = new Sprite(this, 16, 0, 348, 123, 1, 16, 16, 2, false);
    	entityBoundary = new RectBoundedBox(positionX, positionY, width, height);
    }

    public void changeColor(Color color) {
        wallColor = color;
    }

    @Override
    public boolean isColliding(Entity b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        Rendererizador.playAnimation(sprite);
    }

    @Override
    public void removeFromScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public RectBoundedBox getBoundingBox()
    {
            return entityBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return false;
    }

}
