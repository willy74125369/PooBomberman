package AnimacionBomberman;

import bomberman.entity.Entity;
import bomberman.utils.ImageUtils;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Sprite
{
	public double playSpeed;
	public int spriteLocationOnSheetX;
	public int spriteLocationOnSheetY;
	public int numberOfFrames;
	public double width;
	public double height;
	public int scale;
	public int actualSize;
	public boolean resersePlay;

        public Image[] spriteImages;
        public boolean hasValidSpriteImages;
        
        public Entity entityReference;
        
	public Sprite(Entity e, int actualSize, double playSpeed, int spriteLocationOnSheetX, int spriteLocationOnSheetY, int numberOfFrames, double width, double height,
			int scale, boolean leftToRight)
	{
		super();
		this.actualSize = actualSize;
		this.playSpeed = playSpeed;
		this.spriteLocationOnSheetX = spriteLocationOnSheetX;
		this.spriteLocationOnSheetY = spriteLocationOnSheetY;
		this.numberOfFrames = numberOfFrames;
		this.width = width;
		this.height = height;
		this.scale = scale;
		resersePlay = leftToRight;
                this.entityReference=e;
	}

	public int getXPosition() {
		return entityReference.getPositionX();
	}

	public int getYPosition() {
		return entityReference.getPositionY();
	}


    public Sprite(Entity e,int actualSize,double playSpeed,Image spriteSheet,List<Rectangle> specifications,double width, double height,int scale, boolean leftToRight){
        super();
        this.actualSize = actualSize;
        this.playSpeed = playSpeed;
        this.numberOfFrames=specifications.size();
        this.width = width;
        this.height = height;
        this.scale = scale;
        resersePlay = leftToRight;
        this.entityReference=e;
        hasValidSpriteImages=true;
        spriteImages=new Image[specifications.size()];
        for (int i = 0; i < specifications.size(); i++) {
            Rectangle specification = specifications.get(i);
            int x=(int)specification.getX();
            int y=(int)specification.getY();
            int w=(int)specification.getWidth();
            int h=(int)specification.getHeight();

            spriteImages[i]=ImageUtils.crop(spriteSheet, x, y, w, h);
        }
    }
}
