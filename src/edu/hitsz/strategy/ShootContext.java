package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 *
 * 射击策略选择
 * @author hitsz
 */

public class ShootContext {
    private AbstractShootStrategy abstractShootStrategy;
    public ShootContext(AbstractShootStrategy abstractShootStrategy){
        this.abstractShootStrategy = abstractShootStrategy;
    }
    public void setStrategy(AbstractShootStrategy abstractShootStrategy){
        this.abstractShootStrategy = abstractShootStrategy;
    }
    public List<BaseBullet> executeStrategy(int direction, int shootNum, int power){
        return abstractShootStrategy.shootStrategy(direction, shootNum, power);
    }

}
