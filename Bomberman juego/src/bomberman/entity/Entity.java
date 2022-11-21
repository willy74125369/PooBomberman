
package bomberman.entity;

import bomberman.entity.boundedbox.RectBoundedBox;

public interface Entity {
    boolean isColliding(Entity b);
    boolean isPlayerCollisionFriendly();
    void draw();
    void removeFromScene();
    int getPositionX();
    int getPositionY();
    RectBoundedBox getBoundingBox();
    
}
