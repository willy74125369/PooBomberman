package bomberman.entity.player;

import bomberman.GameLoop;
import bomberman.Rendererizador;
import bomberman.animations.AnimacionJugador;
import bomberman.animations.Sprite;
import bomberman.cons.Direcciones;
import bomberman.cons.GlobalConstants;
import bomberman.entity.Entity;
import bomberman.entity.KillableEntity;
import bomberman.entity.MovingEntity;
import bomberman.entity.boundedbox.RectBoundedBox;
import bomberman.scenes.Sandbox;

public class Player implements MovingEntity, KillableEntity {

    private int health;
    private boolean isAlive;
    RectBoundedBox playerBoundary;

    Sprite currentSprite;
    AnimacionJugador playerAnimations;

    Direcciones currentDirection;

    public int positionX = 0;
    public int positionY = 0;

    String name;

    public Player() {
        init(64, 64);
    }

    public Player(int posX, int posY) {
        init(posX, posY);
        health = 100;
        isAlive = true;
    }

    private void init(int x, int y) {
        name = "Jugador";

        playerAnimations = new AnimacionJugador(this);

        positionX = x;
        positionY = y;

        playerBoundary = new RectBoundedBox(positionX, positionY, GlobalConstants.PLAYER_WIDTH, GlobalConstants.PLAYER_HEIGHT);

        currentSprite = playerAnimations.getPlayerIdleSprite();
    }

    public void move(Direcciones direction) {
        move(1, direction);
    }

    private void setCurrentSprite(Sprite s) {
        if (s != null) {
            currentSprite = s;
        } else {
            System.out.println("Sprite missing!");
        }
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary = (RectBoundedBox) b.getBoundingBox();
        return playerBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public void draw() {
        if (currentSprite != null) {
            Rendererizador.playAnimation(currentSprite);
        }
    }

    @Override
    public void die() {
        setCurrentSprite(playerAnimations.getPlayerDying());
    }

    private boolean checkCollisions(int x, int y) {
    	playerBoundary.setPosition(x, y);

        for (Entity e : Sandbox.getEntities()) {
            if (e != this && isColliding(e) && !e.isPlayerCollisionFriendly()) {
            	playerBoundary.setPosition(positionX, positionY);
                
                return true;
            }
        }
    	playerBoundary.setPosition(positionX, positionY);
        return false;
    }

    @Override
    public void move(int steps, Direcciones direction) {

        steps *= GameLoop.getDeltaTime();

        if (steps == 0) {
            setCurrentSprite(playerAnimations.getPlayerIdleSprite());
            return;
        } else {
            switch (direction) {
                case UP:
                	if(!checkCollisions(positionX, positionY - steps)) {
	                    positionY -= steps;
	                    setCurrentSprite(playerAnimations.getMoveUpSprite());
	                    currentDirection = Direcciones.UP;
                	}
                    break;
                case DOWN:
                	if(!checkCollisions(positionX, positionY + steps)) {
                		positionY += steps;
	                    setCurrentSprite(playerAnimations.getMoveDownSprite());
	                    currentDirection = Direcciones.DOWN;
                	}
                    break;
                case LEFT:
                	if(!checkCollisions(positionX - steps, positionY)) {
                		positionX -= steps;
	                    setCurrentSprite(playerAnimations.getMoveLeftSprite());
	                    currentDirection = Direcciones.LEFT;
                	}
                    break;
                case RIGHT:
                	if(!checkCollisions(positionX + steps, positionY)) {
                		 positionX += steps;
	                    setCurrentSprite(playerAnimations.getMoveRightSprite());
	                    currentDirection = Direcciones.RIGHT;
                	}
                    break;
                default:
                    setCurrentSprite(playerAnimations.getPlayerIdleSprite());
            }
        }
    }

    @Override
    public void reduceHealth(int damage) {
        if (health - damage <= 0) {
            die();
        } else {
            health -= damage;
        }
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
    public RectBoundedBox getBoundingBox() {
        playerBoundary.setPosition(positionX, positionY);
        return playerBoundary;
    }

    @Override
    public boolean isPlayerCollisionFriendly() {
        return true;
    }
}
