package edu.hitsz.strategy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 所有射击策略的抽象父类
 * 普通射击 (CommonShoot), 散状射击 (ScatterShoot)
 * @author hitsz
 */
public abstract class AbstractShootStrategy {
    protected HeroAircraft hero = HeroAircraft.getInstance();
    public abstract List<BaseBullet> shootStrategy(int direction, int shootNum, int power);
}
