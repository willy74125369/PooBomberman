package bomberman;

import bomberman.animations.Sprite;
import bomberman.scenes.Sandbox;
import bomberman.utils.ImageUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rendererizador {

    static Image img;
    static {
        img = ImageUtils.loadImage("Resources/img/sprites_without_border.png");
    }
    public static void init() {
        
    }
    public static Image getSpiteSheet(){
        return img;
    }
    public static void playAnimation(Sprite sprite) {
        double time = GameLoop.getTiempoDeJuegoActual();
        GraphicsContext gc = Sandbox.getGraphicsContext();
        if (sprite.hasValidSpriteImages) {
            playAnimation(sprite.spriteImages, sprite.playSpeed, sprite.getXPosition(), sprite.getYPosition(), sprite.width*sprite.scale, sprite.height*sprite.scale);
        } else {
            playAnimation(gc, time, sprite.actualSize, sprite.spriteLocationOnSheetX, sprite.spriteLocationOnSheetY, sprite.numberOfFrames, sprite.getXPosition(), sprite.getYPosition(), sprite.width, sprite.height, sprite.scale, sprite.resersePlay, sprite.playSpeed);
        }
    }

    public static void playAnimation(Image[] imgs, double speed, int x, int y, double w, double h) {
        double time = GameLoop.getTiempoDeJuegoActual();
        GraphicsContext gc = Sandbox.getGraphicsContext();
        int numberOfFrames = imgs.length;
        int index = findCurrentFrame(time, numberOfFrames, speed);
        gc.drawImage(imgs[index], x, y, w, h);
    }

    public static void playAnimation(GraphicsContext gc, double time, int actualSize, int startingPointX, int startingPointY, int numberOfFrames, int x, int y, double width, double height, double scale, boolean reversePlay, double playSpeed) {

        double speed = playSpeed >= 0 ? playSpeed : 0.3;

        int index = findCurrentFrame(time, numberOfFrames, speed);

        int newSpriteSheetX = reversePlay ? startingPointX + index * actualSize : startingPointX;
        int newSpriteSheetY = reversePlay ? startingPointY : startingPointY + index * actualSize;
        gc.drawImage(img, newSpriteSheetX, newSpriteSheetY, width, height, x, y, width * scale, height * scale);
    }

    private static int findCurrentFrame(double time, int totalFrames, double speed) {
        return (int) (time % (totalFrames * speed) / speed);
    }
}
