
package bomberman.entity;

import bomberman.cons.Direcciones;

public interface MovingEntity extends Entity {


    public void move(int steps, Direcciones direction);

}
