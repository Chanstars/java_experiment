package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 所有道具的抽象父类：
 * 恢复道具 HpSupply,火力道具 FireSupply,炸弹道具 BombSupply
 *
 * @author hitsz
 */
public abstract class AbstractSupply extends AbstractFlyingObject {
    protected HeroAircraft heroSupply = HeroAircraft.getInstance();

    /**
     * 道具生效具体数值
     */
    public AbstractSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
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

    public abstract void function();
}
