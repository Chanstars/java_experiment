package edu.hitsz.supply;

import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 所有道具的抽象父类：
 * 恢复道具 HpSupply,火力道具 FireSupply,炸弹道具 BombSupply
 *
 * @author hitsz
 */
public abstract class Supply extends AbstractFlyingObject {

    /**
     * 道具生效具体数值
     */
    protected int power;
    public Supply(int locationX, int locationY, int speedX, int speedY, int power){
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }
    }

    public abstract int function();
}
