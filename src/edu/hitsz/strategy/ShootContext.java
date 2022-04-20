package edu.hitsz.strategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 *
 * 射击策略选择
 * @author hitsz
 */

public class ShootContext {
    private ShootStrategy shootStrategy;
    public ShootContext(ShootStrategy shootStrategy){
        this.shootStrategy = shootStrategy;
    }
    public void setStrategy(ShootStrategy shootStrategy){
        this.shootStrategy = shootStrategy;
    }
    public List<BaseBullet> executeStrategy(int direction, int shootNum, int power){
        return shootStrategy.shootStrategy(direction, shootNum, power);
    }

}
